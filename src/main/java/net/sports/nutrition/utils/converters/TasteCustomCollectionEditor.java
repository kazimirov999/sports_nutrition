package net.sports.nutrition.utils.converters;

import net.sports.nutrition.domain.entities.Taste;
import net.sports.nutrition.services.ITasteService;
import org.jboss.logging.Logger;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import java.util.Collection;

/**
 * This TasteCustomCollectionEditor converting text values(id) to collection of Taste object.
 *
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class TasteCustomCollectionEditor extends CustomCollectionEditor {

    private static final Logger log = Logger.getLogger(TasteCustomCollectionEditor.class);

    final private ITasteService tasteService;

    public TasteCustomCollectionEditor(Class<? extends Collection> collectionType, ITasteService tasteService) {
        super(collectionType);
        this.tasteService = tasteService;
    }

    @Override
    protected Object convertElement(Object id){
        if(!"String".equals(id.getClass().getSimpleName())){
            return null;
        }

        Taste taste = new Taste();
        try{
            taste = tasteService.getTasteById(Long.parseLong((String) id));
        }catch (Exception e){
            log.error("Error convert Long to String", e);
        }

        return taste;
    }

}
