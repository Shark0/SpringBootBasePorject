package com.shark.application.util;

import java.util.List;

public class ListUtil {

    public static boolean isEmpty(List list) {
        if(list == null) {
            return true;
        }
        return list.isEmpty();
    }
}
