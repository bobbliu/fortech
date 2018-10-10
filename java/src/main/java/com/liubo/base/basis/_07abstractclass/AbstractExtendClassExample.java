package com.liubo.base.basis._07abstractclass;

import org.junit.Test;

public class AbstractExtendClassExample extends AbstractClassExample {

    @Override
    @Test
    public void func1() {
        // 'AbstractClassExample' is abstract; cannot be instantiated
        // AbstractClassExample ac1 = new AbstractClassExample();
        AbstractClassExample ac2 = new AbstractExtendClassExample();
        ac2.func2();
    }
}
