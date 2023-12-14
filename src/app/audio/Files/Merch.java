package app.audio.Files;

import lombok.Getter;

@Getter
public final class Merch {
    private String name;
    private String description;
    private Integer price;

    public Merch(final String name, final String description, final Integer price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void setName(final String name) {
        this.name = name; }

    public void setDescription(final String description) {
        this.description = description; }
    public void setPrice(final Integer price) {
        this.price = price; }
}
