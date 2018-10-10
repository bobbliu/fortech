package com.liubo.base.basis._08interfaceExample;

import org.junit.Test;

public class InterfaceImplementExample implements InterfaceExample{
    @Override
    @Test
    public void func1() {
        InterfaceExample ie2 = new InterfaceImplementExample();
        ie2.func2();
        System.out.println(InterfaceExample.x);
    }
}
