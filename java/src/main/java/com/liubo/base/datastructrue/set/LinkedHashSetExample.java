package com.liubo.base.datastructrue.set;

import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * @ClassName: LinkedHashSet
 * @ProjectName scalademo
 * @Author Admin
 * @Date 2018-10-01 1:17
 * @Description: LinkedHashSet
 * 会保存插入的顺序。
 * 看到array，就要想到角标。
 * 看到link，就要想到first，last。
 * 看到hash，就要想到hashCode,equals.
 * 看到tree，就要想到两个接口。Comparable，Comparator。
 */
public class LinkedHashSetExample {
    public static void main(String[] args) {
        LinkedHashSet lhs = new LinkedHashSet();
        lhs.add("ccc");
        lhs.add("ddd");
        lhs.add("aaa");
        lhs.add("bbb");
        lhs.add("fff");
        lhs.add("eee");

        Iterator it = lhs.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
