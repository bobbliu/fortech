package com.liubo.base.datastructrue.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: ListExample
 * @ProjectName scalademo
 * @Author Admin
 * @Date 2018-10-09 21:17
 * @Description: List的3个特性
 * 1.是按顺序查找
 * 2.允许存储项为空
 * 3.允许多个存储项的值相等
 *
 * ArrayList是由数组实现的，方便查找，返回数组下标对应的值即可，适用于多查找的场景
 * LinkedList由链表实现，插入和删除方便，适用于多次数据替换的场景
 */
public class ListExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("zero");
        list.add(null);
        list.add("two");
        list.add(null);
        list.add("three");

        Iterator it = list.iterator();
        while (it.hasNext()) {
            //System.out.println(list.indexOf(it.next()) +":"+ it.next());
            System.out.println(it.next());
        }
    }
}
