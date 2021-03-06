package com.liubo.base.datastructrue.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName: BlockingQueueDemo
 * @ProjectName scalademo
 * @Author Admin
 * @Date 2018-09-30 19:38
 * @Description:
 */
public class BlockingQueueDemo {
    public static LinkedBlockingQueue queue = new LinkedBlockingQueue(5);
    public static void main(String [] args){
        Receiver receiver  = new Receiver();

        for(int i = 0;i<20;i++){
            new Thread(new SenderPut()).start();
        }
        /*
        for(int i = 0;i<20;i++){
            new Thread(new SenderOffer()).start();
        }
        */
        try {
            Thread.currentThread().sleep(3000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        new Thread(receiver).start();
    }
}

class SenderPut implements Runnable {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            System.out.println("已经进入线程id："+Thread.currentThread().getId()+"的内部");
            BlockingQueueDemo.queue.put(Thread.currentThread().getId());
            System.out.println("当前发送的线程id为："+Thread.currentThread().getId());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class SenderOffer implements Runnable {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            System.out.println("已经进入线程id："+Thread.currentThread().getId()+"的内部");
            BlockingQueueDemo.queue.offer(Thread.currentThread().getId());
            System.out.println("当前发送的线程id为："+Thread.currentThread().getId());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class Receiver implements Runnable {
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true){
            try {
                System.out.println("当前取出的线程id为："+BlockingQueueDemo.queue.take());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}