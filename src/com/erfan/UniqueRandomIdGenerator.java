package com.erfan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author Erfan Ahmed
 * @since 04/13/2018
 */
public class UniqueRandomIdGenerator {

    /**
     * Letter 'O' and number 0 didn't take into account to avoid misleading interpretation
     */
    private static char LETTER[] = {
            'A', '1', 'B', '2', 'C', '3', 'D', '4', 'E', '5', 'F', '6', 'G', '7', 'H', '8', 'I', '9', 'J', 'K', 'L',
            'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    /**
     * prime numbers are for mod(%) operation
     * prime number must not be greater than (LETTER.length + 1)
     */
    private static List<Integer> PRIME_LIST = makePrimList(2, LETTER.length);
    private static int START_POS = 0;

    public static String getUniqueRandomId() {
        StringBuilder uniqueRandomId = new StringBuilder();

        LocalDateTime localDateTime = LocalDateTime.now();

        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        int sec = localDateTime.getSecond();
        int nanoSec = localDateTime.getNano();

        uniqueRandomId.append(LETTER[year % getRandomPrime()])
                .append(LETTER[month % getRandomPrime()])
                .append(LETTER[day % getRandomPrime()])
                .append(LETTER[hour % getRandomPrime()])
                .append(LETTER[minute % getRandomPrime()])
                .append(LETTER[sec % getRandomPrime()])
                .append(LETTER[nanoSec % getRandomPrime()]);

        return shuffle(uniqueRandomId);
    }

    public static String getUniqueRandomId(String prefixOrSuffix, Enum type) {

        if (type == Type.PREFIX) {
            return prefixOrSuffix + "-" + getUniqueRandomId();
        } else if (type == Type.SUFFIX) {
            return getUniqueRandomId() + "-" + prefixOrSuffix;
        }

        return getUniqueRandomId();
    }

    public static String getUniqueRandomId(String prefix, String suffix) {
        return prefix + "-" + getUniqueRandomId() + "-" + suffix;
    }

    /**
     * This method generates a random number position
     * Returns the prime number of that position of PRIME_LIST
     *
     * @param origin (inclusive) basically the start position of LIST which is 0
     * @param bound  (exclusive) basically size of PRIME_LIST
     * @return a prime number from PRIME_LIST
     */
    private static int getPrimeNumber(int origin, int bound) {
        int position = new Random().ints(1, origin, bound).findFirst().getAsInt();

        return PRIME_LIST.get(position);
    }

    /**
     * To mod(%) with different prime number
     * This will add more randomness
     *
     * @return a random prime number from PRIME_LIST
     */
    private static int getRandomPrime() {
        return getPrimeNumber(START_POS, PRIME_LIST.size());
    }

    private static List<Integer> makePrimList(int fromNum, int toNum) {
        List<Integer> primeNumList = new ArrayList();

        IntStream.rangeClosed(fromNum, toNum)
                .filter(i -> IntStream.rangeClosed(2, (int) Math.sqrt(i))
                        .allMatch(j -> i % j != 0))
                .forEach(n -> {
                    primeNumList.add(n);
                });

        return primeNumList;
    }

    /**
     * this method shuffles the generated uid which increases randomness during concurrent actions
     *
     * @param uid
     * @return randomly shuffled uid
     */
    private static String shuffle(StringBuilder uid) {
        StringBuilder newId = new StringBuilder();

        for (int i = 0; i < uid.length() + 1; i++) {
            int pos = new Random().ints(1, 0, uid.length()).findFirst().getAsInt();
            newId.append(uid.charAt(pos));
            uid.deleteCharAt(pos);

            i = 0;
        }

        return newId.toString();
    }

    public enum Type {
        PREFIX,
        SUFFIX,
    }
}
