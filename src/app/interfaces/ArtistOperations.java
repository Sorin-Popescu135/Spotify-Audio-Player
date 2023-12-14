package app.interfaces;

import app.audio.Collections.Album;
import app.audio.Files.Event;
import app.audio.Files.Merch;

import java.util.ArrayList;

public interface ArtistOperations {

    /**
     * Adds an album to the artist's collection.
     *
     * @param album The album to be added.
     * @return A string message indicating the result of the operation.
     */
    String addAlbum(Album album);

    /**
     * Retrieves the albums of the artist.
     *
     * @return An ArrayList of albums belonging to the artist.
     */
    ArrayList<Album> getUserAlbums();

    /**
     * Checks if the artist has an album with the same name.
     *
     * @param album The album to check.
     * @return True if an album with the same name exists, false otherwise.
     */
    boolean hasAlbumWithSameName(Album album);

    /**
     * Shows the names of the songs on an album.
     *
     * @param album The album whose songs are to be displayed.
     * @return An ArrayList of song names.
     */
    ArrayList<String> showSongsName(Album album);

    /**
     * Adds an event to the artist.
     *
     * @param event The event to be added.
     * @return A string message indicating the result of the operation.
     */
    String addEvent(Event event);

    /**
     * Adds merchandise for the artist.
     *
     * @param merch The merchandise to be added.
     * @return A string message indicating the result of the operation.
     */
    String addMerch(Merch merch);

    /**
     * Removes an album from the artist's collection.
     *
     * @param album The album to be removed.
     * @return A string message indicating the result of the operation.
     */
    String removeAlbum(Album album);

    /**
     * Removes an event from the artist.
     *
     * @param name The name of the event to be removed.
     * @return A string message indicating the result of the operation.
     */
    String removeEvent(String name);

    /**
     * Checks if the artist has an event with the same name.
     *
     * @param name The name of the event to check.
     * @return True if an event with the same name exists, false otherwise.
     */
    boolean hasEventWithSameName(String name);

}
