package com.liubo.base.basis._19generics;

/**
 * @ClassName: Node
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 15:53
 * @Description: 类型擦除
 * Java泛型中最令人苦恼的地方或许就是类型擦除了，特别是对于有C++经验的
 * 程序员。类型擦除就是说Java泛型只能用于在编译期间的静态类型检查，然后
 * 编译器生成的代码会擦除相应的类型信息，这样到了运行期间实际上JVM根本
 * 就知道泛型所代表的具体类型。这样做的目的是因为Java泛型是1.5之后才被
 * 引入的，为了保持向下的兼容性，所以只能做类型擦除来兼容以前的非泛型代
 * 码。对于这一点，如果阅读Java集合框架的源码，可以发现有些类其实并不支持泛型。
 *
 * 编译器做完相应的类型检查之后，实际上到了运行期间上面这段代码实际上将转换成Object ：
 *
 * 这意味着不管我们声明Node<String>还是Node<Integer>，到了运行期间，
 * JVM统统视为Node<Object>。有没有什么办法可以解决这个问题呢？这就
 * 需要我们自己重新设置bounds了，将上面的代码修改成下面这样：
 *
 * public class Node<T extends Comparable<T>> {
 *     private T data;
 *     private Node<T> next;
 *     public Node(T data, Node<T> next) {
 *         this.data = data;
 *         this.next = next;
 *     }
 *     public T getData() { return data; }
 *     // ...
 * }
 *
 * 这样编译器就会将T出现的地方替换成Comparable而不再是默认的Object了：
 * public class Node {
 *     private Comparable data;
 *     private Node next;
 *     public Node(Comparable data, Node next) {
 *         this.data = data;
 *         this.next = next;
 *     }
 *     public Comparable getData() { return data; }
 *     // ...
 * }
 *
 * 上面的概念或许还是比较好理解，但其实泛型擦除带来的问题远远不止这些，
 * 接下来我们系统地来看一下类型擦除所带来的一些问题，有些问题在C++的
 * 泛型中可能不会遇见，但是在Java中却需要格外小心。
 */
public class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
