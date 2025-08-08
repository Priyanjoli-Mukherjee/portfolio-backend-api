package com.heroku.java.utils;

import java.util.ArrayList;
import java.util.HashSet;

public class ListUtils {
    public static <T> ArrayList<T> unique(ArrayList<T> arr) {
        HashSet<T> set = new HashSet<>();
        ArrayList<T> result = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            T ele = arr.get(i);
            if (!set.contains(ele)) {
                set.add(ele);
                result.add(ele);
            }
        }
        return result;
    }
}
