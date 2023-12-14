package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import app.utils.Enums;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public final class Playlist extends AudioCollection {
    private final ArrayList<Song> songs;
    private Enums.Visibility visibility;
    private Integer followers;
    private int timestamp;
    private int totallikes;

    public Playlist(final String name, final String owner) {
        this(name, owner, 0);
    }

    public Playlist(final String name, final String owner, final int timestamp) {
        super(name, owner);
        this.songs = new ArrayList<>();
        this.visibility = Enums.Visibility.PUBLIC;
        this.followers = 0;
        this.timestamp = timestamp;
        this.totallikes = 0;
    }

    /**
     * Checks if a song is present in the playlist.
     *
     * @param song The song to be checked. It should not be null.
     * @return true if the song is present in the playlist, false otherwise.
     */
    public boolean containsSong(final Song song) {
        return songs.contains(song);
    }

    /**
     * Adds a new song to the playlist.
     *
     * @param song The song to be added. It should not be null.
     */
    public void addSong(final Song song) {
        songs.add(song);
    }

    /**
     * Removes a song from the playlist.
     *
     * @param song The song to be removed. It should not be null.
     */
    public void removeSong(final Song song) {
        songs.remove(song);
    }

    /**
     * Removes a song from the playlist by its index.
     *
     * @param index The index of the song to be removed.
     */
    public void removeSong(final int index) {
        songs.remove(index);
    }

    /**
     * Switches the visibility of the playlist.
     * If the visibility is currently PUBLIC, it will be changed to PRIVATE, and vice versa.
     */
    public void switchVisibility() {
        if (visibility == Enums.Visibility.PUBLIC) {
            visibility = Enums.Visibility.PRIVATE;
        } else {
            visibility = Enums.Visibility.PUBLIC;
        }
    }

    /**
     * Increases the number of followers of the playlist by one.
     */
    public void increaseFollowers() {
        followers++;
    }

    /**
     * Decreases the number of followers of the playlist by one.
     */
    public void decreaseFollowers() {
        followers--;
    }

    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }

    @Override
    public AudioFile getTrackByIndex(final int index) {
        return songs.get(index);
    }

    @Override
    public boolean isVisibleToUser(final String user) {
        return this.getVisibility() == Enums.Visibility.PUBLIC
                || (this.getVisibility() == Enums.Visibility.PRIVATE
                && this.getOwner().equals(user));
    }

    @Override
    public boolean matchesFollowers(final String thefollowers) {
        return filterByFollowersCount(this.getFollowers(), thefollowers);
    }

    private static boolean filterByFollowersCount(final int count, final String query) {
        if (query.startsWith("<")) {
            return count < Integer.parseInt(query.substring(1));
        } else if (query.startsWith(">")) {
            return count > Integer.parseInt(query.substring(1));
        } else {
            return count == Integer.parseInt(query);
        }
    }

    /**
     * Calculates the total number of likes for all songs in the playlist.
     */
    public void getTotalSongsLikes() {
        for (Song song : this.songs) {
            this.totallikes += song.getLikes();
        }

    }
}
