package com.liubo.base.basis._14tostring;

/**
 * toString()
 * 默认返回 ToStringExample@4554617c 这种形式，其中 @ 后面的数值为散列码的无符号十六进制表示。
 */
public class ToStringExample {
    private int num;

    public static void main(String[] args) {
        ToStringExample ts = new ToStringExample(123);
        System.out.println(ts);
    }
    public ToStringExample(int num) {
        this.num = num;
    }
}
