package net.sports_nutrition.utils;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 08.02.2016 15:15
 */
public class GeneratorUtil {

    public static String generateId(){
        return (EncodeUtil.md5Encryption(String.valueOf(System.currentTimeMillis()))).substring(0,8);
    }
}
