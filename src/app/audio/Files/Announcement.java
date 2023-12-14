package app.audio.Files;

import lombok.Getter;

@Getter
public final class Announcement {
    private String name;

    private String description;

    public Announcement(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public void setName(final String name) {
        this.name = name; }

    public void setDescription(final String description) {
        this.description = description; }
}
