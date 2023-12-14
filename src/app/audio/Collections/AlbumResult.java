package app.audio.Collections;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public final class AlbumResult {

    private String name;
    private ArrayList<String> songs;

    public AlbumResult() {

    }

    public AlbumResult(final String albumname, final ArrayList<String> songnames) {
        this.name = albumname;
        this.songs = songnames;
    }

    public void setName(final String name) {
        this.name = name; }

    public void setSongs(final ArrayList<String> songs) {
        this.songs = songs; }

    /**
     * Adds a new song to the list of songs in the album result.
     *
     * @param songname The name of the song to be added. It should not be null.
     */
    public void addSong(final String songname) {
        songs.add(songname);
    }
}
