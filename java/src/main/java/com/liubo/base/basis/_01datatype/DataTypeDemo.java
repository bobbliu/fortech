package com.liubo.base.basis._01datatype;

import org.testng.annotations.Test;

public class DataTypeDemo {
    /**
     * 一、包装类型
     * boolean/1
     * byte/8
     * char/16
     * short/16
     * int/32
     * float/32
     * long/64
     * double/64
     * 基本类型都有对应的包装类型，基本类型与其对应的包装类型之间的赋值使用自动装箱与拆箱完成。
     */
    @Test
    public void test1() {
        Integer x = 2;//装箱
        int y = x;//拆箱
    }

    /**
     * 二、缓存池
     * new Integer(123) 与 Integer.valueOf(123) 的区别在于：
     * * new Integer(123) 每次都会新建一个对象；
     * * Integer.valueOf(123) 会使用缓存池中的对象，多次调用会取得同一个对象的引用。
     * <p>
     * valueOf() 方法的实现比较简单，就是先判断值是否在缓存池中，如果在的话就直接返回缓存池的内容。
     * 在 Java 8 中，Integer 缓存池的大小默认为 -128~127。
     * <p>
     * 编译器会在自动装箱过程调用 valueOf() 方法，因此多个 Integer 实例使用自动装箱来创建并且值相同，那么就会引用相同的对象。
     */
    @Test
    public void test2() {
        Integer x = new Integer(123);
        Integer y = new Integer(123);
        System.out.println(x == y);
        Integer z = Integer.valueOf(123);
        Integer k = Integer.valueOf(123);
        System.out.println(z == k);

        Integer m = 123;
        Integer n = 123;
        System.out.println(m == n);
    }

    /**
     * 基本类型对应的缓冲池如下：
     *
     * boolean values true and false
     * all byte values
     * short values between -128 and 127
     * int values between -128 and 127
     * char in the range \u0000 to \u007F
     * 在使用这些基本类型对应的包装类型时，就可以直接使用缓冲池中的对象。
     */
}
