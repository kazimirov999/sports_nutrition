package net.sports.nutrition.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 08.02.2016 15:07
 */
public class EncodeUtil {

    public static String md5Encryption(String str) {
        String md5Hex = DigestUtils.md5Hex(str);

        return md5Hex;
    }

}
