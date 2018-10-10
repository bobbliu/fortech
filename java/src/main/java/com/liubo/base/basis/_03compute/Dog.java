package com.liubo.base.basis._03compute;

/**
 * 参数传递
 */
public class Dog {
    String name;

    Dog(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getObjectAddress(){
        return super.toString();
    }
}
