package net.sports.nutrition.utils;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This class writes message to the RedirectAttributes.
 * <p>Default attribute name - serviceMessage.</p>
 *
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class ServiceRedirectMessage {

    private static final String attribute = "serviceMessage";

    private ServiceRedirectMessage() {
    }

    /**
     * Writes message to the RedirectAttributes.
     *
     * @param redirect - holder for  RedirectAttributes
     * @param message  - recording message
     * @see ServiceRedirectMessage#write(RedirectAttributes, String, String)
     */
    public static void write(RedirectAttributes redirect, String message) {
        redirect.addFlashAttribute(attribute, message);
    }

    /**
     * Writes message to the RedirectAttributes width custom attribute name.
     *
     * @param redirect        - holder for  RedirectAttributes
     * @param customAttribute - name of the attribute
     * @param message         - recording message
     * @see ServiceRedirectMessage#write(RedirectAttributes, String, String)
     */
    public static void write(RedirectAttributes redirect, String customAttribute, String message) {
        redirect.addFlashAttribute(customAttribute, message);
    }


}
