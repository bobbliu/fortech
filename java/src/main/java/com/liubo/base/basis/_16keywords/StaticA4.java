package com.liubo.base.basis._16keywords;

/**
 * @ClassName: StaticA4
 * @ProjectName scalademo
 * @Description: 3. 静态语句块 静态语句块在类初始化时运行一次。
 * @Author liubo
 * @Date 2018-09-30 11:31
 */
public class StaticA4 {
    static{
        System.out.println("123");
    }

    public static void main(String[] args) {
        StaticA4 a1 = new StaticA4();
        StaticA4 a2 = new StaticA4();
    }
}
