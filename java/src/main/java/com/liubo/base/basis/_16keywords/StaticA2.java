package com.liubo.base.basis._16keywords;
/**

/**
 * 2. 静态方法
 *
 * 静态方法在类加载的时候就存在了，它不依赖于任何实例。所以静态方法必须有实现，也就是说它不能是抽象方法。
 */
public abstract  class StaticA2 {
    public static void func1(){
    }

    //public abstract static void fun2();         // Illegal combination of modifiers: 'abstract' and 'static'
}
