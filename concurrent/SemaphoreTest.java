package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 控制并发线程数 Semaphore
 *
 * 用于控制同时访问特定资源的线程数量
 *
 * @author: lizhenmin
 * @date: 2018/9/3 23:15
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;

    private static ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);

    /**
     * 最大并大数 10
     */
    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {

        for (int i = 0; i < THREAD_COUNT; i++) {
            pool.execute(() -> {
                try {

                    // 获取许可证
                    semaphore.acquire();
                    System.out.println("save data");
                    // 释放许可证
                    semaphore.release();
                } catch (InterruptedException e) {

                }
            });
        }

        pool.shutdown();
    }
}
