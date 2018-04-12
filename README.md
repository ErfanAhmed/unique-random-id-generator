# unique-random-id-generator
A Java based unique random Id generator application

### Required JDK version is 1.8 or higher

#### Example usage:

```
1. UniqueRandomIdGenerator.getUniqueRandomId()
2. UniqueRandomIdGenerator.getUniqueRandomId("prefix", "suffix")
3. UniqueRandomIdGenerator.getUniqueRandomId("suffix", UniqueRandomIdGenerator.Type.SUFFIX)
4. UniqueRandomIdGenerator.getUniqueRandomId("prefix", UniqueRandomIdGenerator.Type.PREFIX)
```

#### Example output:

>1. 9CABSSK
>2. prefix-9CAB2BF-suffix
>3. 2CBBSU8-suffix
>4. prefix-GCAB9DB