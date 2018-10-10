package com.liubo.base.datastructrue.set;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @ClassName: TreeSetElemComparable
 * @ProjectName scalademo
 * @Author Admin
 * @Date 2018-10-01 0:25
 * @Description: TreeSet集合排序的两种方式：
 * 一，让元素自身具备比较性。
 * 也就是元素需要实现Comparable接口，覆盖compareTo 方法。
 * 这种方式也作为元素的自然排序，也可称为默认排序。
 * 年龄按照首要条件，年龄相同再比姓名。
 */
public class TreeSetElemComparable {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet();
        ts.add(new PersonForTreeSetElemComparable("cc", 17, "男"));
        ts.add(new PersonForTreeSetElemComparable("aa", 20, "男"));
        ts.add(new PersonForTreeSetElemComparable("dd", 15, "女"));
        ts.add(new PersonForTreeSetElemComparable("bb", 18, "女"));
        ts.add(new PersonForTreeSetElemComparable("dd", 17, "女"));
        ts.add(new PersonForTreeSetElemComparable("dd", 15, "女"));

        Iterator it = ts.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
        System.out.println(ts.size());
    }
}
