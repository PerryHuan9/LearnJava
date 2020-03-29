package com.learn.functional;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestLambda {
    public static void learn() {
        base();
    }

    public static void log(Object o) {
        System.out.println(o);
    }


    /**
     * 只有一个抽象方法的接口叫做FunctionalInterface， 用注解@FunctionalInterface标记
     * FunctionalInterface允许传入：
     * 接口的实现类（传统写法，代码较繁琐）；
     * Lambda表达式（只需列出参数名，由编译器推断类型）；
     * 符合方法签名的静态方法；
     * 符合方法签名的实例方法（实例类型被看做第一个参数类型）；
     * 符合方法签名的构造方法（实例类型被看做返回类型）。
     */
    public static void base() {
        String[] strs = {"hello", "love", "name", "mine"};
        log(Arrays.toString(strs));
        // 传入lambda表达式
        Arrays.sort(strs, (a, b) -> b.compareTo(a));
        log(Arrays.toString(strs));
        // 传入静态方法的引用
        Arrays.sort(strs, TestLambda::compareTo);
        log(Arrays.toString(strs));
        // 构造方法的引用
        List<String> list = List.of(strs);
        List<Person> personList = list.stream().map(Person::new).collect(Collectors.toList());
        log(personList);


    }

    public static int compareTo(String s1, String s2) {
        return s1.compareTo(s2);
    }
}


class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String toString() {
        return "Person:" + this.name;
    }
}


