package net.sports.nutrition.utils;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 28.01.2016 11:07
 */
public class ServiceRedirectMessage{

    private static final String attribute = "serviceMessage";

    private ServiceRedirectMessage() {}

    public static void write(RedirectAttributes redirect, String message) {
       redirect.addFlashAttribute(attribute, message);
    }
    public static void write(RedirectAttributes redirect,String customAttribute, String message) {
        redirect.addFlashAttribute(customAttribute, message);
    }


}
