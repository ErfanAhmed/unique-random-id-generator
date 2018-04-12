package com.erfan;

/**
 * @author Erfan Ahmed
 * @since 04/13/2018
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(UniqueRandomIdGenerator.getUniqueRandomId());
        System.out.println(UniqueRandomIdGenerator.getUniqueRandomId("prefix", "suffix"));
        System.out.println(UniqueRandomIdGenerator.getUniqueRandomId("suffix", UniqueRandomIdGenerator.Type.SUFFIX));
        System.out.println(UniqueRandomIdGenerator.getUniqueRandomId("prefix", UniqueRandomIdGenerator.Type.PREFIX));
    }
}
