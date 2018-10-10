package com.liubo.base.basis._04floatanddouble;

import org.junit.Test;

/**
 * float 与 double
 */
public class FloatAndDouble {
    /**
     * Java 不能隐式执行向下转型，因为这会使得精度降低。
     */
    @Test
    public void floatTest() {
        //1.1 字面量属于 double 类型，不能直接将 1.1 直接赋值给 float 变量，因为这是向下转型。
        //float f = 1.1;

        //1.1f 字面量才是 float 类型。
        float f = 1.1f;
    }

    /**
     * 隐式类型转换
     * 因为字面量 1 是 int 类型，它比 short 类型精度要高，因此不能隐式地将 int 类型下转型为 short 类型。
     */
    @Test
    public void shortTest() {
        short s1 = 1;
        //s1 = s1 + 1;

        //但是使用 += 或者 ++ 运算符可以执行隐式类型转换。
        s1 += 1;
        s1++;
    }
}
