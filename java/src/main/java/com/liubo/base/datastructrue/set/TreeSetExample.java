package com.liubo.base.datastructrue.set;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @ClassName: TreeSetExample
 * @ProjectName scalademo
 * @Author Admin
 * @Date 2018-10-01 0:15
 * @Description:
 * 红黑树算法的规则: 左小右大。
 * 既然TreeSet可以自然排序,那么TreeSet必定是有排序规则的。
 * 1:让存入的元素自定义比较规则。
 *
 * 2:给TreeSet指定排序规则。
 * 方式一：元素自身具备比较性
 * 元素自身具备比较性，需要元素实现Comparable接口，重写compareTo方法，
 * 也就是让元素自身具备比较性，这种方式叫做元素的自然排序也叫做默认排序。
 * 方式二：容器具备比较性
 * 当元素自身不具备比较性，或者自身具备的比较性不是所需要的。那么此时可
 * 以让容器自身具备。需要定义一个类实现接口Comparator，重写compare方法，
 * 并将该接口的子类实例对象作为参数传递给TreeMap集合的构造方法。
 *
 * 注意：当Comparable比较方式和Comparator比较方式同时存在时，以Comparator的比较方式为主；
 *
 *注意：在重写compareTo或者compare方法时，必须要明确比较的主要条件相等时要比较次要条件。
 * （假设姓名和年龄一直的人为相同的人，如果想要对人按照年龄的大小来排序，如果年龄相同的人，
 * 需要如何处理？不能直接return 0，因为可能姓名不同（年龄相同姓名不同的人是不同的人）。此时
 * 就需要进行次要条件判断（需要判断姓名），只有姓名和年龄同时相等的才可以返回0.）
 * 通过return 0来判断唯一性。
 *
 * 问题:为什么使用TreeSet存入字符串,字符串默认输出是按升序排列的?因为字符串实现了一个接口,
 * 叫做Comparable 接口.字符串重写了该接口的compareTo 方法,所以String对象具备了比较性.那么
 * 同样道理,我的自定义元素(例如Person类,Book类)想要存入TreeSet集合,就需要实现该接口,也就是
 * 要让自定义对象具备比较性.
 * 存入TreeSet集合中的元素要具备比较性.
 * 比较性要实现Comparable接口，重写该接口的compareTo方法
 *
 *TreeSet属于Set集合，该集合的元素是不能重复的，TreeSet如何保证元素的唯一性
 * 通过compareTo或者compare方法中的来保证元素的唯一性。
 * 添加的元素必须要实现Comparable接口。当compareTo()函数返回值为0时，说明两个对象相等，此时该对象不会添加进来。
 *
 * 比较器接口
 * ----| Comparable
 *        		compareTo(Object o)     元素自身具备比较性
 * ----| Comparator
 *        		compare( Object o1, Object o2 )	给容器传入比较器
 */
public class TreeSetExample {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet();
        ts.add("ccc");
        ts.add("aaa");
        ts.add("ddd");
        ts.add("bbb");
        System.out.println(ts);
        System.out.println(ts.size());
        Iterator it = ts.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
