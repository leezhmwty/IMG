package test;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * 等待/通知机制
 *
 * @author: lizhenmin
 * @date: 2018/8/21 21:34
 */
public class WaitNotify {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {

        @Override
        public void run() {

            // 加锁，拥有lock的Monitor
            synchronized (lock) {

                // 条件不满足时，继续wait，同时释放了lock的锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait @ " + LocalTime.now());
                        lock.wait();
                    } catch (InterruptedException e) {
                        // do something
                    }
                }

                // 条件满足时，完成工作
                System.out.println(Thread.currentThread() + " flag is false. running @ " + LocalTime.now());
            }
        }
    }

    static class Notify implements Runnable {

        @Override
        public void run() {

            // 加锁，拥有lock的Monitor
            synchronized (lock) {

                // 获取lock锁，然后进行通知，通知时不会释放锁
                // 直到当前线程释放了lock后，WaitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + " hold lock. notify @ " + LocalTime.now());
                lock.notifyAll();
                flag = false;
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                }
            }

            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep @ " + LocalTime.now());
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
