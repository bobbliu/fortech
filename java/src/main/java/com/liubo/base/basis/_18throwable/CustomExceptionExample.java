package com.liubo.base.basis._18throwable;

/**
 * @ClassName: CustomExceptionExample
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 13:27
 * @Description: 自定义异常
 * 使用Java内置的异常类可以描述在编程时出现的大部分异常情况。除此之外，
 * 用户还可以自定义异常。用户自定义异常类，只需继承Exception类即可。
 * <p>
 * 在程序中使用自定义异常类，大体可分为以下几个步骤:
 * <p>
 * 创建自定义异常类。
 * 在方法中通过throw关键字抛出异常对象。
 * 如果在当前抛出异常的方法中处理异常，可以使用try-catch语句捕获并处理；
 * 否则在方法的声明处通过throws关键字指明要抛出给方法调用者的异常，继续进
 * 行下一步操作。
 * 在出现异常方法的调用者中捕获并处理异常。
 */
public class CustomExceptionExample extends Exception {
    private int detail;

    CustomExceptionExample(int a) {
        detail = a;
    }

    public String toString() {
        return "CustomExceptionExample [" + detail + "]";
    }
}
