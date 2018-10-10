package com.liubo.base.basis._19generics;

/**
 * @ClassName: Box
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 14:36
 * @Description:
 *泛型类：
 * 这是最常见的做法，这样做的一个坏处是Box里面现在只能装入String类型的元素，
 * 今后如果我们需要装入Integer等其他类型的元素，还必须要另外重写一个Box，
 * 代码得不到复用，使用泛型可以很好的解决这个问题。
 */
public class Box<T> {
    private T t;

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }

    //这样我们的Box类便可以得到复用，我们可以将T替换成任何我们想要的类型：
    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<>();
        Box<String> stringBox = new Box<>();
        Box<Double> doubleBox = new Box<>();
    }
}
