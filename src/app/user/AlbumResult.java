package app.user;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class AlbumResult {

    private String name;
    private ArrayList<String> songs;

    public AlbumResult() {}

    public AlbumResult(String albumname, ArrayList<String> songnames) {
        this.name = albumname;
        this.songs = songnames;
    }

    public void setName(String name) { this.name = name; }

    public void setSongs(ArrayList<String> songs) { this.songs = songs; }

    public void addSong(String songname) { songs.add(songname); }
}
