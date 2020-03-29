package com.learn.base;


import java.util.Arrays;

public class TestBase {

    public static void learn() {
        TestBase tv = new TestBase();
        tv.learnBaseType();
        tv.integerComputed();
        tv.floatComputed();
        tv.boolComputed();
        tv.charAndString();
        tv.array();
        tv.testArrays();
        System.out.println("**********************************************");
    }

    /**
     * 基本数据类型
     * 整数类型： byte（1）, short（2）, int（4）, long（8）
     * 浮点数类型：float（2）, double（8）
     * 字符类型：char（2）
     * 布尔类型：boolean
     */
    public void learnBaseType() {
        // byte类型支持 -128~127
        byte b = 127;
        // short 类型支持 -32768~32767
        short s = 32767;
        // 整数类型支持 -2147483648~2147483647
        int i = 2147483647;
        // 允许加下划线使其更容易识别
        int i1 = 21_0000_0000;
        // 使用16进制表示
        int i2 = 0xffffff12;
        // 使用2进制表示
        int i3 = 0b101111;
        // 使用8进制表示
        int i4 = 0344547;
        // long类型的结尾要加L
        long l = 1213l;
        long l1 = 1213L;
        System.out.println("byte:" + b + "；\nshort:" + s + "；\nint:" + i + ',' + i1 + ',' + i2 + ',' + i3 + ',' + i4 + "；\nlong:" + l + ',' + l1);

        // 浮点数即为小数，因为小数使用科学计数法表示的时候，小数点是可以“浮动”的，
        // 如1234.5可以表示成12.345x102，也可以表示成1.2345x103，所以称为浮点数。
        // 默认不加后缀的小数都是double类型，float类型尾部需加f
        // float 类型最大可以表示3.4e38,double最大表示1.79e308
        float f = 123.34f;
        float f1 = 3.24e38f;
        double d = 768.34;
        double d1 = 3.14e-324;
        System.out.println("float:" + f + "," + f1 + ";\ndouble:" + d + "," + d1);

        // 布尔类型,理论上存储布尔类型只需要1bit，但是通常JVM内部会把boolean表示为4字节整数
        boolean bool = true;
        boolean bool1 = false;
        System.out.println("boolean:" + bool + "," + bool1);

        // char类型, Java char类型除了可表示标准的ASCII外，还可表示一个Unicode字符
        char c = 'A';
        char c1 = '中';
        System.out.println("char:" + c + "," + c1);

        // 定义变量时加上final修饰符就会变成常量
        final int fi = 1231;
        // fi = 1231; error, 常量不能为重新赋值

        // var关键字,使你可以省略类型声明，交由编译器推导
        var a = 1231;
        System.out.println("var:" + a);
    }

