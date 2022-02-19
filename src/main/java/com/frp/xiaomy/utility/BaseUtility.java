package com.frp.xiaomy.utility;

import java.io.UnsupportedEncodingException;

public class BaseUtility
{
    public static byte[] byteMerger(byte[] bt1, byte[] bt2) {
        byte[] bt3 = new byte[bt1.length + bt2.length];
        System.arraycopy(bt1, 0, bt3, 0, bt1.length);
        System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
        return bt3;
    }

    public static String print(String text) {
        byte[] b = null;
        try {
            b = text.getBytes(System.getProperty("sun.jnu.encoding"));
            return new String(b, System.getProperty("sun.jnu.encoding"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

            return null;
        }
    }
    public static byte[] intToBytes2(int n) {
        byte[] b = new byte[4];

        for (int i = 0; i < 4; i++)
        {
            b[i] = (byte)(n >> 24 - i * 8);
        }

        return b;
    }


    public static int byteToInt2(byte[] b) {
        int mask = 255;
        int temp = 0;
        int n = 0;
        for (int i = 0; i < b.length; i++) {
            n <<= 8;
            temp = b[i] & mask;
            n |= temp;
        }
        return n;
    }
}
