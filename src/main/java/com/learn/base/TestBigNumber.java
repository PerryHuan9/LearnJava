package com.learn.base;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TestBigNumber {
    public static void learn() {
        var tbn = new TestBigNumber();
        tbn.bigInteger();
        tbn.bigDecimal();
    }

    /**
     * java提供的最大的整数的范围是64位， 为long类型,其可以通过cpu指令进行计算，速度非常快
     * 对于大于64位的数据，只能通过软件进行模拟， java提供了java.math.BigInteger进行模拟
     * BigInteger也继承自Number类
     *
     */
    public void bigInteger() {
        BigInteger bi = new BigInteger("123131232244334");
        System.out.println(bi.pow(5));
        Number bi2 = new BigInteger("213242342342134234");
        System.out.println(bi.add((BigInteger) bi2));
        System.out.println(bi2.longValue()); // 转为long类型， 会丢失高位信息
        System.out.println(bi.shortValue());
        System.out.println(((BigInteger) bi2).longValueExact()); // 精确装换，超出范围会报错
//        System.out.println(bi.shortValueExact()); // java.lang.ArithmeticException: BigInteger out of short range
    }

    /**
     * 相对于BigInteger，浮点数也提供了用于精确运算的BigDecimal，BigDecimal不会丢失精度，但运算速度慢
     *
     */
    public void bigDecimal() {
        BigDecimal bd = new BigDecimal("1231243354.3455646767");
        BigDecimal bd2 = new BigDecimal("1231243354.344565473232");
        System.out.println(bd.add(bd2));
        System.out.println(bd.pow(56));
        System.out.println(bd.longValue());
        System.out.println(bd.doubleValue());
    }

}
