package net.sports.nutrition.utils;

import org.springframework.ui.Model;

import java.io.Serializable;

/**
 * This class writes message to the Model.
 * <p>Default attribute name - serviceMessage.</p>
 *
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class ServiceMessage implements Serializable {

    private static final String attribute = "serviceMessage";

    private ServiceMessage() {
    }

    /**
     * Writes message to the Model.
     *
     * @param model   - holder for model attributes
     * @param message - recording message
     * @see ServiceMessage#write(Model, String, String)
     */
    public static void write(Model model, String message) {
        model.addAttribute(attribute, message);
    }

    /**
     * Writes message to the Model width custom attribute name.
     *
     * @param model           - holder for model attributes
     * @param customAttribute -  name of the attribute
     * @param message         - recording message
     * @see ServiceMessage#write(Model, String)
     */
    public static void write(Model model, String customAttribute, String message) {
        model.addAttribute(customAttribute, message);
    }

}
