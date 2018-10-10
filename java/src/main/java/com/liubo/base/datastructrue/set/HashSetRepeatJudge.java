package com.liubo.base.datastructrue.set;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @ClassName: HashSetRepeatJudge
 * @ProjectName scalademo
 * @Author Admin
 * @Date 2018-09-30 20:33
 * @Description:
 * 使用HashSet存储自定义对象，并尝试添加重复对象（对象的重复的判定）
 * 注意hashcode && equals方法的重写
 * ArrayList 、 LinkedList不能去除重复数据。HashSet可以去除重复，但是无序。
 */
public class HashSetRepeatJudge {
    public static void main(String[] args) {
        HashSet hs =new HashSet();
        hs.add(new Person("jack",20));
        hs.add(new Person("rose",20));
        hs.add(new Person("hmm",20));
        hs.add(new Person("lilei",20));
        hs.add(new Person("jack",20));

        Iterator it = hs.iterator();
        while (it.hasNext()){
            Object next = it.next();
            System.out.println(next);
        }
    }
}