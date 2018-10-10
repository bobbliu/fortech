package com.liubo.base.basis._18throwable;

/**
 * @ClassName: ThrowsExample
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 13:04
 * @Description: 如果一个方法可以导致一个异常但不处理它，它必须指定这种行为以使方法的
 * 调用者可以保护它们自己而不发生异常。要做到这点，我们可以在方法声明中
 * 包含一个throws子句。一个throws子句列举了一个方法可能引发的所有异常
 * 类型。这对于除了Error或RuntimeException及它们子类以外类型的所有异常
 * 是必要的。一个方法可以引发的所有其他类型的异常必须在throws子句中声明，
 * 否则会导致编译错误。
 * <p>
 * throw1()方法不想处理所导致的异常，因而它必须声明throws子句来列举可能
 * 引发的异常即IllegalAccessException；其次，main()方法必须定义try/catch
 * 语句来捕获该异常。
 *
 * Throws抛出异常的规则：
 *
 * 如果是不受检查异常（unchecked exception），即Error、RuntimeException
 * 或它们的子类，那么可以不使用throws关键字来声明要抛出的异常，编译仍能顺
 * 利通过，但在运行时会被系统抛出。
 *
 * 必须声明方法可抛出的任何检查异常（checked exception）。即如果一个方法
 * 可能出现受可查异常，要么用try-catch语句捕获，要么用throws子句声明将它
 * 抛出，否则会导致编译错误
 *
 * 仅当抛出了异常，该方法的调用者才必须处理或者重新抛出该异常。当方法的调
 * 用者无力处理该异常的时候，应该继续抛出，而不是囫囵吞枣。
 *
 * 调用方法必须遵循任何可查异常的处理和声明规则。若覆盖一个方法，则不能声
 * 明与覆盖方法不同的异常。声明的任何异常必须是被覆盖方法所声明异常的同类
 * 或子类。
 */
public class ThrowsExample {
    static void throws1() throws IllegalAccessException {
        System.out.println("Inside throw1 . ");
        throw new IllegalAccessException("demo");
    }

    public static void main(String[] args) {
        try {
            throws1();
        } catch (IllegalAccessException e) {
            System.out.println("Caught" + e);
        }
    }
}
