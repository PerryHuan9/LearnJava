package com.learn.junit;

public class Utils {

    public static int abs(int i) {
        return  Math.abs(i);
    }

    public static String capitalize(String s) {
        if (s.length() == 0) {
            return s;
        }
        return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }

}
