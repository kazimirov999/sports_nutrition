package net.sports_nutrition.utils.converters;

import net.sports_nutrition.domain.entities.Taste;
import net.sports_nutrition.services.ITasteService;
import org.jboss.logging.Logger;

import java.beans.PropertyEditorSupport;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 30.01.2016 21:15
 */
public class TasteEditor extends PropertyEditorSupport {

    private static final Logger log = Logger.getLogger(TasteEditor.class);

    final private ITasteService tasteService;

    public TasteEditor(ITasteService tasteService) {
        this.tasteService = tasteService;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        Taste taste = null;
        if (id != null && !id.isEmpty())
            try {
                taste = tasteService.getTasteById(Long.parseLong(id));
            } catch (Exception e) {
                log.info("Error convert Long to String!!!" + e.getLocalizedMessage());
            }
        this.setValue(taste);
    }


}
