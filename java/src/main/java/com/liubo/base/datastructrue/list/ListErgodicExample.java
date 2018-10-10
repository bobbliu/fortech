package com.liubo.base.datastructrue.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListErgodicExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        list.add("HAHAHAHA");

        //第一种遍历，使用Foreach遍历list
        for(String str : list){
            System.out.println(str);
        }

        //第二种遍历，把链表变为数组的内容进行遍历
        String[] strArray = new String[list.size()];
        list.toArray(strArray);
        for(int i = 0; i<strArray.length; i++){
            System.out.println(strArray[i]);
        }

        //第三种遍历，使用迭代器进行遍历
        //三种方法都是用来遍历ArrayList集合，第三种方法是采用迭代器的方法，该方法可以不用担心在遍历的过程中会超出集合的长度。
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
