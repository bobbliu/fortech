package com.liubo.base.datastructrue.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName: LinkedBlockingQueuePerform
 * @ProjectName scalademo
 * @Author Admin
 * @Date 2018-09-30 19:45
 * @Description:
 */
public class LinkedBlockingQueuePerform {
    public static LinkedBlockingQueue list = new LinkedBlockingQueue();
    public static long beginTime = System.currentTimeMillis();
    public static long yunxingTime =3000L;
    public static void main(String[] args){
        for(int i=0;i<100;i++){
            Thread lock = new Thread(new BlockLink());
            lock.start();
        }
        while(true){
            long nowTime = System.currentTimeMillis();
            if((nowTime-beginTime)>yunxingTime){
                System.out.println("在使用LinkedBlockingQueue的情况下100个线程循环增加的元素数为："+LinkedBlockingQueuePerform.list.size());
                break;
            }
            else{
                try {
                    Thread.currentThread().sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class BlockLink implements Runnable{

    @Override
    public void run() {
        while(true){
            if((System.currentTimeMillis()-LinkedBlockingQueuePerform.beginTime)>LinkedBlockingQueuePerform.yunxingTime)
            {
                break;
            }
            add();
        }
    }

    public void add(){
        LinkedBlockingQueuePerform.list.add("");//增加元素
    }
}
