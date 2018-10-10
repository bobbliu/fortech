package com.liubo.base.basis._19generics;

/**
 * @ClassName: Util
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 14:42
 * @Description: 泛型方法：
 * 看完了泛型类，接下来我们来了解一下泛型方法。声明一个泛型方法很简单，
 * 只要在返回类型前面加上一个类似<K, V>的形式就行了：
 */
public class Util {

    public static void main(String[] args) {
        //或者在Java1.7/1.8利用type inference，让Java自动推导出相应的类型参数：
        Pair<Integer, String> p1 = new Pair<>(1, "apple");
        Pair<Integer, String> p2 = new Pair<>(2, "pear");
        boolean same = Util.compare(p1, p2);
        System.out.println(same);
    }

    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
    }

    /**
     * 边界符
     * 现在我们要实现这样一个功能，查找一个泛型数组中大于某个特定元素的个数，
     * <p>
     * 但是这样很明显是错误的，因为除了short, int, double, long, float, byte,
     * char等原始类型，其他的类并不一定能使用操作符>，所以编译器报错，
     * 那怎么解决这个问题呢？答案是使用边界符。
     *
     * @param anArray
     * @param elem
     * @param <T>
     * @return
     */

    //做一个类似于下面这样的声明，这样就等于告诉编译器类型参数T代表的都
    // 是实现了Comparable接口的类，这样等于告诉编译器它们都至少实现了compareTo方法。
    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray) {
            //if (e > elem) {          // compiler error
            if (e.compareTo(elem) > 0) {
                ++count;
            }
        }
        return count;
    }

}
