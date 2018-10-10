package com.liubo.base.datastructrue.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName: HashSetExample
 * @ProjectName scalademo
 * @Author Admin
 * @Date 2018-09-30 20:09
 * @Description: HashSet在放入集合前首先要进行元素重复的判断，判断方法是先进行hashcode
 * 的判断，如果hashcode相同，再进行equals方法判断，如果equals不同，放入
 * 集合，如果equals相同，不允许重复元素写入；如果hashcode不相同，就不再进
 * 行equals的判断了，可以写入集合。
 * 一个hashcode的位置可以存放多个元素，存储的元素为hashcode相同但equals
 * 不同的元素。
 * <p>
 * 判断重复元素：HashSet使用hashCode和equals方法，ArrayList使用了equals方法
 */
public class HashSetExample {
    public static void main(String[] args) {
        //Set 集合存和取的顺序不一致
        Set hs = new HashSet();
        hs.add("世界军事");
        hs.add("舰船知识");
        hs.add("兵器知识");
        hs.add("汉和防务");

        // [世界军事, 汉和防务, 兵器知识, 舰船知识]
        System.out.println(hs);

        //返回集合中元素的数量
        System.out.println(hs.size());

        //如果集合中尚未包含指定元素，返回true
        boolean add = hs.add("世界军事");       //false，未写入集合
        System.out.println(add);
        System.out.println(hs.size());

        Iterator it = hs.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
