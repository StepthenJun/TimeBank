package com.example.api.util;

/**
 * @program: ledger
 * @ClassName: ConvertUtil
 * @description:Convert Data
 * @author: kai
 * @create: 2024-02-21 20:54
 */
public class ConvertUtil {

    public static String byteToArray(byte[]data) {
        String result = "0x";
        for (int i = 0; i < data.length; i++) {
            result += Integer.toHexString((data[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3);
        }
        return result;

    }

    public static int byteToInt(byte data) {
        return data & 0xff;
    }

}
