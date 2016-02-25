package net.sports.nutrition.utils;

import org.springframework.ui.Model;

import java.io.Serializable;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 28.01.2016 9:38
 */
public class ServiceMessage  implements Serializable {

    private static final String attribute = "serviceMessage";

    private ServiceMessage(){}

    public static void  write(Model model,String message){
        model.addAttribute(attribute,message);
    }

    public static void  write(Model model,String customAttribute,String message){
        model.addAttribute(customAttribute,message);
    }

}
