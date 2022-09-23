package com.lichuanqi.apipassenger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AotmicTest {

        public static AtomicInteger race=new AtomicInteger(0);
        public static void increase(){
            race.incrementAndGet();
        }
        private static final int THREADS_COUNT=20;

        @Test
        public  void test(){
            Thread[] threads=new Thread[THREADS_COUNT];
            for(int i=0;i<THREADS_COUNT;i++){
                threads[i]=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<10000;i++){
                            increase();
                        }
                    }
                });
                threads[i].start();
            }
            while(Thread.activeCount()>1){
                Thread.yield();
                System.out.println(race);
            }
        }


}
