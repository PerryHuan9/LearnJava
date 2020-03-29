package com.learn.functional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestStream {

    /**
     * Stream提供的常用操作有：
     * 转换操作：map()，filter()，sorted()，distinct()；
     * 合并操作：concat()，flatMap()；
     * 并行处理：parallel()；
     * 聚合操作：reduce()，collect()，count()，max()，min()，sum()，average()；
     * 其他操作：allMatch(), anyMatch(), forEach()。
     *
     */
    public static void learn() {
        base();
        functions();
        collect();
    }

    public static void log(Object o) {
        System.out.print(o);
    }

    public static void logln(Object o) {
        System.out.println(o);
    }

    /**
     * Java8不但增加了lambda表达式，还引入了一个全新的流式API：Stream API。
     * 划重点：这个Stream不同于java.io的InputStream和OutputStream，它代表的是任意Java对象的序列。
     * 再次划重点：这个Stream和List也不一样，List存储的每个元素都是已经存储在内存中的某个Java对象，
     * 而Stream输出的元素可能并没有预先存储在内存中，而是实时计算出来的。
     */
    public static void base() {
        // 创建Stream
        Stream<String> stream = Stream.of("Hello", "world", "I", "love", "you");
        stream.forEach(TestStream::log);
        logln("");
        // 基于一个数组或者Collection
        Stream<Integer> stream1 = Arrays.stream(new Integer[]{12, 34, 45, 56, 67});
        Stream<Double> stream2 = List.of(2.34, 12.234, 23.34, 34.345).stream();
        stream1.forEach((val) -> log(val + "\t"));
        logln("");
        stream2.forEach(TestStream::log);
        logln("");
        // 基于Supplier , Supplier保存的是一个生成算法
        Stream<Integer> stream3 = Stream.generate(new Supplier<Integer>() {
            private int n = 0;

            @Override
            public Integer get() {
                return n++;
            }
        });
        stream3.limit(20).forEach(TestStream::logln);
        // 创建Stream的第三种方法是通过一些API提供的接口，直接获得Stream。
        Pattern pattern = Pattern.compile("\\s+");
        Stream<String> stream4 = pattern.splitAsStream("The quick brown fox jumps over the lazy dog");
        stream4.forEach(TestStream::logln);
        /**
         *  因为Java的范型不支持基本类型，所以我们无法用Stream<int>这样的类型，会发生编译错误
         * 为了保存int，只能使用String<Integer>，但这样会产生频繁的装箱、拆箱操作。
         * 为了提高效率，Java标准库提供了IntStream、LongStream和DoubleStream这三种使用基本类型的Stream
         */
        IntStream is = Arrays.stream(new int[]{12, 34, 56});
        is.forEach(TestStream::logln);

    }

    public static void logStream(Stream stream) {
        log("[");
        stream.forEach(o -> log(o + "\t,"));
        logln("]");
    }

    public static void logStream(IntStream stream) {
        log("[");
        stream.forEach(o -> log(o + "\t,"));
        logln("]");
    }

    public static void functions() {
        // map
        Stream<Integer> stream = Stream.of(1, 3, 5, 7, 8, 9);
        stream = stream.map(n -> n * n);
        logStream(stream);
        List.of("  Apple ", " pear ", " ORANGE", " BaNaNa ")
                .stream()
                .map(String::trim) // 去空格
                .map(String::toLowerCase) // 变小写
                .forEach(TestStream::logln); // 打印
        // filter
        IntStream is = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8).filter(n -> n % 2 == 0);
        logStream(is);
        // reduce
        logln(IntStream.of(1, 2, 3, 4, 5, 6, 7, 8).reduce(0, (result, n) -> result + n));
    }

    public static void collect() {
        Stream<String> stream = Stream.of("Apple", "", null, "Pear", "  ", "Orange");
        // 输出为List
        List<String> list = stream.filter(s -> s != null && !s.isBlank()).collect(Collectors.toList());
        logln(list);
        // 输出为数组
        String[] strings = list.stream().toArray(String[]::new);
        logln(Arrays.toString(strings));
        // 输出为Map
        Stream<String> stream2 = Stream.of("APPL:Apple", "MSFT:Microsoft");
        Map<String, String> map = stream2.collect(Collectors.toMap(
                s -> s.substring(0, s.indexOf(":")),
                s -> s.substring(s.indexOf(":") + 1))
        );
        logln(map);
        // 分组输出
        List<String> l = List.of("Apple", "Banana", "Blackberry", "Coconut", "Avocado", "Cherry", "Apricots");
        Map<String, List<String>> groups = l.stream().collect(
                Collectors.groupingBy(s -> s.substring(0, 1), Collectors.toList())
        );
        logln(groups);
    }
}
