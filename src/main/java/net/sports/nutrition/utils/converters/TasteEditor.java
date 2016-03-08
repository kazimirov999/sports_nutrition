package net.sports.nutrition.utils.converters;

import net.sports.nutrition.domain.entities.Taste;
import net.sports.nutrition.services.ITasteService;
import org.jboss.logging.Logger;

import java.beans.PropertyEditorSupport;

/**
 * This TasteEditor converting text value(id) to Taste object.
 *
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
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
        if (id != null && !id.isEmpty()) {
            try {
                taste = tasteService.getTasteById(Long.parseLong(id));
            } catch (Exception e) {
                log.error("Error convert Long to String", e);
            }
        }
        this.setValue(taste);
    }


}
