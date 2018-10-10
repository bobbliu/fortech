package com.liubo.base.basis._16keywords;

/**
 * @ClassName: StaticA7
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 11:40
 * @Description:
 * 6. 初始化顺序
 * 静态变量和静态语句块优先于实例变量和普通语句块，静态变量和静态语句块的初始化顺序取决于它们在代码中的顺序。
 *
 * 存在继承的情况下，初始化顺序为：
 * 父类（静态变量、静态语句块）
 * 子类（静态变量、静态语句块）
 * 父类（实例变量、普通语句块）
 * 父类（构造函数）
 * 子类（实例变量、普通语句块）
 * 子类（构造函数
 */
public class StaticA7 {

    public static String staticField = "静态变量";

    static{
        System.out.println("静态语句块");
    }

    public String field = "实例变量";

    {
        System.out.println("普通语句块");
    }

    //最后才是构造函数的初始化。

    public StaticA7(){
        System.out.println("构造函数");
    }

}
