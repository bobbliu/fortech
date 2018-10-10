package com.liubo.base.basis._18throwable;

/**
 * @ClassName: FinallyExample
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 13:13
 * @Description: 4. finally
 * 当异常发生时，通常方法的执行将做一个陡峭的非线性的转向，它甚至会过早
 * 的导致方法返回。例如，如果一个方法打开了一个文件并关闭，然后退出，你
 * 不希望关闭文件的代码被异常处理机制旁路。finally关键字为处理这种意外而
 * 设计。
 * <p>
 * finally创建的代码块在try/catch块完成之后另一个try/catch出现之前执行。
 * finally块无论有没有异常抛出都会执行。如果抛出异常，即使没有catch子句
 * 匹配，finally也会执行。一个方法将从一个try/catch块返回到调用程序的任何
 * 时候，经过一个未捕获的异常或者是一个明确的返回语句，finally子句在方法
 * 返回之前仍将执行。这在关闭文件句柄和释放任何在方法开始时被分配的其他
 * 资源是很有用
 * <p>
 * finally子句是可选项，可以有也可以无，但是每个try语句至少需要一个catch
 * 或者finally子句。
 *
 * 如果finally块与一个try联合使用，finally块将在try结束之前执行。
 */
public class FinallyExample {
    static void proc1() {
        try {
            System.out.println("Inside proc1");
            throw new RuntimeException("demo");
        } finally {
            System.out.println("proc1's finally");
        }
    }

    static void proc2(){
        try{
            System.out.println("Inside proc2");
            return;
        }finally {
            System.out.println("proc2's finally");
        }
    }

    static void proc3(){
        try{
            System.out.println("Inside proc3");
        }finally {
            System.out.println("proc3's finally");
        }
    }

    public static void main(String[] args) {
        try{
            proc1();
        }catch (Exception e){
            System.out.println("Exception caught");
        }
        proc2();
        proc3();
    }
}
