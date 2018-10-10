package com.liubo.base.basis._13hashcode;

import com.liubo.base.basis._12Object.EqualsExmaple;
import org.testng.annotations.Test;

import java.util.HashSet;

/**
 * hashCode()
 * hashCode() 返回散列值，而 equals() 是用来判断两个对象是否等价。
 * 等价的两个对象散列值一定相同，但是散列值相同的两个对象不一定等价。
 * <p>
 * 在覆盖 equals() 方法时应当总是覆盖 hashCode() 方法，保证等价的两个对象散列值也相等。
 * <p>
 * 下面的代码中，新建了两个等价的对象，并将它们添加到 HashSet 中。我们希望将这两个对
 * 象当成一样的，只在集合中添加一个对象，但是因为 EqualExample 没有实现 hasCode() 方法，
 * 因此这两个对象的散列值是不同的，最终导致集合添加了两个等价的对象。
 */
public class HashCodeExample {

    @Test
    public void hashCodeTest() {
        EqualsExmaple e1 = new EqualsExmaple(1, 1, 1);
        EqualsExmaple e2 = new EqualsExmaple(1, 1, 1);
        System.out.println(e1.equals(e2));

        HashSet<EqualsExmaple> set = new HashSet<>();
        set.add(e1);
        set.add(e2);
        System.out.println(set.size());
    }

}
