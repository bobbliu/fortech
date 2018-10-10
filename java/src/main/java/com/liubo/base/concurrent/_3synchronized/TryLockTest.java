package com.liubo.base.concurrent._3synchronized;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 于tryLock(long time, TimeUnit unit)和lockInterruptibly()，
 * 前者主要存在一个等待时间，在测试代码中写入一个等待时间，
 * 后者主要是等待中断，会抛出一个中断异常，常用度不高，
 */
public class TryLockTest {
    private Lock lock = new ReentrantLock();

    //需要参与同步的方法
    private void method(Thread thread) {
        if (lock.tryLock()) {
            try {
                System.out.println("线程名" + thread.getName() + "获得了锁");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("线程名" + thread.getName() + "释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println("我是" + Thread.currentThread().getName() + "有人占着锁，我就不要啦");
        }
    }

    public static void main(String[] args) {
        TryLockTest lockTest = new TryLockTest();

        //线程1
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockTest.method(Thread.currentThread());
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockTest.method(Thread.currentThread());
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
