package com.learn.base;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.StringJoiner;

public class TestString {
    public static void learn() {
        System.out.println("************************");
        var ts = new TestString();
        ts.stringCompare();
        ts.stringMethods();
        ts.stringBuilder();
        ts.stringJoiner();
    }

    public void stringCompare() {
        String h1 = "hello";
        String h2 = "hello";
        System.out.println(h1 == h2); // true ,纯属巧合，编译器在编译的时候把相同的字符串当成一个对象放到常量池中
        System.out.println(h1.equals(h2));// true
        String h3 = "HELLO";
        System.out.println(h1 == h3.toLowerCase());  // false
        System.out.println(h1.equals(h3.toLowerCase())); // true
        System.out.println(h1.equalsIgnoreCase(h3)); // true 忽略大小写比较
    }

    public void stringMethods() {
        String s = "hello word";
        System.out.println(s.contains("ll")); // true
        System.out.println(s.indexOf("w")); // 6
        System.out.println(s.lastIndexOf("w")); // 3
        System.out.println(s.startsWith("he")); // true
        System.out.println(s.endsWith("rd")); // true
        System.out.println(s.substring(4)); // o word
        System.out.println(s.substring(4, 7)); // o_w
        // 首尾去空
        System.out.print("\thello\u3000\r\n".trim()); // 去除首尾空白字符（无法去除中文空格\u3000）
        System.out.print("123");
        System.out.print("\u3000\t您好！\u3000\r\n".strip()); //  去除首尾空白字符（包括中文空格\u3000）
        // 判空
        System.out.println("".isEmpty()); // 是否为长度为0的空字符串
        System.out.println("".isBlank()); // true 是否只包含空白字符
        System.out.println("  ".isBlank());  //true
        System.out.println("\u3000".isBlank()); // true
        // 替换子串
        System.out.println(s.replace("h", "H")); // 替换字符串
        System.out.println("A,,B;C,,,D".replaceAll("[\\,\\;\\s]+", ","));
        // 分隔字符
        System.out.println(Arrays.toString("12,34,45,67;89".split("[//,//;]")));
        // 字符串数组拼接
        String[] arr = {"天", "理", "难", "容"};
        System.out.println(String.join("", arr));
        // 类型转换
        System.out.println(String.valueOf(10));
        System.out.println(String.valueOf(true));
        System.out.println(String.valueOf(45.88));
        System.out.println(String.valueOf(new Object()));
        System.out.println(Integer.parseInt("20"));
        System.out.println(Double.parseDouble("35.88"));
        System.out.println(Boolean.parseBoolean("true"));
        // 要特别注意，Integer有个getInteger(String)方法，
        // 它不是将字符串转换为int，而是把该字符串对应的系统变量转换为Integer
        System.out.println(Integer.getInteger("java.version")); // null 无此系统变量

        // 转换为char数组
        System.out.println(Arrays.toString("hello".toCharArray()));
        try {
            System.out.println(Arrays.toString(s.getBytes()));
            System.out.println(Arrays.toString(s.getBytes("GBK")));
            System.out.println(Arrays.toString(s.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    /**
     * 使用+符号就行字符串拼接时，在多次拼接时会创建多个临时对象，java提供了StringBuilder来进行高效操作
     * StringBuffer与StringBuilder的接口完全一样，只不过StringBuffer是线程安全的
     */
    public void stringBuilder() {
        StringBuilder sb = new StringBuilder(1024);
        String[] ss = {"乾坤", "无极", "，", "大道", "三六"};
        for (String s : ss) {
            sb.append(s);
        }
        sb.insert(0, "无名氏曰: ").append("。");
        System.out.println(sb.toString());
    }

    /**
     * String.join(arr)可以使用分隔符来拼接字符串数组，
     * 但在需要指定首尾的时候可以使用StringJoiner
     */
    public void stringJoiner() {
        String[] ss = {"乾坤", "无极", "，", "大道", "三六"};
        StringJoiner sj = new StringJoiner(" ++ ", " --> ", " <-- ");
        for(String s: ss) {
            sj.add(s);
        }
        System.out.println(String.join(" ++ ",ss));
        System.out.println(sj.toString());
    }
}
