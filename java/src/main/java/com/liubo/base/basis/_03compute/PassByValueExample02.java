package com.liubo.base.basis._03compute;

/**
 * 但是如果在方法中改变对象的字段值会改变原对象该字段值，因为改变的是同一个地址指向的内容。
 */
class PassByValueExample02 {
    public static void main(String[] args){
        Dog dog = new Dog("A");
        func(dog);
        System.out.println(dog.getName());
    }
    private static void func(Dog dog){
        dog.setName("B");
    }
}
