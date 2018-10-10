package com.liubo.base.basis._10super;

public class SuperExtendsExample extends SuperExample {

    private int z;

    public static void main(String[] args) {
        SuperExample e = new SuperExtendsExample(1, 2, 3);
        e.func();
    }

    public SuperExtendsExample(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    @Override
    public void func() {
        super.func();
        System.out.println("SuperExtendsExample.func()");
    }
}
