package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * daemon线程得使用
 *
 * @author: lizhenmin
 * @date: 2018/8/20 23:03
 */
public class DeamonThread {

    public static void main(String[] args) throws InterruptedException {
        Thread subThread = new Thread(new Job());

        // 将subThread设置为守护线程,默认false
        subThread.setDaemon(true);

        // 启动subThread线程
        subThread.start();

        System.out.println(Thread.currentThread() + " End");
        TimeUnit.SECONDS.sleep(1);
        /*
         * subThread.setDaemon(true)时，程序最终打印 ->
         * Thread[main,5,main] End
         * 或
         * Thread[main,5,main] End
         * Thread-0 start
         * 说明若当前线程是daemon线程时，则主线程不会等待daemon线程执行结束，而是直接退出
         */

        /*
         * subThread.setDaemon(false)时，程序最终打印 ->
         * Thread[main,5,main] End
         * Thread-0 start
         * Thread-0 End
         * 说明若当前线程是非daemon线程时，则主线程会等待daemon线程执行结束后在退出
         */
    }

    static class Job implements Runnable {

        @Override
        public void run() {
            try {

                System.out.println(Thread.currentThread().getName() + " start");

                // 让当前线程睡眠10秒后恢复执行
                TimeUnit.SECONDS.sleep(10);
                System.out.println(Thread.currentThread().getName() + " End");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
