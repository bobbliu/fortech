package com.liubo.base.basis._05switch;

/**
 * 从 Java 7 开始，可以在 switch 条件判断语句中使用 String 对象。
 */
public class SwitchDemo {
    public static void main(String[] args) {
        String s = "a";
        switch (s) {
            case "a":
                System.out.println("aaa");
                break;
            case "b":
                System.out.println("bbb");
                break;
        }
        /**
         * switch 不支持 long，是因为 switch 的设计初衷是对那些只有少数的几个
         * 值进行等值判断，如果值过于复杂，那么还是用 if 比较合适。
         */
        long x = 111;

/*        switch (x) {
            case 111:
                System.out.println(111);
                break;
            case 222:
                System.out.println(222);
                break;
        }*/

        if (x == 111) {
            System.out.println(111);
        } else if (x == 222) {
            System.out.println(222);
        }
    }
}
