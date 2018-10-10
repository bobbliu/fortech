package com.liubo.base.basis._17reflection;

/**
 * @ClassName: ReflectionExample
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 11:55
 * @Description:
 * 七、反射
 * 每个类都有一个 Class 对象，包含了与类有关的信息。当编译一个新类时，会产生一个同名的 .class 文件，
 * 该文件内容保存着 Class 对象。
 *
 * 类加载相当于 Class 对象的加载，类在第一次使用时才动态加载到 JVM 中。也可以使用
 * Class.forName("com.mysql.jdbc.Driver") 这种方式来控制类的加载，该方法会返回一个 Class 对象。
 *
 * 反射可以提供运行时的类信息，并且这个类可以在运行时才加载进来，甚至在编译时期该类的 .class 不存在也可以加载进来。
 *
 * Class 和 java.lang.reflect 一起对反射提供了支持，java.lang.reflect 类库主要包含了以下三个类：
 *
 * Field ：可以使用 get() 和 set() 方法读取和修改 Field 对象关联的字段；
 * Method ：可以使用 invoke() 方法调用与 Method 对象关联的方法；
 * Constructor ：可以用 Constructor 创建新的对象。
 */
public class ReflectionExample {

}
