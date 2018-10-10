package com.liubo.base.concurrent._3synchronized;

public class ConsumerSynch implements Runnable {
    @Override
    public synchronized void run() {
        int count = 10;
        while (count > 0) {
            synchronized (SynchronizedTest.obj) {
                System.out.println("B");
                count--;
                SynchronizedTest.obj.notify();//主动释放锁
                try {
                    SynchronizedTest.obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
