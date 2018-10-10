package com.liubo.base.basis._19generics;

import java.util.ArrayList;

/**
 * @ClassName: ErasedTypeEquivalence
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 16:02
 * @Description:
 *在Java中不允许创建泛型数组，类似下面这样的做法编译器会报错：
 *
 * 假设我们支持泛型数组的创建，由于运行时期类型信息已经被擦除，JVM实
 * 际上根本就不知道new ArrayList<String>()和new ArrayList<Integer>()的
 * 区别。类似这样的错误假如出现才实际的应用场景中，将非常难以察觉。
 */
public class ErasedTypeEquivalence {
    public static void main(String[] args) {
        //List<Integer>[] arrayOfLists = new List<Integer>[2];  // compile-time error

        //字符串数组不能存放整型元素，而且这样的错误往往要等到代码运行的
        // 时候才能发现，编译器是无法识别的。
        Object[] strings = new String[2];
        strings[0] = "hi";   // OK
        //strings[1] = 100;    // java.lang.ArrayStoreException: java.lang.Integer

        //Object[] stringLists = new List<String>[];      // compiler error, but pretend it's allowed
        //stringLists[0] = new ArrayList<String>();       // OK
        // An ArrayStoreException should be thrown, but the runtime can't detect it.
        //stringLists[1] = new ArrayList<Integer>();

        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2);
    }
}
