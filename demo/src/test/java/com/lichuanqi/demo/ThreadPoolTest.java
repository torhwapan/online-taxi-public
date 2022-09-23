package com.lichuanqi.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadPoolTest {


    /**
     * csdn 参考 : https://blog.csdn.net/cpcpcp123/article/details/125407817
     * @throws Exception
     */
    @Test
    public void testThreaPoolClose() throws Exception {
        /**
         * AtomicInteger  保证原子性，用于计数
         */
        AtomicInteger totalCount = new AtomicInteger(0);

        // 线程总数
        final int tastCount = 50;
        // 计数器
        CountDownLatch countDownLatch = new CountDownLatch(tastCount);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(6, 6, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));
        Thread[] threads = new Thread[tastCount];
        for (int i = 0; i < tastCount; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.err.println("总调用次数" + totalCount.incrementAndGet());
                    countDownLatch.countDown();
                }
            });
            threadPoolExecutor.submit(threads[i]);
        }
        /**
         * 测试线程池，关闭方法一，先手动关闭线程池池，再循环判断线程池是否已经完全关闭，
         */
//        threadPoolExecutor.shutdown();
//        while(! threadPoolExecutor.isTerminated()){
//        }
//        System.out.println("线程池已关闭");

        /**
         *  测试线程池，关闭方法二， 通过CountDownLatch计数. 但是countDownLatch只能使用一次，不可重复使用
         */
        countDownLatch.await();
        System.out.println("线程池任务执行完毕,countDownLauch count :" + countDownLatch.getCount());


        /**
         * 测试线程池，关闭方法三，通过CyclicBarrier，可重复使用，但是使用起来较麻烦。
         */


    }


}
