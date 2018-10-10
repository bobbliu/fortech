package com.liubo.base.datastructrue.set;

/**
 * @ClassName: Book
 * @ProjectName scalademo
 * @Author Admin
 * @Date 2018-10-01 1:05
 * @Description:
 */
public class Book {
    private String name;
    private double price;

    public Book() {
    }

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book [name=" + name + ", price=" + price + "]";
    }
}
