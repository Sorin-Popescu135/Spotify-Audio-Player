package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import app.utils.visitor.Visitable;
import app.utils.visitor.Visitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public final class Album extends AudioCollection implements Visitable {
    private List<Song> songs;
    private String description;
    private Integer releaseYear;

    public Album() {
        super("", "");
        this.songs = new ArrayList<>();
    }

    public Album(final Integer releaseYear, final String name, final List<Song> songs,
                 final String description, final String owner) {
        super(name, owner);
        this.releaseYear = releaseYear;
        this.songs = songs;
        this.description = description;
    }

    public void setSongs(final List<Song> songs) {
        this.songs = songs;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setReleaseYear(final Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Adds a new song to the list of songs in the album.
     *
     * @param song The song to be added. It should not be null.
     */
    public void addSong(final Song song) {
        this.songs.add(song);
    }

    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }

    @Override
    public AudioFile getTrackByIndex(final int index) {
        return songs.get(index);
    }

    /**
     * Checks if the album contains duplicate songs.
     *
     * @return true if there are duplicate songs in the album, false otherwise.
     */
    public boolean hasDuplicateSongs() {
        Set<String> seenSongNames = new HashSet<>();
        for (Song song : songs) {
            if (seenSongNames.contains(song.getName())) {
                return true;
            }
            seenSongNames.add(song.getName());
        }
        return false;
    }

    /**
     * Calculates the total number of likes for all songs in the album.
     *
     * @return The total number of likes for all songs in the album.
     */
    public Integer getTotalLikes() {
        int totalLikes = 0;
        for (Song song : songs) {
            totalLikes += song.getLikes();
        }
        return totalLikes;
    }

    @Override
    public boolean accept(final Visitor visitor) {
        return visitor.visit(this);
    }
}
