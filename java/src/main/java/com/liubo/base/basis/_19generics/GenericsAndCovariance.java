package com.liubo.base.basis._19generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: GenericsAndCovariance
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 15:39
 * @Description: 类似<? extends T>的用法，利用它我们可以从list里面get元素，那么可不
 * 可以往list里面add元素呢？我们来尝试一下：
 * <p>
 * 答案是否定，Java编译器不允许我们这样做，为什么呢？对于这个问题我们不妨
 * 从编译器的角度去考虑。因为List<? extends Fruit> flist它自身可以有多种含义：
 *
 * List<? extends Fruit> flist = new ArrayList<Fruit>();
 * List<? extends Fruit> flist = new ArrayList<Apple>();
 * List<? extends Fruit> flist = new ArrayList<Orange>();
 *
 * 当我们尝试add一个Apple的时候，flist可能指向new ArrayList<Orange>();
 * 当我们尝试add一个Orange的时候，flist可能指向new ArrayList<Apple>();
 * 当我们尝试add一个Fruit的时候，这个Fruit可以是任何类型的Fruit，而flist可能
 * 只想某种特定类型的Fruit，编译器无法识别所以会报错。
 *
 * 所以对于实现了<? extends T>的集合类只能将它视为Producer向外提供(get)元素，
 * 而不能作为Consumer来对外获取(add)元素。
 */
public class GenericsAndCovariance {
    public static void main(String[] args) {
        List<? extends Fruit> flist = new ArrayList<>();
        // Compile Error: can't add any type of object:
        //flist.add(new Apple());
        //flist.add(new Fruit());
        //flist.add(new Orange());
        flist.add(null);

        Fruit f = flist.get(0);
    }
}
