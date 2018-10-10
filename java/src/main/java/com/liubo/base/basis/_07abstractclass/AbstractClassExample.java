package com.liubo.base.basis._07abstractclass;

/**
 * 抽象类
 *
 * 抽象类和抽象方法都使用 abstract 关键字进行声明。抽象类一般会包含抽象方法，抽象方法一定位于抽象类中。
 * 抽象类和普通类最大的区别是，抽象类不能被实例化，需要继承抽象类才能实例化其子类。
 */
public abstract  class AbstractClassExample {
    protected int x;
    private int y;

    public abstract void func1();

    public void func2(){
        System.out.println("func2");
    }
}
