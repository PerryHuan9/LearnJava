package com.learn.base;

import java.beans.*;

/**
 * JavaBean 是将实例属性私有化，然后通过公开的getter和setter方法来访问和修改对象属性的类
 * JavaBean主要用来传输数据
 */
class Person {
    private String name;
    private int age;
    private boolean isTeenagers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isTeenagers() {
        return isTeenagers;
    }

    public void setTeenagers(boolean teenagers) {
        isTeenagers = teenagers;
    }
}


public class TestBeanAndEnum {
    public static void learn() {
        var tb = new TestBeanAndEnum();
        tb.iterateJavaBean();
        tb.testEnum();
    }

    public void iterateJavaBean() {
        // 编译JavaBean的属性
        Person p = new Person();
        try {
            BeanInfo bi = Introspector.getBeanInfo(Person.class);
            for (PropertyDescriptor pd: bi.getPropertyDescriptors()){
                System.out.println(pd.getName());
                System.out.println("\t\t"+pd.getReadMethod());
                System.out.println("\t\t"+pd.getWriteMethod());
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

    }

    /**
     * 枚举类型相当于一个类，每一个常量都是一个静态静态类实例
     * 1、定义的枚举类型总是继承自java.lang.Enum类，并且其不能被继承
     * 2、只能定义出enum的实例，无法通过new进行创建枚举实例
     * 3、定义的每个实例都是引用实例的唯一实例
     */
    enum Weekday {
        SUN, MON, TUE, WED, THU, FRI, SAT;
    }

    /**
     * 在需要较高定制化时，可以为枚举常量实例添加字段
     */
    enum Color {
        RED(10), BLUE(23), YELLO(55), GREEN(66);

        private final int colorValue;

        private Color(int val) {
            this.colorValue = val;
        }
    }



    public void testEnum() {
        Weekday wd = Weekday.MON;
        // 引用类型都需要使用equals比较，但枚举类型除外
        if (wd== Weekday.MON) {
            System.out.println("Today is Monthday.");
        }
        // 枚举类型的每个常量都是枚举实例，故也有方法
        // name() 返回枚举常量的名字，与toString()返回的一致，但name方法不能被重写，是安全的
        // ordinal 返回枚举常量的位置，位置改变会随之改变，不可靠
        System.out.println(wd.name()); // MON
        System.out.println(wd.ordinal()); // 1
        System.out.println(Weekday.SUN.name()); // SUM
        System.out.println(Weekday.SUN.ordinal()); // 0

        Color blue = Color.BLUE;
        if (blue == Color.BLUE) {
            System.out.println("Color is Blue.");
        }
        System.out.println(blue.name());
        System.out.println(blue.ordinal());
        System.out.println(blue.colorValue);


    }
}