    /**
     * 整数运算：绝对精确，永远精确
     */
    public void integerComputed() {
        //1、 普通运算
        int n = 888;
        int b = 333;
        int result = n + b * 2;
        System.out.println("result:" + result);
        int d = 7 / 2;
        System.out.println(d); // 3
        // 结果为小数
        float f = 7 / 2f;
        System.out.println(f);
        double dou = 7 / 2.0;
        System.out.println(dou);
        // 当结果超过类型最大值时会产生溢出, 溢出并不会报错，但会产生奇怪的结果
        int maxVal = Integer.MAX_VALUE;
        int overflow = maxVal + 1;
        System.out.println("overflow:" + overflow); //-2147483648
        // 使用long类型来接纳结果便不会溢出
        long l = maxVal + 1l;
        System.out.println("normal:" + l);

        // ++,--,+=,-=,*=,/=
        var v = 100;
        v++;
        System.out.println(v);// 101
        v--;
        System.out.println(v);//100
        v += 88;
        System.out.println(v); // 188
        v -= 22;
        System.out.println(v);// 166
        v /= 2;
        System.out.println(v); // 83
        v *= 2;
        System.out.println(v); // 166

        //2、移位运算：对byte、short进行移位运算会将其转为int
        // << 时会将符号位带上一起左移，因此有可能出现负数变左移之后变正数的现象
        // 左移实际上就是不断地×2，右移实际上就是不断地÷2
        int val = 21_0000_0000;
        int mvVal = val << 1;
        System.out.println(mvVal); //-94967296
        int val2 = -21_0000_0000;
        System.out.println("hello:"+Integer.toBinaryString(val2));
        System.out.println("hello2:"+Integer.toBinaryString(val2<<8));
        System.out.println("hello3:"+Integer.toBinaryString(val2<<9));
        System.out.println("hello4:"+Integer.toBinaryString(val2<<11));
        System.out.println(val2);
        // >> 右移时不带上符号位
        int test = 1 >> 1;
        int test2 = -2 >> 1;
        System.out.println(test); // 0
        System.out.println(test2); // -1
        // 位移long类型数据
        long l2 = 12_0000_0000_0000L;
        long l3 = l2 >> 1;
        System.out.println(l3);
        // >>> 带符号位右移
        int i5 = -21_0000_0000 >>> 2;
        System.out.println(i5);

        // 3、位运算 &,|,~,^
        int bit = 11 & 112;
        int bit2 = 11 | 10;
        int bit3 = ~11;
        System.out.println("11 & 112:" + bit);
        System.out.println(" 11 | 10;" + bit2);
        System.out.println("~11:" + bit3); // -12（需要注意的是负数在计算机中是存储其补码的）
        int bit4 = 1 ^ 1;
        int bit5 = 1 ^ 0;
        int bit6 = 0 ^ 1;
        int bit7 = 0 ^ 0;
        System.out.println("1^1:" + bit4); // 0
        System.out.println("1^0:" + bit5); // 1
        System.out.println("0^1:" + bit6); // 1
        System.out.println("0^0:" + bit7); // 0


        // 4、类型自动提升&强制转型
        // 在运算过程中，如果参与运算的两个数类型不一致，那么计算结果为较大类型的整型。
        // 例如，short和int计算，结果总是int，原因是short首先自动被转型为int：
        int s = 1231;
        short ss = 123;
        int re = s + ss;
        System.out.println(re);

        // 如果要讲一个类型强制转为另外一个类型可以使用强制类型转换
        // 需要注意的是强制类型转换有可能出现错误的结果


    }

    /**
     * 浮点数完全遵从IEEE-754规范，浮点数无法精确表示，存在运算误差
     */
    public void floatComputed() {
        // 并不是所有的浮点数都不能精确表示，能转换成有限个小数的浮点数是可以精确表示的
        double d = 0.5; // 0.5可以精确表示，其二进制表示为0.1
        double d1 = 0.1; // 0.1不能精确表示，其转成二进制是个无限小数
        System.out.println(d);
        System.out.println(d1);
        double x = 1.0 / 10;
        double y = 1 - 9.0 / 10;
        System.out.println("1.0 / 10 = " + x);
        System.out.println("1 - 0.9/10 = " + y);

        // 由于浮点数计算存在误差，所以比较两个浮点数是否相等时往往会出错，
        // 比较正确的方法可以判断两个数的差是否小于一个很小的数
        if (Math.abs(x - y) < 0.00001) {
            System.out.println("x == y");
        } else {
            System.out.println("x != y");
        }

        // 当参与的两个数中有一个数是浮点数时，如另外一个数为整型时，其会自动提升为浮点型
        int n = 88;
        double re = 5 + n / 1.0;
        System.out.println(re);
        // 需要注意的是，当在复杂的四则运算时，两个整数之间的运算不会存在类型提升
        double a = 6.6 + 5 / 2;
        System.out.println(a); //8.6

        // 整数运算在除数为0时会报错，而浮点数运算在除数为0时，不会报错，但会返回几个特殊值
        // NaN表示Not a Number
        // Infinity表示无穷大
        // -Infinity表示负无穷大
        double d5 = 0.0 / 0; // NaN
        double d6 = 1.0 / 0; // Infinity
        double d7 = -1.0 / 0; // -Infinity
        System.out.println("0.0 / 0 = " + d5);
        System.out.println("1.0 / 0 = " + d6);
        System.out.println("-1.0 / 0 = " + d7);

    }


