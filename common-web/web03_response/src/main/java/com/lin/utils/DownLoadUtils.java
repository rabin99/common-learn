package com.lin.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @auther : lin
 * @date : 2019/6/11 9:26
 * @desc : 解决兼容不容浏览器下载时出现的文件名乱码问题
 */
public class DownLoadUtils {
    public static String getFileName(String agent, String fileName) throws UnsupportedEncodingException {
        if (agent.contains("MSIE")) {
            // IE
            fileName = URLEncoder.encode(fileName, "utf-8");
            fileName = fileName.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 直接通过编码解决，BASE64Encoder无效 ，字符集默认是gbk（gb2312）
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
        }else {
            // other
            fileName = URLEncoder.encode(fileName,"utf-8");
        }
        return fileName;
    }
}
