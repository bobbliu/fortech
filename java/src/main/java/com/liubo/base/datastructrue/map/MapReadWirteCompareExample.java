package com.liubo.base.datastructrue.map;

import java.util.*;

/**
 * HashMap
 * 最常用的Map,它根据键的HashCode 值存储数据,根据键可以直接获取它的值，具有很快的访问速度。
 * HashMap最多只允许一条记录的键为Null(多条会覆盖);允许多条记录的值为 Null。非同步的。
 * <p>
 * TreeMap
 * 能够把它保存的记录根据键(key)排序,默认是按升序排序，也可以指定排序的比较器，当用Iterator
 * 遍历TreeMap时，得到的记录是排过序的。TreeMap不允许key的值为null。非同步的。
 * <p>
 * Hashtable
 * 与 HashMap类似,不同的是:key和value的值均不允许为null;它支持线程的同步，即任一时刻只有
 * 一个线程能写Hashtable,因此也导致了Hashtale在写入时会比较慢。
 * <p>
 * LinkedHashMap
 * 保存了记录的插入顺序，在用Iterator遍历LinkedHashMap时，先得到的记录肯定是先插入的.
 * 在遍历的时候会比HashMap慢。key和value均允许为空，非同步的。
 */
public class MapReadWirteCompareExample {
    static int hashMapW = 0;
    static int hashMapR = 0;
    static int linkMapW = 0;
    static int linkMapR = 0;
    static int treeMapW = 0;
    static int treeMapR = 0;
    static int hashTableW = 0;
    static int hashTableR = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            MapReadWirteCompareExample mrwce = new MapReadWirteCompareExample();
            mrwce.mrwce(100 * 10000);
            System.out.println();
        }
        System.out.println("hashMapW = " + hashMapW / 10);
        System.out.println("hashMapR = " + hashMapR / 10);
        System.out.println("linkMapW = " + linkMapW / 10);
        System.out.println("linkMapR = " + linkMapR / 10);
        System.out.println("treeMapW = " + treeMapW / 10);
        System.out.println("treeMapR = " + treeMapR / 10);
        System.out.println("hashTableW = " + hashTableW / 10);
        System.out.println("hashTableR = " + hashTableR / 10);
    }

    public void mrwce(int size) {
        int index;
        Random random = new Random();
        String[] key = new String[size];

        //hashMap插入
        Map<String, String> map = new HashMap<String, String>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            key[i] = UUID.randomUUID().toString();
            map.put(key[i], UUID.randomUUID().toString());
        }
        long end = System.currentTimeMillis();
        hashMapW += (end - start);
        System.out.println("HashMap插入耗时 = " + (end - start) + "ms");

        //hashMap读取
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(size);
            map.get(key[index]);
        }
        end = System.currentTimeMillis();
        hashMapR += (end - start);
        System.out.println("HashMap读取耗时 = " + (end - start) + "ms");

        //linkedHashMap插入
        map = new LinkedHashMap<String, String>();
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            key[i] = UUID.randomUUID().toString();
            map.put(key[i], UUID.randomUUID().toString());
        }
        end = System.currentTimeMillis();
        linkMapW += (end - start);
        System.out.println("LinkedHashMap插入耗时 = " + (end - start) + "ms");

        //linkedHashMap读取
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(size);
            map.get(key[index]);
        }
        end = System.currentTimeMillis();
        linkMapR += (end - start);
        System.out.println("LinkedHashMap读取耗时 = " + (end - start) + "ms");

        //treeMap插入
        key = new String[size];
        map = new TreeMap<String, String>();
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            key[i] = UUID.randomUUID().toString();
            map.put(key[i], UUID.randomUUID().toString());
        }
        end = System.currentTimeMillis();
        treeMapW += (end - start);
        System.out.println("TreeMap插入耗时 = " + (end - start) + "ms");

        //treeMap读取
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(size);
            map.get(key[index]);
        }
        end = System.currentTimeMillis();
        treeMapR += (end - start);
        System.out.println("TreeMap读取耗时 = " + (end - start) + "ms");

        //hashTable插入
        key = new String[size];
        map = new Hashtable<String, String>();
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            key[i] = UUID.randomUUID().toString();
            map.put(key[i], UUID.randomUUID().toString());
        }
        end = System.currentTimeMillis();
        hashTableW += (end - start);
        System.out.println("HashTable插入耗时 = " + (end - start) + "ms");

        //hashTable读取
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(size);
            map.get(key[index]);
        }
        end = System.currentTimeMillis();
        hashTableR += (end - start);
        System.out.println("HashTable读取耗时 = " + (end - start) + "ms");
    }
}