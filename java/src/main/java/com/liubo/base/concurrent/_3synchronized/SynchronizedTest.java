package com.liubo.base.concurrent._3synchronized;

/**
 * synchronized可以保证方法或者代码块在运行时，同一时刻只有一个方法可以进入到临界区，同时它还可以保证共享变量的内存可见性
 * 1.普通同步方法，锁是当前实例对象
 * 2.静态同步方法，锁是当前类的class对象
 * 3.同步方法块，锁是括号里面的对象
 *
 */

//这里使用static obj作为锁的对象，当线程Produce启动时（假如Produce首先获得锁，则Consumer会等待），
// 打印“A”后，会先主动释放锁，然后阻塞自己。Consumer获得对象锁，打印“B”，然后释放锁，阻塞自己，
// 那么Produce又会获得锁，然后...一直循环下去，直到count = 0.这样，使用Synchronized和wait()
// 以及notify()就可以达到线程同步的目的。
public class SynchronizedTest {
    public static final Object obj = new Object();

    public static void main(String[] args) {
        new Thread(new ProducerSynch()).start();
        new Thread(new ConsumerSynch()).start();
    }
}
