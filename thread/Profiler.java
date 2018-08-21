package test;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal
 *
 * @author: lizhenmin
 * @date: 2018/8/21 22:42
 */
public class Profiler {

    private static final ThreadLocal<Long> LONG_THREAD_LOCAL = new ThreadLocal<>();

    public static final void begin() {
        LONG_THREAD_LOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - LONG_THREAD_LOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + Profiler.end() + " mills");
    }

}
