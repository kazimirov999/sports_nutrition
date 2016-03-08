package net.sports.nutrition.utils;

import java.util.Random;

/**
 * This class generates random number.
 *
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class GeneratorUtil {

    /**
     * Returns random number with eight digits.
     *
     * @return random number
     */
    public static String generateId() {
        Random rand = new Random();

        return String.valueOf(rand.nextInt(80000000) + 10000000);
    }
}
