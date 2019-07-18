package com.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Description:
 *
 * @author linjh
 * @date 2018/10/16
 */
public class SortedMapTest {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap();
        map.put("1111",1);
        map.put("3333",5);
        map.put("2222",2);


        System.out.println(map.toString());
        SortedMap sortedMap = new TreeMap(map);
        System.out.println(sortedMap.toString());
        sortedMap.put("000",1);
        System.out.println(sortedMap.toString());
    }
}
