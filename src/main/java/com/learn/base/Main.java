package com.learn.base;


import java.util.Arrays;

public class Main {
    /**
     *
     * @param args 用户传入的命令行参数
     */
    public static void main(String[] args) {
        System.out.println("命令行参数是:"+ Arrays.toString(args));
        TestBase.learn();
//        TestProcessControl.learn();
        TestModule.learn();
        TestString.learn();
        TestBoxType.learn();
        TestBeanAndEnum.learn();
        TestBigNumber.learn();
        TestUtils.learn();
    }

}
