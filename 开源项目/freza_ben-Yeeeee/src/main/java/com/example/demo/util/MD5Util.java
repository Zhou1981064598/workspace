package com.example.demo.util;

import org.springframework.util.DigestUtils;

public final class MD5Util {

    private final static String salt = "Afz:23?41/jJ8";

    public static String getMd5(String message) {
        String base = message + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}
