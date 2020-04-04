package com.learn.thread;

public class TestThread {
    public static void learn() {
        threadBasic();
        interrupt();
        deamon();
    }


    public static void log(Object object) {
        System.out.println(object);
    }

    /**
     * 线程有6种状态:
     * New：新创建的线程，尚未执行；
     * Runnable：运行中的线程，正在执行run()方法的Java代码；
     * Blocked：运行中的线程，因为某些操作被阻塞而挂起；
     * Waiting：运行中的线程，因为某些操作在等待中；
     * Timed Waiting：运行中的线程，因为执行sleep()方法正在计时等待；
     * Terminated：线程已终止，因为run()方法执行完毕。
     */
    public static void threadBasic() {
        // 第一种创建新线程的方式
        Thread thread = new MyThread();
        thread.start();
        // 第二种
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log("run in thread1");
            }
        });
        thread1.start();
        // 第三种
        Thread thread2 = new Thread(() -> {
            try {
                thread1.join(); // 等待 thread1执行完成再执行thread2
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log("run in thread2");
        });
        thread2.setPriority(10); // 设置线程的优先级
        thread2.start();
        try {
            thread2.join(); // 等待thead2线程执行完成之后再继续执行主线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("Main线程");
    }

    /**
     * 中断线程
     */
    public static void interrupt() {
        // 控制线程的中断
        Thread thread = new InterruptThread();
        thread.start();
        try {
            Thread.sleep(10);
            log("主线程即将中断 thread");
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 另外一种方法是使用一个变量
        CtrlThread thread1 = new CtrlThread();
        thread1.start();
        try {
            Thread.sleep(1);
            thread1.running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    /**
     * 当还有一个线程没有退出时，JVM进程便不会关闭，
     * 但存在如那种永远不会停止的定时进程，这时如果JVM进程是无法关闭。
     * java提供了守护线程，对于守护线程，只要其它线程全部关闭，则无论守护线程是否关闭，JVM进程都会关闭
     * 守护线程
     */
    public static void deamon() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                long l = 0;
                while (true) {
                    l += 1000;
                    log("定时:" + l);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        thread.setDaemon(true); // 开启守护线程
        thread.start();
        try {
            Thread.sleep(1000);
            log("执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("run in my thread");
    }
}

class InterruptThread extends Thread {
    @Override
    public void run() {
        int n = 0;
        while (!isInterrupted()) { // 检测是否已被中断
            n++;
            System.out.println("hello word --" + n);
        }
    }
}


class CtrlThread extends Thread {
    /**
     * 在Java虚拟机中，变量的值保存在主内存中，但是，当线程访问变量时，它会先获取一个副本，
     * 并保存在自己的工作内存中。如果线程修改了变量的值，虚拟机会在某个时刻把修改后的值回写到主内存，
     * 但是，这个时间是不确定的！
     * <p>
     * volatile关键字的目的是告诉虚拟机：
     * 每次访问变量时，总是获取主内存的最新值；
     * 每次修改变量后，立刻回写到主内存。
     */
    public volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("莫道不销魂");
        }
    }
}

