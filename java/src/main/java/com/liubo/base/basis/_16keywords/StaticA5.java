package com.liubo.base.basis._16keywords;

/**
 * @ClassName: StaticA5
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 11:34
 * @Description:
 * 4. 静态内部类 非静态内部类依赖于外部类的实例，而静态内部类不需要。
 * 静态内部类不能访问外部类的非静态的变量和方法。
 */
public class StaticA5 {
    class InnerClass{
    }

    static class StaticInnerClass{
    }

    public static void main(String[] args) {
        //InnerClass innerClass = new InnerClass();       // 'StaticA5.this' cannot be referenced from a static context
        StaticA5 outerClass = new StaticA5();
        InnerClass innerClass = outerClass.new InnerClass();
        StaticInnerClass staticInnerClass = new StaticInnerClass();
    }
}
