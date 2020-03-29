package com.learn.collection;

import java.util.*;

public class TestMap {
    public static void learn() {
        TestMap tm = new TestMap();
        tm.base();
        tm.enumMap();
        tm.equalsNhashCode();
        tm.treeMap();
    }

    public static void log(Object o) {
        System.out.println(o);
    }

    public static <K, V> void logMap(Map<K, V> map) {
        log("{");
        for (Map.Entry<K, V> entry : map.entrySet()) {
            log("\t" + entry.getKey() + " : " + entry.getValue());
        }
        log("}");
    }

    /**
     * 因为HashMap是一种通过对key计算hashCode()，通过空间换时间的方式，
     * 直接定位到value所在的内部数组的索引，因此，查找效率非常高。
     */
    public void base() {
        Map map = new HashMap();
        map.put("apple", "苹果");
        map.put("pear", "雪梨");
        logMap(map);
        map.put("apple", "富士山苹果");
        logMap(map);
        // 根据keySet遍历
        Set set = map.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            Object key = iterator.next();
            log(key + ":" + map.get(key));
        }
    }

    /**
     * Map是根据equals判断是否为同一个key，及只要equals为true即认为是同一个key，
     * Map内部使用一个数组保存value，而使用key的hashCode作为数组的索引，
     * 所以在使用自定义对象作为key时，应该正确覆写equals和hashCode
     * hashCode方法应该严格遵循以下两条规则：
     * 1、如果两个对象相等，则它们的hashCode一定相等
     * 2、如果两个对象不相等，那它们的hashCode尽量不相等
     */
    public void equalsNhashCode() {
        Map<Person, String> map = new HashMap<>();
        map.put(new Person(), "一个无参的Person对象");
        log(map.get(new Person())); // 一个无参的Person对象


    }

    /**
     * 在key是enum类型的情况下，应该使用EnumMap
     * EnumMap，它在内部以一个非常紧凑的数组存储value，并且根据enum类型的key直接定位到内部数组的索引，
     * 并不需要计算hashCode()，不但效率最高，而且没有额外的空间浪费。
     */
    public void enumMap() {
        Map map = new EnumMap(Weekday.class);
        map.put(Weekday.MON, "我是人间惆怅客，知君何事泪纵横");
        map.put(Weekday.SAT, "众里寻他千百度， 蓦然回首，那人却在灯火阑珊处");
        logMap(map);
    }

    /**
     * 遍历HashMap的键时，其顺序是不可预测的，
     * Java提供了一个根据键排序的接口SortedMap，其实现类为TreeMap
     * TreeMap的键得实现Comparable，否则应该在创建TreeMap时指定排序算法
     */
    public void treeMap() {
        // String已实现了Comparable接口
        TreeMap<String, String> tm = new TreeMap<>();
        tm.put("oppo", "oppo手机");
        tm.put("huawei", "华为手机");
        tm.put("xiaomi", "小米手机");
        logMap(tm); // 顺序会是 huawei oppo xiaomi
        TreeMap<Person, String> map = new TreeMap<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.age, o2.age);
            }
        });
        map.put(new Person("Perry", "Huang", 24),"PerryHuang" );
        map.put(new Person("Perry2", "Huang", 28),"PerryHuang2" );
        map.put(new Person("Perry3", "Huang", 20),"PerryHuang3" );
        map.put(new Person("Perry4", "Huang", 18),"PerryHuang4" );
//        无法存储具有相同比较值的key， 此处age是比较值，故对象的age不能相同
//        map.put(new Person("Perry5", "Huan5", 18),"PerryHuang4" );
        logMap(map);
        log(map.get(new Person("Perry5", "Huan5", 18)));


    }

    enum Weekday {
        SUN, MON, TUE, WED, THU, FRI, SAT;
    }

    public class Person {
        String firstName;
        String lastName;
        int age;

        public Person() {
        }

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        @Override
        public String toString() {
            return firstName + "," + lastName + "," + age;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Person) {
                Person person = (Person) obj;
                return Objects.equals(this.firstName, person.firstName)
                        && Objects.equals(this.lastName, person.lastName)
                        && age == person.age;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName, age);
        }
    }
}
