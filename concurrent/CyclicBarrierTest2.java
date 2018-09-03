package test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: lizhenmin
 * @date: 2018/9/3 22:45
 */
public class CyclicBarrierTest2 {

    /**
     * CyclicBarrier(int parties, Runnable barrierAction)
     * 当线程到达同步点时，优先执行barrierAction
     */
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new A());

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {

            } catch (BrokenBarrierException e) {

            }

            System.out.println(1);
        }).start();

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {

        } catch (BrokenBarrierException e) {

        }

        System.out.println(2);
        /*
         * 输出
         * 3
         * 1
         * 2
         */

    }

    static class A implements Runnable {

        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
