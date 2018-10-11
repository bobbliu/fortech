package com.liubo.base.datastructrue.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * HashMap四种遍历方式性能比较
 */
public class MapErgodicCompareExample {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < 10000; i++) {
            map.put(i, i);
        }

        //foreach循环，keySet迭代
        long start = System.currentTimeMillis();
        for (Integer key : map.keySet()) {
            map.get(key);
        }
        long end = System.currentTimeMillis();
        System.out.println("foreach循环，keySet迭代 ->" + (end - start) + "ms");

        //foreach循环，entrySet迭代
        start = System.currentTimeMillis();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            entry.getKey();
            entry.getValue();
        }
        end = System.currentTimeMillis();
        System.out.println("foreach循环，entrySet迭代 ->" + (end - start) + "ms");

        //迭代器，keySet迭代
        start = System.currentTimeMillis();
        Iterator<Integer> it = map.keySet().iterator();
        Integer key;
        while (it.hasNext()) {
            key = it.next();
            map.get(key);
        }
        end = System.currentTimeMillis();
        System.out.println("迭代器循环，keySet迭代 ->" + (end - start) + "ms");

        //迭代器，entrySet迭代
        start = System.currentTimeMillis();
        Iterator<Map.Entry<Integer, Integer>> ite = map.entrySet().iterator();
        Map.Entry<Integer, Integer> entry;
        while (ite.hasNext()) {
            entry = ite.next();
            entry.getKey();
            entry.getValue();
        }
        end = System.currentTimeMillis();
        System.out.println("迭代器循环，entrySet迭代 ->" + (end - start) + "ms");
    }
}
