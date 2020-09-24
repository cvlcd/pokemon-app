package io.azet.pokemon.details.model;

public class Description {
    private String flavorText;
    private String language;

    public Description(String flavorText, String language) {
        this.flavorText = flavorText;
        this.language = language;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
