package com.liubo.base.basis._19generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: GenericWriting
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 15:45
 * @Description:
 * 如果我们要add元素应该怎么做呢？可以使用<? super T>
 *
 *  这样我们可以往容器里面添加元素了，但是使用super的坏处是以后不能get
 *  容器里面的元素了，原因很简单，我们继续从编译器的角度考虑这个问题，
 *  对于List<? super Apple> list，它可以有下面几种含义：
 *
 *  List<? super Apple> list = new ArrayList<Apple>();
 * List<? super Apple> list = new ArrayList<Fruit>();
 * List<? super Apple> list = new ArrayList<Object>();
 *
 * 当我们尝试通过list来get一个Apple的时候，可能会get得到一个Fruit，
 * 这个Fruit可以是Orange等其他类型的Fruit。
 *
 * 根据上面的例子，我们可以总结出一条规律，”Producer Extends, Consumer Super”：
 * “Producer Extends” – 如果你需要一个只读List，用它来produce T，那么使用? extends T。
 * “Consumer Super” – 如果你需要一个只写List，用它来consume T，那么使用? super T。
 * 如果需要同时读取以及写入，那么我们就不能使用通配符了
 *
 */
public class GenericWriting {
    static List<Apple> apples = new ArrayList<Apple>();
    static List<Fruit> fruits = new ArrayList<Fruit>();

    static <T> void writeExact(List<T> list, T item) {
        list.add(item);
    }

    static void f1() {
        writeExact(apples, new Apple());
        writeExact(fruits, new Fruit());
    }

    static <T> void writeWithWildcard(List<? super T> list, T item){
        list.add(item);
    }

    static void f2(){
        writeWithWildcard(apples, new Apple());
        writeWithWildcard(fruits, new Fruit());
    }

    public static void main(String[] args) {
        f1();
        f2();
    }
}
