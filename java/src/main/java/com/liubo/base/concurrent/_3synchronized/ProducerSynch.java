package com.liubo.base.concurrent._3synchronized;

public class ProducerSynch implements Runnable{
    @Override
    public void run() {
        int count = 10;
        while(count > 0){
            synchronized (SynchronizedTest.obj){
                System.out.println("A" );
                count --;
                SynchronizedTest.obj.notify();
                try{
                    SynchronizedTest.obj.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }
    }
}
