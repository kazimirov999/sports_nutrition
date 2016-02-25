package net.sports_nutrition.utils.validators.file;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 27.01.2016 23:23
 */
public enum Type {

    JPEG("image/jpeg"), GIF("image/gif"), BMP("image/bmp"),
    JPG("image/jpg");
   private String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
