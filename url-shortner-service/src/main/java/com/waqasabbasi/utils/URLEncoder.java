package com.waqasabbasi.utils;


public class URLEncoder {

    private static final char[] charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    //Base 62 Encoder. Takes Counter Int and returns Base62 encoded string
    public static String encode(int counter) {

        int size = counter;

        StringBuilder tinyUrl = new StringBuilder();

        while (size > 0) {
            tinyUrl.append(charSet[size % 62]);
            size = size / 62;
        }

        return tinyUrl.toString();

    }
}
