package com.liubo.base.datastructrue.list;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapErgodicExample {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");

        //第一种，普片使用，二次取值，通过Map.keySet遍历key和value
        for (String key : map.keySet()) {
            System.out.println("key= " + key + " and value= " + map.get(key));
        }

        //第二种，通过Map.entrySet使用iterator遍历key和value
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第三种，推荐使用，尤其容量大时
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第四种，通过map.values遍历所有value，但不能遍历key
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }
    }
}
