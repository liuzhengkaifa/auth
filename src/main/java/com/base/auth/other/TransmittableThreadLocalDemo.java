package com.base.auth.other;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuzheng
 * @date 2024年03月25日 9:50
 * @Description 
 */
public class TransmittableThreadLocalDemo {

    public static void main(String[] args) {
        threadLocalTest();
        /**
         * 线程1 = null
         * 线程2 = null
         */

        itlTest();
        /**
         * 线程1 = 我是主线程
         */

        itlTestThreadPoolTest();
        /**
         * 线程1 = 我是主线程
         * 线程2 = 我是主线程
         */

        ttlTest();
        /**
         * 线程1 = 我是主线程
         * 线程2 = 修改主线程
         */
    }

    private static void ttlTest() {
        ThreadLocal<String> local = new TransmittableThreadLocal<>();
        try {
            local.set("我是主线程");
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService = TtlExecutors.getTtlExecutorService(executorService);
            /*
                  executorService.submit(TtlRunnable.get(()->{}));
             */

            CountDownLatch countDownLatch1 = new CountDownLatch(1);
            CountDownLatch countDownLatch2 = new CountDownLatch(1);

            //初始化的时候赋予了父线程 threadLocal的值

            executorService.execute(() -> {
                System.out.println("线程1 = " + local.get());
                countDownLatch1.countDown();
            });
            countDownLatch1.await();

            //主线程值修改
            local.set("修改主线程");

            //再次调用，查看效果
            executorService.execute(() -> {
                System.out.println("线程2 = " + local.get());
                countDownLatch2.countDown();
            });
            countDownLatch2.await();
            executorService.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            local.remove();
        }
    }

    private static void itlTestThreadPoolTest() {
        ThreadLocal<String> local = new InheritableThreadLocal<>();
        try {
            local.set("我是主线程");
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            CountDownLatch countDownLatch1 = new CountDownLatch(1);
            CountDownLatch countDownLatch2 = new CountDownLatch(1);

            //初始化的时候赋予了父线程 threadLocal的值
            executorService.execute(() -> {
                System.out.println("线程1 = " + local.get());
                countDownLatch1.countDown();
            });
            countDownLatch1.await();

            //主线程值修改
            local.set("修改主线程");

            //再次调用，查看效果
            executorService.execute(() -> {
                System.out.println("线程2 = " + local.get());
                countDownLatch2.countDown();
            });
            countDownLatch2.await();
            executorService.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            local.remove();
        }
    }

    private static void itlTest() {
        ThreadLocal<String> local = new InheritableThreadLocal<>();
        try {
            local.set("我是主线程");
            new Thread(() -> {
                System.out.println("线程1 = " + local.get());
            }).start();
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            local.remove();
        }
    }

    private static void threadLocalTest() {
        ThreadLocal<String> local = new ThreadLocal<>();
        try {
            local.set("我是主线程");
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            CountDownLatch countDownLatch1 = new CountDownLatch(1);
            CountDownLatch countDownLatch2 = new CountDownLatch(1);

            executorService.execute(() -> {
                System.out.println("线程1 = " + local.get());
                countDownLatch1.countDown();
            });
            countDownLatch1.await();

            executorService.execute(() -> {
                System.out.println("线程2 = " + local.get());
                countDownLatch2.countDown();
            });
            countDownLatch2.await();
            executorService.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            local.remove();
        }
    }

}
