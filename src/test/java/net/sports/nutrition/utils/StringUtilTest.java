package net.sports.nutrition.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 26.02.2016 0:29
 */
public class StringUtilTest {

    @Test
    public void testReplaceString() throws Exception {
        StringBuilder str = new StringBuilder("You {address} is {addressValue}");
        String result = StringUtil.replaceString(str, "addressValue", "vasia@ak.net");
        assertEquals("You {address} is vasia@ak.net", result);
        assertEquals("You email is vasia@ak.net", StringUtil.replaceString(new StringBuilder(result), "address", "email"));

    }
}