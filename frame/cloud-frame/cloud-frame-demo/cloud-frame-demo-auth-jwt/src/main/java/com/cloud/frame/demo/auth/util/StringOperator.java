package com.cloud.frame.demo.auth.util;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wd on 2018/5/25.
 */
public class StringOperator {

    public static Set<String> split(String str) {

        Set<String> set = new HashSet<>();
        if (StringUtils.isEmpty(str))
            return set;
        set.addAll(CollectionUtils.arrayToList(str.split(",")));
        return set;
    }
}
