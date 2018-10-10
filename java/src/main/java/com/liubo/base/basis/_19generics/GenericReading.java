package com.liubo.base.basis._19generics;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: GenericReading
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 15:04
 * @Description: 通配符:
 * 我们创建了一个泛型类Reader，然后在f1()中当我们尝试Fruit f = fruitReader.readExact(apples);
 * 编译器会报错，因为List<Fruit>与List<Apple>之间并没有任何的关系。
 */
public class GenericReading {
    static List<Apple> apples = Arrays.asList(new Apple());
    static List<Fruit> fruits = Arrays.asList(new Fruit());

    static class Reader<T> {
        T readExact(List<T> list) {
            return list.get(0);
        }
    }

    //这样就相当与告诉编译器， fruitReader的readCovariant方法接受的参数只
// 要是满足Fruit的子类就行(包括Fruit自身)，这样子类和父类之间的关系也就关联上了。

    static class CovariantReader<T> {
        T readCovariant(List<? extends T> list) {
            return list.get(0);
        }
    }

    static void f1() {
        Reader<Fruit> fruitReader = new Reader<Fruit>();
        // Errors: List<Fruit> cannot be applied to List<Apple>.
        //Fruit f = fruitReader.readExact(apples);
    }

    static void f2() {
        CovariantReader<Fruit> fruitCovariantReader = new CovariantReader<Fruit>();
        Fruit f = fruitCovariantReader.readCovariant(fruits);
        Fruit a = fruitCovariantReader.readCovariant(apples);
    }

    public static void main(String[] args) {
        f1();
        f2();
    }

    public String play(String a1,String a2){
        return null;
    }
}
