package app.audio;

import lombok.Getter;

import java.util.ArrayList;

/**
 * Abstract class representing a library entry.
 * This class provides a structure for different types
 * of library entries and methods to match various attributes.
 */
@Getter
public abstract class LibraryEntry {
    private String name;

    /**
     * Constructs a new LibraryEntry with the given name.
     *
     * @param name the name of the library entry
     */
    public LibraryEntry(final String name) {
        this.name = name;
    }

    /**
     * Sets the name of the library entry.
     *
     * @param name the new name of the library entry
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Default constructor for LibraryEntry.
     */
    public LibraryEntry() {

    }

    /**
     * Checks if the library entry's name matches the given name.
     *
     * @param thename the name to match
     * @return true if the names match, false otherwise
     */
    public boolean matchesName(final String thename) {
        return getName().toLowerCase().startsWith(thename.toLowerCase());
    }

    /**
     * Checks if the library entry's album matches the given album.
     * This method should be overridden in subclasses where applicable.
     *
     * @param album the album to match
     * @return false by default, subclasses should override as necessary
     */
    public boolean matchesAlbum(final String album) {
        return false;
    }

    /**
     * Checks if the library entry's tags match the given tags.
     * This method should be overridden in subclasses where applicable.
     *
     * @param tags the tags to match
     * @return false by default, subclasses should override as necessary
     */
    public boolean matchesTags(final ArrayList<String> tags) {
        return false;
    }

    /**
     * Checks if the library entry's lyrics match the given lyrics.
     * This method should be overridden in subclasses where applicable.
     *
     * @param lyrics the lyrics to match
     * @return false by default, subclasses should override as necessary
     */
    public boolean matchesLyrics(final String lyrics) {
        return false;
    }

    /**
     * Checks if the library entry's genre matches the given genre.
     * This method should be overridden in subclasses where applicable.
     *
     * @param genre the genre to match
     * @return false by default, subclasses should override as necessary
     */
    public boolean matchesGenre(final String genre) {
        return false;
    }

    /**
     * Checks if the library entry's artist matches the given artist.
     * This method should be overridden in subclasses where applicable.
     *
     * @param artist the artist to match
     * @return false by default, subclasses should override as necessary
     */
    public boolean matchesArtist(final String artist) {
        return false;
    }

    /**
     * Checks if the library entry's release year matches the given release year.
     * This method should be overridden in subclasses where applicable.
     *
     * @param releaseYear the release year to match
     * @return false by default, subclasses should override as necessary
     */
    public boolean matchesReleaseYear(final String releaseYear) {
        return false;
    }

    /**
     * Checks if the library entry's owner matches the given user.
     * This method should be overridden in subclasses where applicable.
     *
     * @param user the user to match
     * @return false by default, subclasses should override as necessary
     */
    public boolean matchesOwner(final String user) {
        return false;
    }

    /**
     * Checks if the library entry is visible to the given user.
     * This method should be overridden in subclasses where applicable.
     *
     * @param user the user to check visibility for
     * @return false by default, subclasses should override as necessary
     */
    public boolean isVisibleToUser(final String user) {
        return false;
    }

    /**
     * Checks if the library entry's followers match the given followers.
     * This method should be overridden in subclasses where applicable.
     *
     * @param followers the followers to match
     * @return false by default, subclasses should override as necessary
     */
    public boolean matchesFollowers(final String followers) {
        return false;
    }

    /**
     * Checks if the library entry's description matches the given description.
     * This method should be overridden in subclasses where applicable.
     *
     * @param description the description to match
     * @return false by default, subclasses should override as necessary
     */
    public boolean matchesDescription(final String description) {
        return false;
    }
}
