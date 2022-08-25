package com.shitouren.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtil {
	
	@SuppressWarnings("rawtypes")
	public static boolean isValidateList(List list){
		if (list != null && list.size() != 0) {
			return true;
		}else{
			return false;
		}
	}

    /**
     * list集合去重复
     * @param list
     * @param <T>
     * @return
     */
	public static <T> List<T> listDuplicateRemoval(List<T> list){
	    List<T> newList = new ArrayList<>();
	    if(!isValidateList(list)){
            return newList;
        }
        return newList = list.stream().distinct().collect(Collectors.toList());
    }
}
