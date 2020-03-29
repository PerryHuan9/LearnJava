package com.learn.regex;


import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {
    public static void learn() {
        TestRegex.base();
        TestRegex.repeatMatch();
        TestRegex.complex();
        TestRegex.group();
        TestRegex.operate();
    }

    public static void log(Object obj) {
        System.out.println(obj);
    }

    /**
     * 正则表达式的匹配规则是从左到右按规则匹配
     */
    public static void base() {
        // 精确匹配
        String regex = "abc";
        log("abc".matches(regex)); // true
        log("abcd".matches(regex)); // false
        log("aabc".matches(regex)); // false
        log("a&c".matches(regex)); //false
        log("a和c".matches("a\\u548cc")); // true
        // . 匹配任意字符
        String any = "a.c";
        log("abc".matches(any)); // true
        log("a^c".matches(any)); // true
        log("aoc".matches(any)); // true
        log("a-c".matches(any)); // true
        log("aa-c".matches(any)); // false
        log("a-cd".matches(any)); // false
        // \d匹配单个数字
        String number = "a\\dc";
        log("a2c".matches(number)); //true
        log("a0c".matches(number)); //true
        log("abc".matches(number)); //false
        // \D 匹配单个非数字字符
        log("\\D");
        String noNum = "a\\Dc";
        log("a2c".matches(noNum)); //false
        log("a0c".matches(noNum)); //false
        log("abc".matches(noNum)); //true
        log("a-c".matches(noNum)); //true
        log("a%c".matches(noNum)); //true
        log("a中c".matches(noNum)); //true
        // \w 匹配一个字母、数字、下划线
        log("\\w");
        String word = "a\\wc";
        log("a2c".matches(word)); //true
        log("abc".matches(word)); //true
        log("a_c".matches(word)); //true
        log("a%c".matches(word)); //false
        log("a中c".matches(word)); //false
        // \W 匹配非\w匹配字符
        log("\\W");
        String noWord = "a\\Wc";
        log("a2c".matches(noWord)); //false
        log("abc".matches(noWord)); //false
        log("a_c".matches(noWord)); //false
        log("a%c".matches(noWord)); //true
        log("a中c".matches(noWord)); //true
        // \s 匹配一个空格（\t匹配为多个空格）
        log("\\s");
        String white = "a\\sc";
        log("a c".matches(white)); //true
        log("a c".matches(white)); //true
        log("a  c".matches(white)); //false
        log("a   c".matches(white)); //false
        log("abc".matches(white)); //false
        // \S 匹配一个非空格字符
        log("\\S");
        String noWhite = "a\\Sc";
        log("a c".matches(noWhite)); //false
        log("a c".matches(noWhite)); //false
        log("abc".matches(noWhite)); //true
        log("a_c".matches(noWhite)); //true
        log("a+c".matches(noWhite)); //true
    }

    /**
     * 重复匹配
     */
    public static void repeatMatch() {
        // * 匹配任意个字符
        log("*");
        String any = "\\d*";
        log("".matches(any)); // true
        log("1".matches(any)); // true
        log("123".matches(any)); // true
        log("123a".matches(any)); // false
        // + 匹配至少一个字符
        log("+");
        String oneMore = "\\w+";
        log("".matches(oneMore)); // false
        log("1".matches(oneMore)); // true
        log("123".matches(oneMore)); // true
        log("123a".matches(oneMore)); // true
        log("123+".matches(oneMore)); // false
        // ? 匹配至多一个字符
        log("?");
        String oneLess = "\\w?";
        log("".matches(oneLess)); // true
        log("1".matches(oneLess)); // true
        log("123".matches(oneLess)); // false
        log("123+".matches(oneLess)); // false
        // 匹配n个字符
        log("{n}");
        String three = "\\w{3}";
        log("".matches(three)); // false
        log("12".matches(three)); // false
        log("123".matches(three)); // true
        log("123+".matches(three)); // false
        // 匹配n-m个字符
        log("{n, m}");
        String oneThree = "\\w{1,3}";
        log("".matches(oneThree)); // false
        log("12".matches(oneThree)); // true
        log("123".matches(oneThree)); // true
        log("123+".matches(oneThree)); // false
        // 匹配至少n个字符
        log("{n, }");
        String twoMore = "\\w{2,}";
        log("".matches(twoMore)); // false
        log("12".matches(twoMore)); // true
        log("123".matches(twoMore)); // true
        log("123+".matches(twoMore)); // false
        // 匹配至多m个字符
        log("{0,m }");
        String twoLess = "\\w{0,2}";
        log("".matches(twoLess)); // true
        log("12".matches(twoLess)); // true
        log("123".matches(twoLess)); // false
        log("123+".matches(twoLess)); // false
    }

    /**
     * 复杂规则
     */
    public static void complex() {
        // ^ 匹配开头 $匹配结尾
        String begin = "^A\\d+";
        log(begin);
        log("A".matches(begin)); // false
        log("A1".matches(begin)); // true
        log("A101".matches(begin)); // true
        log("A808".matches(begin)); // true
        String end = "\\w+(\\.java)$";
        log(end);
        log("main.java".matches(end)); //true
        log("main1.java".matches(end)); // true
        log("main.jaa".matches(end)); // false
        log("main.java2".matches(end)); //false
        String beginEnd = "^[A-C][1-9]$";
        log(beginEnd);
        log("A4".matches(beginEnd)); // true
        log("A0".matches(beginEnd)); // false
        log("D0".matches(beginEnd)); // false
        log("AA".matches(beginEnd)); // false
        // 匹配指定范围
        String scope = "^[A-C][a-z1-9A-Z]+";
        log(scope);
        log("Az1231".matches(scope)); // true
        log("Bccdd1231".matches(scope)); // true
        log("A01231".matches(scope)); // false
        log("Dpp1231".matches(scope)); // false
        // | 或规则匹配
        // 以字母或下划线开头，中间任意个字符，以.class或.java结尾
        String or = "^[a-zA-Z_]\\w*(\\.class|\\.java)$";
        log(or);
        log("a12.class".matches(or));    //true
        log("_AA12.java".matches(or));   // true
        log("__AA12.class".matches(or)); // true
        log("12a12.class".matches(or));  // false
    }


    /**
     * 分组匹配
     */
    public static void group() {
        //分组匹配
        String phone = "(\\d{3,4})-(\\d{6,8})";
        Pattern pattern = Pattern.compile(phone);
        Matcher matcher = pattern.matcher("010-2324432");
        if (matcher.matches()) {
            log(matcher.group(0)); // 010-2324432
            log(matcher.group(1)); // 010
            log(matcher.group(2)); // 2324432
        }
        // 非贪婪模式
        String greed = "(\\d+)(0*)";
        log(greed);
        pattern = Pattern.compile(greed);
        matcher = pattern.matcher("1235000");
        if (matcher.matches()) {
            log(matcher.group(0)); // 1235000
            log(matcher.group(1)); // 1235000
            // 在贪婪模式下规则会匹配尽量多的字符， 所以(\d{3,4})把整个数都匹配
            log(matcher.group(2)); // ''
        }
        String notGreed = "(\\d+?)(0*)";
        log(notGreed);
        pattern = Pattern.compile(notGreed);
        matcher = pattern.matcher("1235000");
        if (matcher.matches()) {
            log(matcher.group(0)); // 1235000
            log(matcher.group(1)); // 1235
            log(matcher.group(2)); // '000'
        }
    }

    public static void operate() {
        // 分隔字符串
        log(Arrays.toString("a ,b;;, c    d;;e".split("[\\,\\;\\s]+")));
        // 搜索字符串
        String str = "the quick brown fox jumps over the lazy dog.";
        Pattern pattern = Pattern.compile("\\wo\\w");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            log(str.substring(matcher.start(), matcher.end()));
        }
        // 替换字符串
        String string = "The     quick\t\t brown   fox  jumps   over the  lazy dog.";
        log(string.replaceAll("[\\s]+", " "));
        // 反向引用
        String s = "the quick brown fox jumps over the lazy dog.";
        log(s.replaceAll("\\s([a-z]{4})\\s", " <span>$1</span> "));

    }
}
