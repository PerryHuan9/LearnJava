package com.learn.log;

public class Main {

    public static void main(String[] args) {
        System.out.println("**************logging****************");
        TestLogging.learn();
        System.out.println("**************Commons Logging********");
        TestCommonsLogging.learn();
        System.out.println("***************Slf4j*****************");
        TestSlf4j.learn();

    }
}
