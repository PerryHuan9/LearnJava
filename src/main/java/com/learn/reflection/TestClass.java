package com.learn.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;

public class TestClass {

    public static void learn() {
        TestClass tc = new TestClass();
        tc.testGetClass();
        tc.testField();
    }

    /**
     * JVM在加载某个类或接口的时候，首先会加载改类或接口的.class文件，
     * 根据该文件创建一个对应的Class类的实例，该实例包含该类的所有信息.
     * 1、通过Class实例获取class信息的方法叫做放射。
     * 2、每个类型都有对应的Class实例，基本类型也不例外
     * 3、JVM具有动态加载的特性，JVM在执行程序时并不是一次性将所有的类型都加载进内存，
     * 而是第一次需要用到时才会加载。利用这个特性可以做一些事情，如根据条件加载不同的类。
     */
    public void testGetClass() {
        // 获取一个类型的class实例有以下三种方式
        Class cla1 = TestClass.class;
        Class cla2 = new TestClass().getClass();
        Class cla3 = null;
        try {
            cla3 = Class.forName("com.learn.reflection.TestClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 一个类只可能存在一个Class实例，故以上三种获取的Class实例其实是同一个
        System.out.println(cla1 == cla2); // true
        System.out.println(cla3 == cla2); // true
        // 根据Class实例可以创建对应类的实例, 只能使用对应类的无参public构造器创建实例
        try {
            TestClass tc = (TestClass) cla1.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // 用instanceOf来判断某个对象是否为某个类型的实例
        Integer i = Integer.valueOf(888);
        System.out.println(i instanceof Integer); // true
        System.out.println(i instanceof Number); // true
        // instanceOf对于父类的判断也为true，如果要判断某个实例为确切的某个类的实例，
        // 可以Class实例判断
        System.out.println(i.getClass() == Integer.class); //true
//        System.out.println(i.getClass() == Number.class); // false

        // 打印Class实例的基本信息
        printClassInfo(int.class);
        printClassInfo(float.class);
        printClassInfo(Integer.class);
        printClassInfo(Number.class);
        printClassInfo(Runnable.class);
        printClassInfo(String.class);
        printClassInfo(String[].class); // String[]也是一个类型。并且其和String不一致

    }

    public static void printClassInfo(Class cls) {
        System.out.println("Class name: " + cls.getName());
        System.out.println("Simple name: " + cls.getSimpleName());
        if (cls.getPackage() != null) {
            System.out.println("Package name: " + cls.getPackage().getName());
        }
        System.out.println("is interface: " + cls.isInterface());
        System.out.println("is enum: " + cls.isEnum());
        System.out.println("is array: " + cls.isArray());
        System.out.println("is primitive: " + cls.isPrimitive());
        System.out.println("************************************");
    }


    public void testField() {
        Class cls = Student.class;
        try {
            Field name = cls.getField("name"); // 获取某个publish field(包括继承的)
            System.out.println(name);
//            Field f1 =cls.getField("sex"); // 无法获取非publish属性，
//            System.out.println(f1);
            Field id = cls.getDeclaredField("id");
            System.out.println(id); // 获取指定属性（不包括继承的属性）
            System.out.println(Arrays.toString(cls.getFields())); // [isTeenager, name, age]
            System.out.println(Arrays.toString(cls.getDeclaredFields())); // [isTeenager, id]
            System.out.println(id.getName());
            System.out.println(id.getType());
            System.out.println(id.getName());


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }



}

class Person {
    public String name;
    public int age;
    private String sex;
}
class Student  extends Person{
    public final boolean isTeenager = true;
    protected String id;
}
