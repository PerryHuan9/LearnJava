package com.learn.generic;

class Pair<T extends Number> {
    private T first;
    private T last;

    public Pair(T first, T last) {
        this.first = first;
        this.last = last;
    }

    public T getFirst() {
        return first;
    }

    public T getLast() {
        return last;
    }
    public void setFirst(T first) {
        this.first = first;
    }
    public void setLast(T last) {
        this.last = last;
    }
}

public class Main {
    public static void main(String[] args) {
        Pair<Number> p = new Pair<>(12, 34);
        p.setFirst(12);

    }
}
