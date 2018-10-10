package com.liubo.base.basis._18throwable;

/**
 * @ClassName: NestTryExample2
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 12:50
 * @Description:
 * 当有方法调用时，try语句的嵌套可以很隐蔽的发生。例如，我们可以将对方
 * 法的调用放在一个try块中。在该方法的内部，有另一个try语句。在这种情
 * 况下，方法内部的try仍然是嵌套在外部调用该方法的try块中的。下面我们
 * 将对上述例子进行修改，嵌套的try块移到方法nesttry()的内部：
 */
public class NestTryExample2 {
    static void nestTry(int a){
        try{
            if(a == 1){
                a = a / (a - a);
            }
            if(a == 2){
                int c[] = {1};
                c[42] = 99;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("ArrayOutOfBounds:" + e);
        }
    }

    public static void main(String[] args) {
        try{
            int a = args.length;
            int b = 42 / a;
            System.out.println("a = " + a);
            nestTry(a);
        }catch(ArithmeticException e){
            System.out.println("Divide by 0" + e);
        }
    }
}
