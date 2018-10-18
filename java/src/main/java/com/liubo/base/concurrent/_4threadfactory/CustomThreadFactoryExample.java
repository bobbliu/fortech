package com.liubo.base.concurrent._4threadfactory;

public class CustomThreadFactoryExample {
    public static void main(String[] args) {
        CustomThreadFactory factory = new CustomThreadFactory("CustomThreadFactory");
        Task task = new Task();
        Thread thread;
        System.out.println("Starting the Threads \n");
        for(int i = 1; i <= 10; i++){
            thread = factory.newThread(task);
            thread.start();
        }
        System.out.println("All Threads are created now \n");
        System.out.println("Give me CustomThreadFactory stats:\n" + factory.getStats());
    }
}
