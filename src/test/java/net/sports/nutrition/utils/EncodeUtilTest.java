package net.sports.nutrition.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 26.02.2016 12:36
 */
public class EncodeUtilTest {

    @Test
    public void testMd5Encryption() throws Exception {
        String str = "testMd5HashMethod";
        assertEquals(DigestUtils.md5Hex(str), EncodeUtil.md5Encryption(str));
    }
}