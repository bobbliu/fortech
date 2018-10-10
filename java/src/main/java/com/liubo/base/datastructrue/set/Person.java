package com.liubo.base.datastructrue.set;

/**
 * @ClassName: Person
 * @ProjectName scalademo
 * @Author Admin
 * @Date 2018-09-30 20:34
 * @Description:
 */
public class Person {
    private String name;
    private int age;

    Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        System.out.println("hashcode: " + this.name);
        return this.name.hashCode() + age * 37;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println(this + "---equals---" + obj);
        if (obj instanceof Person) {
            Person p = (Person) obj;
            return this.name.equals(p.name) && this.age == p.age;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.getClass()+"@name:" + this.name + " age:" + this.age;
    }
}
