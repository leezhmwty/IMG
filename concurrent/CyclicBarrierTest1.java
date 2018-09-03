package test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 同步屏障 CyclicBarrier
 *
 * CyclicBarrier可以让一组线程达到一个同步点时被阻塞，
 * 直到最后一个线程到达同步点，所有被拦截的线程才会继续运行。
 *
 * @author: lizhenmin
 * @date: 2018/9/3 22:49
 */
public class CyclicBarrierTest1 {

    /**
     * 默认构造函数，入参不能大于想要拦截的线程数，
     * 否则程序进入一直等待的状态
     */
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(1);

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                // 同步点
                cyclicBarrier.await();
            } catch (InterruptedException e) {

            } catch (BrokenBarrierException e) {

            }
            System.out.println(1);
        }).start();

        try {

            // 同步点
            cyclicBarrier.await();
        } catch (InterruptedException e) {

        } catch (BrokenBarrierException e) {

        }

        System.out.println(2);

        /*
         * 以上代码输出
         * 1
         * 2
         * 或
         * 2
         * 1
         * 因为CPU调度线程运行，所以两个线程都有可能先执行
         */
    }
}
