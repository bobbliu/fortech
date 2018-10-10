package com.liubo.base.datastructrue.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @ClassName: ConcurrentLinkedQueuePerform
 * @ProjectName scalademo
 * @Author Admin
 * @Date 2018-09-30 19:43
 * @Description:
 */
public class ConcurrentLinkedQueuePerform {
    public static ConcurrentLinkedQueue list = new ConcurrentLinkedQueue();
    public static long beginTime = System.currentTimeMillis();
    public static long yunxingTime = 3000L;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread lock = new Thread(new CurrentLink());
            lock.start();
        }
        while (true) {
            long nowTime = System.currentTimeMillis();
            if ((nowTime - beginTime) > yunxingTime) {
                System.out.println("在使用ConcurrentLinkedQueue的情况下100个线程循环增加的元素数为：" + ConcurrentLinkedQueuePerform.list.size());
                break;
            } else {
                try {
                    Thread.currentThread().sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class CurrentLink implements Runnable {

    @Override
    public void run() {
        while (true) {
            if ((System.currentTimeMillis() - ConcurrentLinkedQueuePerform.beginTime) > ConcurrentLinkedQueuePerform.yunxingTime) {
                break;
            }
            add();
        }
    }

    public void add() {
        ConcurrentLinkedQueuePerform.list.add("");//增加元素
    }
}