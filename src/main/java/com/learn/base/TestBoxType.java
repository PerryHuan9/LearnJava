package com.learn.base;

public class TestBoxType {
    public static void learn() {
        var tbt = new TestBoxType();
        tbt.base();
        tbt.compare();
        tbt.transform();
        tbt.unsigedInt();
    }

    /**
     * 每一个基本类型都会对应衣蛾包装类型,包装类型是把基本类型包装成引用类型
     * 包装类型的值是不能修改, 整数与浮点数的包装类型都继承自Number类型
     * boolean	java.lang.Boolean
     * byte	    java.lang.Byte
     * short	java.lang.Short
     * int	    java.lang.Integer
     * long	    java.lang.Long
     * float	java.lang.Float
     * double	java.lang.Double
     * char	    java.lang.Character
     */
    public void base() {
        Integer i = new Integer(12);
        Integer i1 = Integer.valueOf(34);
        Integer i3 = Integer.valueOf("88");
        System.out.println(i.intValue()); // 返回值
        // java编译器会自动装箱和自动拆箱
        Integer n = 888; // 888自动装箱成Integer
        System.out.println(n.intValue());
        int result = n + 999; // n自动拆箱
        System.out.println(result);
        // 装箱和拆箱会影响代码的执行效率，因为编译后的class代码是严格区分基本类型和引用类型的。
        // 并且，自动拆箱执行时可能会报NullPointerException
        Integer n1 = null;
        // int i5 = n1; // 报错
    }

    /**
     * 为了节省内存，Integer.valueOf()对于较小的数会返回相同的实例，这就是为什么i==i2
     */
    public void compare() {
        Integer i = 127; // 编译器会自动调用Integer.valueOf()
        Integer i2 = 127;
        Integer n1 = 99999;
        Integer n2 = 99999;
        System.out.println("i == i2 = "+(i==i2)); // true 纯属巧合
        System.out.println("i == i2 = "+(n1==n2)); // false
        System.out.println(i.equals(i2)); // true
        System.out.println(n1.equals(n2)); // true
    }

    public void transform() {
        Integer n = 888;
        System.out.println(Integer.toString(n, 9));
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toOctalString(n));
        System.out.println(Integer.toHexString(n));
        // 所有的整数和浮点数的包装类型都继承自Number，
        // 因此，可以非常方便地直接通过包装类型获取各种基本类型
        Number num = 999;
        System.out.println(num.intValue());
        System.out.println(num.floatValue());
        System.out.println(num.doubleValue());
        System.out.println(num.byteValue());
        System.out.println(num.shortValue());
        System.out.println(num.longValue());
    }

    /**
     * 无符号整形
     */
    public void unsigedInt() {
        byte b = -1;
        byte b1 = 127;
        System.out.println(Byte.toUnsignedInt(b));
        System.out.println(Byte.toUnsignedInt(b1));
        int i = -899;
        int i1 = 888;
        System.out.println(Integer.toUnsignedLong(i));
        System.out.println(Integer.toUnsignedLong(i1));
    }

}
