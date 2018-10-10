package com.liubo.base.datastructrue.set;

/**
 * @ClassName: PersonForTreeSetElemComparable
 * @ProjectName scalademo
 * @Author Admin
 * @Date 2018-10-01 0:27
 * @Description:
 */
public class PersonForTreeSetElemComparable implements Comparable {
    private String name;
    private int age;
    private String gender;

    PersonForTreeSetElemComparable() {
    }

    public PersonForTreeSetElemComparable(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + age * 37;
    }

    @Override
    public boolean equals(Object obj) {
        System.err.println(this + "equals :" + obj);
        if (!(obj instanceof PersonForTreeSetElemComparable)) {
            return false;
        }
        PersonForTreeSetElemComparable p = (PersonForTreeSetElemComparable) obj;
        return this.name.equals(p.name) && this.age == p.age;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", gender=" + gender + "]";
    }

    @Override
    public int compareTo(Object obj) {
        PersonForTreeSetElemComparable p = (PersonForTreeSetElemComparable) obj;
        System.out.println(this + " compareTo:" + p);
        if (this.age > p.age) {
            return 1;
        }
        if (this.age < p.age) {
            return -1;
        }
        return this.name.compareTo(p.name);
    }
}
