package com.learn.junit;

public class Throws {

    public static void doThrow(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("参数错误");
        } else  {
            System.out.println("正常");
        }
    }

}
