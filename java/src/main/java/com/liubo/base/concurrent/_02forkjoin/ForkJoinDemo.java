package com.liubo.base.concurrent._02forkjoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo extends RecursiveTask<Long> {

    private Long start;//起始值
    private Long end;//结束值
    public static final Long critical = 100000L;//临界值

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        //判断是否是拆分完毕
        Long lenth = end - start;
        Long sum = 0L;
        if (lenth <= critical) {
            //如果拆分完毕就相加
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            //没有拆分完毕就开始拆分
            Long middle = (end + start) / 2;//计算的两个值的中间值
            ForkJoinDemo right = new ForkJoinDemo(start, middle);
            right.fork();//拆分，并压入线程队列
            ForkJoinDemo left = new ForkJoinDemo(middle + 1, end);
            left.fork();//拆分，并压入线程队列
            //合并
            return right.join() + left.join();
        }
    }
}