    /**
     * 布尔运算的特点是短路运算
     * 比较运算符：>，>=，<，<=，==，!=
     * 与运算 &&
     * 或运算 ||
     * 非运算 !
     * <p>
     * 关系运算符的优先级从高到低依次是：
     * * !
     * * >，>=，<，<=
     * * ==，!=
     * * &&
     * * ||
     */
    public void boolComputed() {
        boolean b = 12 > 34;
        boolean b1 = b == true;
        int age = 18;
        boolean isTeenager = age > 6 && age < 18; // true

        // 布尔运算的一个重要特点是短路运算。
        // 如果一个布尔运算的表达式能提前确定结果，则后续的计算不再执行，直接返回结果。
        boolean b2 = 5 < 3;
        boolean result = b2 && (5 / 0 > 0); // 因为b2为true，所以不会计算后面的表达式，因此不会报错
        System.out.println(result);

        // 三元运算符
        int value = 666 > 888 ? 666 : 888;
        System.out.println(value);
    }

    /**
     * 字符 Vs. 字符串
     * 字符类型是基本类型，字符串是引用类型
     * 常见的转义字符包括：
     * \" 表示字符"
     * \' 表示字符'
     * \\ 表示字符\
     * \n 表示换行符
     * \r 表示回车符
     * \t 表示Tab
     * \u1234 表示一个Unicode编码的字符
     */
    public void charAndString() {
        // 一个char字符表示一个Unicode字符，所以一个中文也能用char表示
        char a = 'a';
        char a1 = '凛';
        // 如要显示字符的Unicode编码，只需将其赋值给int
        int i = a;
        int i1 = a1;
        System.out.println(i); // 97
        System.out.println(i1); // 20955
        // 还可以使用unicode码表示字符
        char c3 = '\u0041'; // 'A'，因为十六进制0041 = 十进制65
        char c4 = '\u4e2d'; // '中'，因为十六进制4e2d = 十进制20013
        System.out.println(c3);
        System.out.println(c4);

        // 字符串
        String str = "hello word";
        String str1 = "Dou you know \"NEW York\"";
        // 字符串连接: 其它任意类型与字符串相加时，都会先转成字符串，然后进行拼接
        String str3 = str + str1;
        System.out.println(str3);
        // java13可以使用多行字符串
//        String str4 = """
//               静夜思
//             床前明月光，
//             疑是地上霜。
//             举头望明月，
//             低头思故乡。
//        """;
//        System.out.println(str4);
        // 字符串具有不可变性
        String s = "hello";
        String t = s;
        s = "world";
        System.out.println(t); // t是"hello"

        // 引用类型的变量可以指向一个空值null，它表示不存在，即该变量不指向任何对象
        // 引用类型变量在未初始化时未null
        String string = null;
        String string2; // null
        String string3 = string;
        System.out.println(string);
        System.out.println(string3);

    }


    /**
     * 数组类型
     * 1、数组是引用类型
     * 2、一旦创建，长度不可变
     * 3、数组所有元素初始化为默认值，整型都是0，浮点型是0.0，布尔型是false
     */
    public void array() {
        int[] arr = new int[5];
        arr[2] = 888;
        System.out.println(Arrays.toString(arr));
        // 创建时对值进行初始化
        double[] arr1 = new double[]{0.1, 0.2};
        System.out.println(Arrays.toString(arr1));
        // 不指定长度，交由其自动推导
        boolean[] arr2 = {true, false, true};
        System.out.println(Arrays.toString(arr2));
        // 对数组的访问不能超出其索引
//       arr[5] = 123; // 报错， arr的索引时0-4
        // 字符串数组
        String[] strs = {"hello", "word", "!"};
        for (String s : strs) System.out.print(s);
        int[][] a = new int[10][10];
    }

    /**
     * Arrays 是数组操作的工具类
     */
    public void testArrays() {
        System.out.println();
        String[] strs = new String[] {"I", "am", "a", "good", "man"};
        // 打印一维数组
        System.out.println(Arrays.toString(strs));
        int[][] a = new int[][]{{1, 2, 3}, {4, 5, 6}};
        var b = new double[2][3][4];
        // 打印多维数组
        System.out.println(Arrays.deepToString(a));
        System.out.println(Arrays.deepToString(b));
        // 排序
        var arr = new int[]{23,12,1,567,232,1234,546};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

    }
}
