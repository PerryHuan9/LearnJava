package com.learn.collection;

import java.util.*;

public class TestSet {
    public static void learn() {
        TestSet ts = new TestSet();
        ts.set();
        ts.queue();
        ts.priorityQueue();
        ts.deque();
        ts.stack();
    }

    public static void log(Object o) {
        System.out.println(o);
    }

    public static <V> void logSet(Set<V> set) {
        log(Arrays.toString(set.toArray()));
    }


    /**
     * Set 中的元素唯一，HashSet使用HashMap实现,容器中的元素无序，TreeSet则可以排序
     */
    public void set() {
        Set<String> set = new HashSet<>();
        String s = "长江后浪推前浪";
        log(set.add(s));
        log(set.add(s)); // false, 已存在
        log(set.add("后浪被拍死在沙滩上"));
        logSet(set);
        log(set.contains(s));
        set.remove(s);
        logSet(set);

        Set ts = new TreeSet();
        ts.add("Word");
        ts.add("Hello");
        ts.add("Perry");
        ts.add("Huang");
        logSet(ts);
        for (Iterator i = ts.iterator(); i.hasNext(); ) {
            log(i.next());
        }

    }


    /**
     * Queue队列，先进先出，提供了两套操作方法：
     * throw Exception	    返回false或null
     * 添加元素到队尾	    add(E e)	        boolean offer(E e)
     * 取队首元素并删除	E remove()	        E poll()
     * 取队首元素但不删除	E element()	        E peek()
     */
    public void queue() {
        Queue queue = new LinkedList();
        queue.offer(12);
        queue.offer("123123");
        queue.offer("我欲将心向明月，奈何明月照沟渠");
        log(queue.peek());
        log(queue.peek());
        log(queue.poll());
        log(queue.poll());
        log(queue.size());
        queue.add(null);
        log(queue.size());
    }

    /**
     * 优先队列：根据条件控制出队顺序,元素必须实现Comparable接口，或者在创建PriorityQueue的时候传入Comparator对象
     */
    public void priorityQueue() {
        // String已实现Comparable接口
        Queue<String> queue = new PriorityQueue();
        queue.add("888");
        queue.add("666");
        queue.add("333");
        queue.add("111");
        log(Arrays.toString(queue.toArray()));
        log(queue.poll()); // 111
        log(queue.poll()); // 333

        Queue<User> users = new PriorityQueue<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.number.charAt(0) == o2.number.charAt(0)) {
                    return o1.number.compareTo(o2.number);
                }
                if (o1.number.charAt(0) == 'V'){
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        users.offer(new User("Perry", "A1"));
        users.offer(new User("Perry1", "A3"));
        users.offer(new User("Perry2", "A2"));
        users.offer(new User("PerryV2", "V2"));
        users.offer(new User("PerryV1", "V1"));
        log(Arrays.toString(users.toArray()));
        log(users.remove()); // v1
        log(users.element()); // v2


    }


    /**
     * Deque双向队列
     *                   Queue	                        Deque
     * 添加元素到队尾	     add(E e) / offer(E e)	        addLast(E e) / offerLast(E e)
     * 取队首元素并删除	 E remove() / E poll()	        E removeFirst() / E pollFirst()
     * 取队首元素但不删除	 E element() / E peek()	        E getFirst() / E peekFirst()
     * 添加元素到队首	     无	                            addFirst(E e) / offerFirst(E e)
     * 取队尾元素并删除	 无	                            E removeLast() / E pollLast()
     * 取队尾元素但不删除	 无	                            E getLast() / E peekLast()
     *
     */
    public void deque() {
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("hello");
        deque.addFirst("word");
        deque.offerFirst("Perry,");
        log(Arrays.toString(deque.toArray()));
        deque.offerLast("黄益凛");
        deque.addLast("打入小");
        log(Arrays.toString(deque.toArray()));
        log(deque.removeFirst());
        log(deque.removeLast());
        log(deque.pollFirst());
        log(deque.pollLast());
    }

    /**
     * Stack 栈拥有后进先出的特性，可以使用Deque的addFirst、removeFirst来模拟
     * 之所以不存在Stack的接口，是已经存在了一个Stack的遗留类
     */
    public void stack() {
        Stack stack = new Stack();
        stack.push("hello");
        stack.push("world");
        stack.push("perry");
        log(stack.pop());
        log(stack.pop());
        log(stack.pop());

    }

    class User {
        private String name;
        private String number;

        public User(String name, String number) {
            this.name = name;
            this.number = number;
        }

        @Override
        public String toString() {
            return name + ":" + number;
        }
    }

}
