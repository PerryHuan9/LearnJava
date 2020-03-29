package com.learn.collection;

import java.util.*;

public class TestLsit {
    public static void learn() {
        var tl = new TestLsit();
        tl.listMethods();
        tl.createList();
        tl.iterateList();
        tl.listArray();
        tl.testEquals();
    }

    public static void logList(List list) {
        System.out.println(Arrays.toString(list.toArray()));
    }

    public static void println(Object o) {
        System.out.println(o);
    }

    public void listMethods() {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(99);
        logList(list);
        list.add(1, 300);
        list.add(2, 666);
        logList(list);
        list.remove(0);
        logList(list);
        Integer i = list.get(2);
        list.remove(i);
        logList(list);
        list.remove(Integer.valueOf(666));
        logList(list);
        println(list.size());
        println(Integer.valueOf(12) instanceof Object);
        // list内部判断是否为同一个对象不是使用==，而是使用equals，保存的对象需要正确覆写equals
        println(list.contains(3000));
    }

    public void createList() {
        // 不加泛型时，默认为object，什么类型都可以加入
        List list = new LinkedList();
        list.add(new TestLsit());
        list.add(Double.valueOf(34.56));
        List<String> l = new ArrayList<>(12);
        l.add("人生得意须尽欢，莫使金樽空对月");
        System.out.println(l.size()); //1
        l.add(null);
        println(l.size());
        for (String s : l) {
            println(s);
        }
        //除了以ArrayList和LinkedArray的方式创建数组之外，还可以直接使用List.of(),
        // 但是 List.of创建的数组无法进行修改操作（增加，修改),元素也不能有null
        List<String> l2 = List.of("我的未来", "不是梦"/*, null*/); // 有null会报错
        logList(l2);
        List<Integer> l3 = List.of(new Integer[]{12, 34});
        logList(l3);
    }

    public void iterateList() {
        String[] strArr = new String[]{"1231", "2324", "2342"};
        // 低效方式
        List<String> list = List.of(strArr);
        for (int i = 0; i < list.size(); i++) {
            println(list.get(i));
        }
        // 迭代器遍历
        for (Iterator<String> i = list.iterator(); i.hasNext(); ) {
            println(i.next());
        }

        // for each
        for (String s : list) {
            println(s);
        }
    }

    /**
     * 列表与数组的互换
     */
    public void listArray() {
        // 数组->列表
        String[] strArr = {"会登凌绝顶", "一览众山小"};
        List<String> list = List.of(strArr);
        logList(list);
        list = Arrays.asList(strArr);
        logList(list);
        // 列表->数组
        // 第一种，toArray() 会丢失泛型信息， 返回的是Object[]
        Object[] ss = list.toArray();
        println(Arrays.toString(ss));
        // 第二种：toArray传入一个同样类型或父类型的同样长度的数组
        String[] s = list.toArray(new String[list.size()]);
        println(Arrays.toString(s));
        CharSequence[] cs = list.toArray(new CharSequence[list.size()]);
        println(Arrays.toString(cs));
        // 第三钟： toArray传入一个函数
        String[] sss = list.toArray(String[]::new);
        println(Arrays.toString(sss));


    }

    public void testEquals() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Perry", 34));
        list.add(new Person("Perry2", 34));
        println(list.indexOf(new Person("Perry", 34))); // 0
        println(list.contains(new Person("Perry2", 34))); // true
    }

    class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Person) {
                Person objPerson = (Person) obj;
                // 只要name和age都相等就认为是同一个对象
                return Objects.equals(this.name, objPerson.name) && this.age == objPerson.age;
            }
            return false;
        }
    }
}
