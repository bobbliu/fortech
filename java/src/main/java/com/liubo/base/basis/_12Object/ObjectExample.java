package com.liubo.base.basis._12Object;

import org.junit.Test;

/**
 * 查看Object源码
 */
public class ObjectExample {

    /**
     * 1. 等价关系
     */
    @Test
    public void equalsTest() {
        /**
         * equals()
         * 1. 等价关系
         *
         * Ⅰ 自反性
         */
        String x = "aaa";
        String y = "aaa";
        String z = "aaa";
        System.out.println(x.equals(x));

        /**
         *Ⅱ 对称性
         */
        System.out.println(x.equals(y) == y.equals(x));

        /**
         * Ⅲ 传递性
         */
        if (x.equals(y) && y.equals(z)) {
            System.out.println(x.equals(z));
        }

        /**
         * Ⅳ 一致性
         *
         * 多次调用 equals() 方法结果不变
         */
        System.out.println(x.equals(y) == x.equals(y));

        /**
         * Ⅴ 与 null 的比较
         *
         * 对任何不是 null 的对象 x 调用 x.equals(null) 结果都为 false
         */
        System.out.println(x.equals(null));
    }

    /**
     * 2. 等价与相等
     * <p>
     * 对于基本类型，== 判断两个值是否相等，基本类型没有 equals() 方法。
     * 对于引用类型，== 判断两个变量是否引用同一个对象，而 equals() 判断引用的对象是否等价。
     */
    @Test
    public void and2EqualsTest() {
        Integer x = new Integer(1);
        Integer y = new Integer(1);
        System.out.println(x.equals(y));    //true
        System.out.println(x == y); //false
    }
}

