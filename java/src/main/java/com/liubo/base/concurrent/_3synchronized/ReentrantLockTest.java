package com.liubo.base.concurrent._3synchronized;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在并发量比较小的情况下，使用synchronized是个不错的选择，
 * 但是在并发量比较高的情况下，其性能下降很严重，此时ReentrantLock是个不错的方案。
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        ConsumerLock consumerLock = new ConsumerLock(lock);
        ProducerLock producerLock = new ProducerLock(lock);
        new Thread(consumerLock).start();
        new Thread(producerLock).start();
    }
}
