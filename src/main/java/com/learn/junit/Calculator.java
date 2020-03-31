package com.learn.junit;

public class Calculator {
    private long value = 0;

    public long add(long x) {
        value += x;
        return value;
    }

    public long sub(long x) {
        value -= x;
        return value;
    }
}
