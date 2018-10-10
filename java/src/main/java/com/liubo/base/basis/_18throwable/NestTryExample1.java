package com.liubo.base.basis._18throwable;

/**
 * @ClassName: NestTryExample
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 12:40
 * @Description: 嵌套try语句：
 * try语句可以被嵌套。也就是说，一个try语句可以在另一个try块的内部。每次进入try语句，
 * 异常的前后关系都会被推入堆栈。如果一个内部的try语句不含特殊异常的catch处理程序，堆栈将弹出，
 * 下一个try语句的catch处理程序将检查是否与之匹配。这个过程将继续直到一个catch语句被匹配成功，
 * 或者是直到所有的嵌套try语句被检查完毕。如果没有catch语句匹配，Java运行时系统将处理这个异常。
 */
public class NestTryExample1 {
    public static void main(String[] args) {
        try {
            int a = args.length;
            int b = 42 / a;
            System.out.println("a = " + a);
            try {
                if (a == 1) {
                    a = a / (a - a);
                }
                if (a == 2) {
                    int c[] = {1};
                    c[42] = 99;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("ArrayOutOfBounds:" + e);
            }
        } catch (ArithmeticException e) {
            System.out.println("Divide by 0" + e);
        }
    }
}
