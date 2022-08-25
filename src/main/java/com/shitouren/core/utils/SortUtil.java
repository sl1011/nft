package com.shitouren.core.utils;

import java.util.Map;
import java.util.TreeMap;

public class SortUtil{
    public static Map<String, Object> sortByKey(Map<String, Object> map){
        //创建一个带有比较器的TreeMap
        Map<String, Object> treeMap = new TreeMap<>(String::compareTo);
        //将你的map传入treeMap
        treeMap.putAll(map);
        return treeMap;
    }
}
