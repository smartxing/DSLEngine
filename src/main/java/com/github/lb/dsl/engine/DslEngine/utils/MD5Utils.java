package com.github.lb.dsl.engine.DslEngine.utils;

import java.security.MessageDigest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MD5Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MD5Utils.class);
    /**
     * 生成md5 
     *
     * @param message
     * @return
     */
    public static String getMD5(String message) {
        String md5str = "";
        try {
            // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象  
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 2 将消息变成byte数组  
            byte[] input = message.getBytes();

            // 3 计算后获得字节数组,这就是那128位了  
            byte[] buff = md.digest(input);

            // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串  
            md5str = bytesToHex(buff);

        } catch (Exception e) {
            LOGGER.error("生成MD5异常！", e);
        }
        return md5str;
    }

    /**
     * 二进制转十六进制 
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        final StringBuilder md5str = new StringBuilder();
        // 把数组每一字节换成16进制连成md5字符串  
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];

            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }
}
