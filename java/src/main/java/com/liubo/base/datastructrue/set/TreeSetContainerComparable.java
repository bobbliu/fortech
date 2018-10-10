package com.liubo.base.datastructrue.set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @ClassName: TreeSetContainerComparable
 * @ProjectName scalademo
 * @Author Admin
 * @Date 2018-10-01 1:02
 * @Description:
 * 二，让容器自身具备比较性，自定义比较器。
 * 需求：当元素自身不具备比较性，或者元素自身具备的比较性不是所需的。
 * 那么这时只能让容器自身具备。
 * 定义一个类实现Comparator 接口，覆盖compare方法。
 * 并将该接口的子类对象作为参数传递给TreeSet集合的构造函数。
 * 当Comparable比较方式，及Comparator比较方式同时存在，以Comparator
 * 比较方式为主。
 */
public class TreeSetContainerComparable {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet(new MyComparator());
        ts.add(new Book("think in java", 100));
        ts.add(new Book("java核心技术", 75));
        ts.add(new Book("现代操作系统", 50));
        ts.add(new Book("java就业教程", 35));
        ts.add(new Book("think in java", 100));
        ts.add(new Book("ccc in java", 100));

        Iterator it = ts.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}

class MyComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Book b1 = (Book) o1;
        Book b2 = (Book) o2;
        System.out.println(b1 + "comparator " + b2);
        if (b1.getPrice() > b2.getPrice()) {
            return 1;
        }
        if (b1.getPrice() < b2.getPrice()) {
            return -1;
        }
        return b1.getName().compareTo(b2.getName());
    }
}