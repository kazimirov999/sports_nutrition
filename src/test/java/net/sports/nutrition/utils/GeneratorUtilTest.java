package net.sports.nutrition.utils;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;



/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 26.02.2016 12:42
 */
public class GeneratorUtilTest {

    @Test
    public void testGenerateId() throws Exception {
       Set<String> generateValues = new HashSet();
        for (int i = 0; i < 100; i++) {
            String str = GeneratorUtil.generateId();
            assertEquals(8, str.length());
            assertFalse(generateValues.contains(str));
            generateValues.add(str);
        }
    }
}