package com.lichuanqi.demo;


import com.lichuanqi.demo.redis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * lichuanqi
 * 202209/18
 * 分布式锁测试。
 *
 *
 *  为什么需要分布式锁：  非集群、非分布式的情况下， 面对线程安全的问题，可以用java提供的ReentrantLock 或者 Synchronized 即可，
 *                  但是该锁是线程相关的，无法满足分布式或者集群的部署情况。
 *  分布式锁的常用实现方式：（可参考：https://blog.csdn.net/qq_42764269/article/details/122435977）
 *      1、redis
 *      2、zookeeper
 *      3、数据库  （悲观锁、乐观锁）
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistributedLockTest {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * redis 分布式锁 ---- 之基础版，加锁
     */

    @Test
    public void DistributedLock(){
        // 可以高并发情况下测试下

        // 可传入requestId,作为value，保证自己加的锁，只有自己能解
        boolean res = redisUtil.getDistributedLock("key:12345","requestId:123",30);
        if( res) {
            System.out.println("分布式锁获取成功");
        }
    }

    @Test
    public void releaseDistributedLock(){
        // 注意看方法内部， 用的lua脚本，保证 锁的拥有者判断，和锁的释放 的原子性
        boolean res = redisUtil.releaseDistributedLock("key:12345","requestId:123");
        if( res) {
            System.out.println("分布式锁释放成功");
        }
    }

    /**
     * redis分布式锁 ----- 之自旋锁
     */
    @Test
    public void spinLock() {
        String key = "lcqTest";
        int k = 0;
        for (; ; ) {
            boolean r = redisUtil.setnx(key, "", 20);
            if (r) {
                return;
            }

            if (k++ >= 300) {
                throw new RuntimeException("lock error key = " + key);
            }

            try {
                TimeUnit.MILLISECONDS.sleep(10 + new Random().nextInt(20));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




}
