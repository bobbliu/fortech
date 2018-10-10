package com.liubo.base.basis._18throwable;

/**
 * @ClassName: ThrowExample
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 12:57
 * @Description: ThrowableInstance一定是Throwable类类型或者Throwable子类类型的一个
 * 对象。简单的数据类型，例如int，char,以及非Throwable类，例如String
 * 或Object，不能用作异常。有两种方法可以获取Throwable对象：在catch
 * 子句中使用参数或者使用new操作符创建。
 * <p>
 * 程序执行完throw语句之后立即停止；throw后面的任何语句不被执行，最邻
 * 近的try块用来检查它是否含有一个与异常类型匹配的catch语句。如果发现了
 * 匹配的块，控制转向该语句；如果没有发现，次包围的try块来检查，以此类
 * 推。如果没有发现匹配的catch块， 默认异常处理程序中断程序的执行并且打
 * 印堆栈轨迹。
 */
public class ThrowExample {
    /**
     * 该程序两次处理相同的错误，首先，main()方法设立了一个异常关系然后调
     * 用proc()。proc()方法设立了另一个异常处理关系并且立即抛出一个
     * NullPointerException实例，NullPointerException在main()中被再次捕获。
     *
     * 该程序阐述了怎样创建Java的标准异常对象，特别注意这一行：
     * throw new NullPointerException("demo");
     *
     *此处new用来构造一个NullPointerException实例，所有的Java内置的运行
     * 时异常有两个构造方法：一个没有参数，一个带有一个字符串参数。当用第
     * 二种形式时，参数指定描述异常的字符串。如果对象用作print()或者println()
     * 的参数时，该字符串被显示。这同样可以通过调用getMessage()来实现，
     * getMessage()是由Throwable定义的。
     */
    static void proc() {
        try {
            throw new NullPointerException("demo");
        } catch (NullPointerException e) {
            System.out.println("Caught inside proc");
            throw e;
        }
    }

    public static void main(String[] args) {
        try {
            proc();
        } catch (NullPointerException e) {
            System.out.println("Recaught：" + e);
        }
    }
}
