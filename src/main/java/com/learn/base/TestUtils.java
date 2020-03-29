package com.learn.base;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class TestUtils {
    public static void learn() {
        var tu = new TestUtils();
        tu.math();
        tu.random();
        tu.secureRandom();
    }

    /**
     * java提供了Math类进行数学计算
     * 另有一个StrictMath提供了和Math几乎一模一样的方法。
     * 这两个类的区别在于，由于浮点数计算存在误差，不同的平台（例如x86和ARM）计算的结果可能不一致（指误差不同）
     * 因此，StrictMath保证所有平台计算结果都是完全相同的，而Math会尽量针对平台优化计算速度，
     */
    public void math() {
        int a = -888;
        int b = -999;
        System.out.println(Math.abs(a));
        System.out.println(Math.max(a, b));
        System.out.println(Math.min(1.5, 8.8));
        System.out.println(Math.pow(2, 10));
        System.out.println(Math.sqrt(4)); // 开平方根
        System.out.println(Math.exp(5)); // e的5次方
        System.out.println(Math.log(4)); // 求e为底的2的对数
        System.out.println(Math.log10(10)); // 求10为底的10的对数
        System.out.println(Math.sin(3.14));
        System.out.println(Math.cos(3.14));
        System.out.println(Math.asin(3.14));
        System.out.println(Math.PI);
        System.out.println(Math.E);
        for (int i=0;i< 10; i++) {
            System.out.print(Math.random()+"\t"); // Math.random()每次都返回一个0<n<1的随机数
        }
        System.out.println();
    }

    /**
     * Random用于生成伪随机序列
     * 当给定种子时，每次生成的序列都是一样的，
     * 当不给种子时，会使用系统当前的时间戳作为种子，这样生成的序列每次都是不一样的
     *
     */
    public void random() {
        // 种子为10
        Random r = new Random(10);
        for(int i=0;i<5;i++) {
            // 范围为0-10
            System.out.print(r.nextInt(10) + "\t"); // 3	0	3	0	6
        }
        System.out.println();
        // 种子为10
        Random r2 = new Random(10);
        for(int i=0;i<5;i++) {
            System.out.print(r2.nextInt(10) + "\t"); // 3	0	3	0	6
        }
        System.out.println();
        for(int i=0;i<5;i++) {
            System.out.print(r.nextDouble() + "\t"); // 0.672159466804821	0.36817039279355135	0.35676463500575895	0.652536330465718	0.8922895448652606
        }
        System.out.println();
        for(int i=0;i<5;i++) {
            System.out.print(r2.nextDouble() + "\t"); // 0.672159466804821	0.36817039279355135	0.35676463500575895	0.652536330465718	0.8922895448652606
        }

    }

    /**
     * 有伪随机数，就有真随机数。实际上真正的真随机数只能通过量子力学原理来获取，而我们想要的是一个不可预测的安全的随机数，
     * SecureRandom就是用来创建安全的随机数的
     * JDK的SecureRandom实际上有多种不同的底层实现
     *
     */
    public void secureRandom() {
        System.out.println();
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstanceStrong(); // 先使用高强度的安全随机数生成器
        } catch (NoSuchAlgorithmException e) {
            sr = new SecureRandom(); // 不存在高强度的则使用普通的
        }
        byte[] buffer = new byte[16];
        sr.nextBytes(buffer); // 用安全随机数填充buffer
        System.out.println(Arrays.toString(buffer));
        System.out.println(sr.nextInt(100));
    }


}
