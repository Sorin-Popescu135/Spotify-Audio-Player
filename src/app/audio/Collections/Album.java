package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class Album extends AudioCollection {
    List<Song> songs;
    private String description;
    private Integer releaseYear;

    public Album(){
        super("", "");
        this.songs = new ArrayList<>();
    }

    public Album(Integer releaseYear, String name, List<Song> songs, String description, String owner) {
        super(name, owner);
        this.releaseYear = releaseYear;
        this.songs = songs;
        this.description = description;
    }

    public void setSongs( List<Song> songs) {
        this.songs = songs;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void addSong(Song song){
        this.songs.add(song);
    }

    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }

    @Override
    public AudioFile getTrackByIndex(int index) {
        return songs.get(index);
    }

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
}
