package com.liubo.base.basis._16keywords;

/**
 * 只能访问所属类的静态字段和静态方法，方法中不能有 this 和 super 关键字。
 */
public class StaticA3 {
    private static int x;
    private int y;

    public static void func1(){
        int a = x;
        //int b = y;                  // Non-static field 'y' cannot be referenced from a static context
        //int b = this.y;             // 'A.this' cannot be referenced from a static context
    }

}
