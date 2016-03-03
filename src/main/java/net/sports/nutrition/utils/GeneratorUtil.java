package net.sports.nutrition.utils;

import java.util.Random;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 08.02.2016 15:15
 */

public class GeneratorUtil {

    public static String generateId(){
        Random rand = new Random();

        return String.valueOf(rand.nextInt(80000000)+10000000);
    }
}
