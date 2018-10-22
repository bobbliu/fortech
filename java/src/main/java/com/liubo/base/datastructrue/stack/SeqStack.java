package com.liubo.base.datastructrue.stack;

import java.io.Serializable;
import java.util.EmptyStackException;

/**
 * 栈(Stack)是一种有序特殊的线性表，只能在表的一端(称为栈顶，top，总是指向栈顶元素)执行插入和删除操作，
 * 最后插入的元素将第一个被删除，因此栈也称为后进先出(Last In First Out,LIFO)或先进后出(First In Last Out FILO)
 * 的线性表。栈的基本操作创建栈，判空，入栈，出栈，获取栈顶元素等，注意栈不支持对指定位置进行删除，插入
 * <p>
 * MyArrayList作为基础来实现顺序栈
 */

public class SeqStack<T> implements Stack<T>, Serializable {
    private static final long serialVersionUID = 3976189037434701197L;

    /**
     * 栈顶指针，-1代空栈
     */
    private int top = -1;

    /**
     * 容量大小默认为10
     */
    private int capacity = 10;

    /**
     * 存放元素的数组
     */
    private T[] array;
    private int size;

    public SeqStack(int capacity) {
        array = (T[]) new Object[capacity];
    }

    public SeqStack() {
        array = (T[]) new Object[this.capacity];
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        SeqStack<String> s = new SeqStack<>();
        s.push("A");
        s.push("B");
        s.push("C");
        System.out.println("size->" + s.size());
        int l = s.size();//size在减少，必须先记录
        for (int i = 0; i < l; i++) {
            System.out.println("s.pop->" + s.pop());
        }
       System.out.println("s.peek->" + s.peek());
    }

    @Override
    public boolean isEmpty() {
        return this.top == -1;
    }

    /**
     * 添加元素，从栈顶（数组尾部）插入
     *
     * @param data
     */
    @Override
    public void push(T data) {
        //判断容量是否充足
        if (array.length == size)
            ensureCapacity(size * 2 + 1);
        //从栈顶添加元素
        array[++top] = data;
        size++;
    }

    /**
     * 获取栈顶元素，不删除
     *
     * @return
     */
    @Override
    public T peek() {
        if (isEmpty())
            new EmptyStackException();
        return array[top];
    }

    /**
     * 从栈顶（顺序表尾部）删除
     *
     * @return
     */
    @Override
    public T pop() {
        if (isEmpty())
            new EmptyStackException();
        size--;
        return array[top--];
    }

    /**
     * 扩容方法
     *
     * @param capacity
     */
    public void ensureCapacity(int capacity) {
        //如果需要拓展的容量比现在数组的容量还小,则无需扩容
        if (capacity < size)
            return;

        T[] old = array;
        array = (T[]) new Object[capacity];
        //复制元素
        for (int i = 0; i < size; i++) {
            array[i] = old[i];
        }
    }
}
