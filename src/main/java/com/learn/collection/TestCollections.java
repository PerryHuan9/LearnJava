package com.learn.collection;

import java.util.*;

public class TestCollections {
    /**
     * Collections是有关于容器操作的工具集
     */
    public static void learn() {
        var tc = new TestCollections();
        tc.funs();
    }

    public void funs() {
        // 创建空集合 要注意到返回的空集合是不可变集合，无法向其中添加或删除元素。
        List l = Collections.emptyList();
        Set set = Collections.emptySet();
        Map map = Collections.emptyMap();
        // 创建单元素集合
        l = Collections.singletonList('1');
        map = Collections.singletonMap("Perry", "Huang");
        set = Collections.singleton("黄益凛");
        // 排序
        List list = new ArrayList();
        list.add("apple");
        list.add("pear");
        list.add("orange");
        // 排序前:
        System.out.println(list);
        Collections.sort(list);
        // 排序后:
        System.out.println(list);
        // 洗牌后
        Collections.shuffle(list);
        System.out.println(list);
        // 封装成不可变集合
        list = Collections.unmodifiableList(list);
//        list.add(123); //会报错
        // 封装成线程安全集合
        list = Collections.synchronizedList(list);





    }
}
