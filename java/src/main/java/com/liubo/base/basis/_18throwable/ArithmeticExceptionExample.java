package com.liubo.base.basis._18throwable;

import org.junit.Test;

/**
 * @ClassName: ArithmeticExceptionExample
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 12:25
 * @Description: 算术异常
 * 算术异常属于运行时异常，因而实际上该异常不需要程序抛出，运行时系统自动抛出
 */
public class ArithmeticExceptionExample {
    @Test
    public void programThrows() {
        int a = 1;
        int b = 0;
        try {       // try监控区域
            if (b == 0) throw new ArithmeticException();        // 通过throw语句抛出异常
            System.out.println("a/b的值是：" + a / b);
            System.out.println("this will not be printed!");

        } catch (ArithmeticException e) {       //catch捕捉异常
            System.out.println("程序出现异常，变量b不能为0" + e);
        }
        System.out.println("程序正常结束");
    }

    @Test
    public void runtimeAutoThrows() {
        int a = 1;
        int b = 0;
        System.out.println("a/b的值是：" + a / b);
        System.out.println("this will not be printed!");
    }
}
