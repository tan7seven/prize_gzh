package com.prize.prize_gzh.utils;

import java.util.Formatter;
import java.util.UUID;

/**
 * 常用工具类
 */
public class UtilBean {
    //字节数组转换为十六进制字符串
    public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    //生成随机字符串
    public static String createNonceStr() {
        return UUID.randomUUID().toString();
    }
    //生成时间戳
    public static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
