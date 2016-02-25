package net.sports.nutrition.domain.enumx;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 12.02.2016 8:42
 */
public enum Role {

    ROLE_ADMIN("ADMIN"),ROLE_USER("USER");

    private String name;
    Role(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
