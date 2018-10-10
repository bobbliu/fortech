package com.liubo.base.datastructrue.list;

import java.util.*;

/**
 * @ClassName: ListPerformanceExample
 * @ProjectName scalademo
 * @Author Admin
 * @Date 2018-10-09 21:40
 * @Description: List继承了Collection，是有序的列表。
 * 实现类有ArrayList、LinkedList、Vector、Stack等
 * <p>
 * ArrayList是基于数组实现的，是一个数组队列。可以动态的增加容量！
 * LinkedList是基于链表实现的，是一个双向循环列表。可以被当做堆栈使用！
 * Vector是基于数组实现的，是一个矢量队列，是线程安全的！
 * Stack是基于数组实现的，是栈，它继承与Vector，特性是FILO（先进后出）！
 * <p>
 * 在实际的应用中如果使用到队列，栈，链表，首先可以想到使用List。不同的场景下面使用不同的工具，效率才能更高！
 * 1. 当集合中对插入元素数据的速度要求不高，但是要求快速访问元素数据，则使用ArrayList！
 * 2. 当集合中对访问元素数据速度不做要求不高，但是对插入和删除元素数据速度要求高的情况，则使用LinkedList！
 * 3.当集合中有多线程对集合元素进行操作时候，则使用Vector！但是现在BVector现在一般不再使用，如需在多线程下
 * 使用，可以用CopyOnWriteArrayList，在java.util.concurrent包下。
 * 4.当集合中有需求是希望后保存的数据先读取出来，则使用Stack！
 */
public class ListPerformanceExample {
    private static final int COUNT = 100000;

    private static ArrayList<Object> arrayList = new ArrayList<>();
    private static LinkedList<Object> linkedList = new LinkedList<>();
    private static Vector<Object> vector = new Vector<>();
    private static Stack<Object> stack = new Stack<>();

    public static void main(String[] args) {
        System.out.println("开始插入元素-------->");
        insertData(arrayList, "ArrayList");
        insertData(linkedList, "LinkedList");
        insertData(vector, "Vector");
        insertData(stack, "Stack");

        System.out.println("开始读取元素-------->");
        readAccessData(arrayList, "ArrayList");
        readAccessData(linkedList, "LinkedList");
        readAccessData(vector, "Vector");
        readAccessData(stack, "Stack");

        System.out.println("开始删除元素-------->");
        deleteData(arrayList, "ArrayList");
        deleteData(linkedList, "LinkedList");
        deleteData(vector, "Vector");
        deleteData(stack, "Stack");

    }

    /**
     * @param: [list, name]
     * @return: void
     * @auther: Admin
     * @date: 2018-10-09 21:52
     * @description: 向list中插入元素
     */
    private static void insertData(List<Object> list, String name) {
        long startTime = System.currentTimeMillis();
        // 向list的位置0插入COUNT个数
        for (int i = 0; i < COUNT; i++) {
            list.add(0, i);
        }
        long endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println(name + ":插入" + COUNT + "元素，使用的时间是：" + interval + "ms");
    }

    /**
     * @param: [list, name]
     * @return: void
     * @auther: Admin
     * @date: 2018-10-09 21:56
     * @description: 删除list第一个位置的元素
     */
    private static void deleteData(List<Object> list, String name) {
        long startTime = System.currentTimeMillis();
        // 删除list第一个位置元素
        for (int i = 0; i < COUNT; i++) {
            list.remove(0);
        }
        long endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println(name + ":删除" + COUNT + "元素，使用的时间是：" + interval + "ms");
    }

    /**
     * @param: [list, name]
     * @return: void
     * @auther: Admin
     * @date: 2018-10-09 22:00
     * @description: 读取list元素
     */
    private static void readAccessData(List<Object> list, String name) {
        long startTime = System.currentTimeMillis();

        //读取list元素
        for (int i = 0; i < COUNT; i++) {
            list.get(i);
        }
        long endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println(name + ":随机读取" + COUNT + "元素，使用的时间是：" + interval + "ms");
    }
}
