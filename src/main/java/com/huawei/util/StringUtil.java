package com.huawei.util;

/*
 * 字符串工具类
 * */
public class StringUtil {
    /*
     * 博主id处理
     * */
    //url为博客网址
    public static String subId(String url) {
        //非空判断
        if (url != null && url.trim().length() != 0) {
            /*int index = url.lastIndexOf("/");//切割博主id
            String id = url.substring(index + 1);
            return id;*/

            int index1 = url.indexOf("https://");
            int index2 = url.indexOf(".");
            String id = url.substring(index1, index2);
            id = id.replace("https://", "");
            return id;
        } else {
            return "";
        }
    }
}
