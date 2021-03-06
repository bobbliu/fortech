package com.liubo.base.concurrent._3synchronized;

import java.util.concurrent.locks.Lock;

public class ProducerLock implements Runnable{
    private Lock lock;

    public ProducerLock(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0){
            try {
                lock.lock();
                count --;
                System.out.println("A");
            } finally {
                lock.unlock();
                try {
                    Thread.sleep(91L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
