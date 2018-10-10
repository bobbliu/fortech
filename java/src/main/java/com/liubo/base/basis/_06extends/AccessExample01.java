package com.liubo.base.basis._06extends;

/**
 * 继承
 *
 * Java 中有三个访问权限修饰符：private、protected 以及 public，如果不加访问修饰符，表示包级可见。
 *
 * 可以对类或类中的成员（字段以及方法）加上访问修饰符。
 * 类可见表示其它类可以用这个类创建实例对象。
 * 成员可见表示其它类可以用这个类的实例对象访问到该成员；
 *
 * protected 用于修饰成员，表示在继承体系中成员对于子类可见，但是这个访问修饰符对于类没有意义。
 *
 * 设计良好的模块会隐藏所有的实现细节，把它的 API 与它的实现清晰地隔离开来。模块之间只通过它们
 * 的 API 进行通信，一个模块不需要知道其他模块的内部工作情况，这个概念被称为信息隐藏或封装。
 * 因此访问权限应当尽可能地使每个类或者成员不被外界访问。
 *
 * 如果子类的方法重写了父类的方法，那么子类中该方法的访问级别不允许低于父类的访问级别。
 * 这是为了确保可以使用父类实例的地方都可以使用子类实例，也就是确保满足里氏替换原则。
 *
 * 字段决不能是公有的，因为这么做的话就失去了对这个字段修改行为的控制，客户端可以对其随意修改。
 * 例如下面的例子中，AccessExample 拥有 id 共有字段，如果在某个时刻，我们想要使用 int 去存储 id 字段，
 * 那么就需要去修改所有的客户端代码。
 */
public class AccessExample01 {
    public String id;
}