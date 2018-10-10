package com.liubo.base.basis._18throwable;

/**
 * @ClassName: CustomExceptionExampleTest
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 13:30
 * @Description:
 */
public class CustomExceptionExampleTest {
    static void compute(int a) throws CustomExceptionExample {
        System.out.println("Called compute(" + a + ")");
        if (a > 10) {
            throw new CustomExceptionExample(a);
        }
    }

    public static void main(String[] args) {
        try {
            compute(1);
            compute(20);
        } catch (CustomExceptionExample me) {
            System.out.println("Caught " + me);
        }
    }
}
