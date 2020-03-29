package com.learn.base;

import java.util.Scanner;

public class TestProcessControl {
    public static void learn() {
        var tpc = new TestProcessControl();
//        tpc.inOut();
        tpc.branchStructure();
    }

    /**
     * 输入&输出
     */
    public void inOut() {
        // 换行输出
        System.out.println("我会换行");
        System.out.print("我不会换行");
        // 格式化输出
        double i = 290000000;
        System.out.println(i); // 2.9E8
        /**
         * Java的格式化功能提供了多种占位符，可以使用printf把各种数据类型“格式化”成指定的字符串：
         * 占位符	说明
         * %d	格式化输出整数
         * %x	格式化输出十六进制整数
         * %f	格式化输出浮点数
         * %e	格式化输出科学计数法表示的浮点数
         * %s	格式化字符串
         */
        System.out.printf("%d\n", 23);
        System.out.printf("%.2f\n", 23.58901); // 23.59
        System.out.printf("%x\n", 189); // bd

        // 键盘输入
        Scanner scanner = new Scanner(System.in);
        // 输入一段字符串
        System.out.print("Please Input your name:");
        String name = scanner.nextLine();
        System.out.print("Please Input your age:");
        int age = scanner.nextInt();
        System.out.printf("Your Info is:\n\tName: %s\n\tAge：%d", name, age);
    }

    /**
     * 分支结构
     * if & switch
     */
    public void branchStructure() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n请输入年龄：");
        int age = scanner.nextInt();
        String type = "";
        if (age < 0) {
            throw new Error("错误输入");
        } else if (age < 7) {
            type = "童年";
        } else if (age < 18) {
            type = "少年";
        } else if (age < 35) {
            type = "青年";
        } else if (age < 60) {
            type = "中年";
        } else {
            type = "老年";
        }
        System.out.println("你是一个" + type + "人");
        System.out.println("\n请在下面几项中选择一项:");
        System.out.println("A. 人不风流枉少年");
        System.out.println("B. 一寸光阴，一寸金");
        System.out.println("C. 人生得意须尽欢");
        scanner.nextLine(); // 用于接收上一个输入的 \n1
        String choice = scanner.nextLine();
        switch (choice) {
            case "A":
                System.out.println("你是一个风流的人");
                break;
            case "B":
                System.out.println("你是一个珍惜时间的人");
                break;
            case "C":
                System.out.println("你是一个乐于享受的人");
                break;
            default:
                System.out.println("Not this Choice.");
        }
        // java12加入了表达式语法
//        String fruit = "apple";
//        switch (fruit) {
//            case "apple" -> System.out.println("Selected apple");
//            case "pear" -> System.out.println("Selected pear");
//            case "mango" -> {
//                System.out.println("Selected mango");
//                System.out.println("Good choice!");
//            }
//            default -> System.out.println("No fruit selected");
//        }

    }

    interface Hello {
        int a = 12;
    }

    class  My implements Hello {
        public void todo() {
            System.out.println(My.a);
        }
        class  Hello2 {

        }
    }

    public void test() {
        var test = new My();
    }
//    Hello.a



}
