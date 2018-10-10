package com.liubo.base.concurrent._02forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinTest {
    public static void main(String[] args) {
        ordinaryTest();
        System.out.println("--------------------");
        forkJoinTest();
        System.out.println("--------------------");
        parallelStreamTest();
    }

    public static void ordinaryTest() {
        //普通线程实现
        Long x = 0L;
        Long y = 10000000000L;
        long l = System.currentTimeMillis();
        for (long i = 0; i <= y; i++) {
            x += i;
        }
        System.out.println("ordinaryTest result=" + x + " time=" + (System.currentTimeMillis() - l) + "ms");
    }

    public static void forkJoinTest() {
        //ForkJoin实现
        Long x = 0L;
        Long y = 10000000000L;
        Long l = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();//实现ForkJoin 就必须有ForkJoinPool// 的支持
        ForkJoinTask<Long> task = new ForkJoinDemo(x, y);//参数为起始值和结束值
        Long invoke = forkJoinPool.invoke(task);
        System.out.println("forkJoinTest result= " + invoke + " time=" + (System.currentTimeMillis() - l) + "ms");
    }

    public static void parallelStreamTest() {
        //Java8并行流
        Long x = 0L;
        Long y = 10000000000L;
        Long l = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(x, y).parallel().reduce(0, Long::sum);
        System.out.println("parallelStreamTest result=" + reduce + " time=" + (System.currentTimeMillis() - l) + "ms");
    }
}
