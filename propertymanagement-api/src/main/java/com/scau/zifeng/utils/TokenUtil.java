package com.scau.zifeng.utils;

import java.util.Random;

public class TokenUtil {
    public static synchronized String genUniqueKey(String username) throws Exception {
        Random random = new Random();
        int number = random.nextInt(900000) + 100000;
        String key = System.currentTimeMillis() + String.valueOf(number);
        return Md5Util.md5(username,key);
    }
}
