package com.learn.thread;

public class ThreadSync {

    public static void learn() {
        testSynchronized();
    }

    /**
     * 线程调度完全由系统决定，当多个线程同时对一份共享变量进行操作时，
     * 由于Java中大部分语句是不具备原子性的，因此经常会出现错误的计算结果
     * 如 n = n+1这一语句，实际上它对应三条指令 ILOAD IADD ISTORE，
     * 当执行到ILOAD时被中断，那么这次操作就不会得到正确的结果
     * <p>
     * JVM规范定义了几种原子操作：
     * <p>
     * 基本类型（long和double除外）赋值，例如：int n = m；
     * 引用类型赋值，例如：List<String> list = anotherList。
     * long和double是64位数据，JVM没有明确规定64位赋值操作是不是一个原子操作，
     * 不过在x64平台的JVM是把long和double的赋值作为原子操作实现的。
     * <p>
     * JVM允许同一个线程重复获取同一个锁，这种能被同一个线程反复获取的锁，就叫做可重入锁。
     * 可重入锁在使用不当时，会造成死锁，当出现死锁时只能结束进程，所以应保证程序不出现死锁
     */
    public static void testSynchronized() {
        SyncCounter sc = new SyncCounter();
        Thread thread = new Thread() {
            @Override
            public void run() {
//                TestDiedLock.add();
                for (var i = 0; i < 1000; i++) {
                    synchronized (Counter.lock) {
                        Counter.count++;
                        sc.add();
                    }
                }
            }
        };
        Thread thread1 = new Thread() {
            @Override
            public void run() {
//                TestDiedLock.desc();
                for (var i = 0; i < 1000; i++) {
                    synchronized (Counter.lock) {
                        Counter.count--;
                        sc.minus();
                    }
                }
            }
        };
        thread.start();
        thread1.start();
        try {
            thread1.join();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行结果:" + Counter.count); // 0
        System.out.println("执行结果2:" + sc.getCount()); // 0

    }


}

class Counter {
    public static final Object lock = new Object();
    public static int count = 0;
}

class SyncCounter {
    public static int total = 0;
    private int count;

    /**
     * synchronized修饰的静态方法锁住的是SyncCounter.class
     */
    public static synchronized void totalAdd() {
        total++;
    }

    public void add() {
        synchronized (this) {
            count++;
        }
    }

    /**
     * 使用synchronized修饰符与上面的synchronized (this) {}效果一致，
     * 只不过synchronized修饰符是保证这个方法都是同步的
     */
    public synchronized void minus() {
        count--;
    }

    /**
     * 一个get方法不需要同步
     *
     * @return
     */
    public int getCount() {
        return count;
    }
}


class TestDiedLock {
    public static final Object lockA = new Object();
    public static final Object lockB = new Object();

    public static int countA = 0;
    public static int countB = 0;

    public static void sleep1s() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void add() {
        /**
         * 两个线程中对于lockA和lockB的锁定顺序不一致，导致了死锁
         * 线程thread：获得lockA；
         * 线程thread1：获得lockB。
         * 随后：
         * 线程thread：准备获得lockB，失败，等待中；
         * 线程thread1：准备获得lockA，失败，等待中。
         */
        synchronized (lockA) {
            countA++;
            sleep1s();
            synchronized (lockB) {
                countB++;
            }
        }
    }

    public static void desc() {
        synchronized (lockB) {
            countB--;
            sleep1s();
            synchronized (lockA) {
                countA--;
            }
        }
    }

}
