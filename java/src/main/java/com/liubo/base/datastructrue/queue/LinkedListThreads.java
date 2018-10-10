package com.liubo.base.datastructrue.queue;

import java.util.LinkedList;

/**
 * @ClassName: LinkedListThreads
 * @ProjectName scalademo
 * @Author Admin
 * @Date 2018-09-30 19:47
 * @Description:
 */
public class LinkedListThreads {
    public static LinkedList list = new LinkedList();

    //public static ConcurrentLinkedQueue list = new ConcurrentLinkedQueue();
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(new ListPut()).start();
        }
        Thread.currentThread().sleep(3000L);
        System.out.println("List的变量size值为：" + list.size());
        for (int i = 0; i < list.size(); i++) {
            String temp = (String) list.poll();
            if (temp == null) {
                System.out.println("第" + i + "个元素取出为null");
                return;
            }
        }
    }

}

class ListPut implements Runnable {

    @Override
    public void run() {
        //synchronized(ListPut.class){
        for (int i = 0; i < 1000; i++) {
            LinkedListThreads.list.add("");
        }
        //}
        //System.out.println("线程"+Thread.currentThread().getId()+"已经结束了");
    }

}