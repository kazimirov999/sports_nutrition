package net.sports.nutrition.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * This class builds MD5 hash of a String.
 *
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class EncodeUtil {

    /**
     * Encoding String to md5 hash
     *
     * @param str - string for encoding
     * @return <tt>md5 hash</tt>
     */
    public static String md5Encryption(String str) {
        String md5Hex = DigestUtils.md5Hex(str);

        return md5Hex;
    }

}
