package app.audio.Files;

import fileio.input.SongInput;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class Song extends AudioFile {
    private final String album;
    private final ArrayList<String> tags;
    private final String lyrics;
    private final String genre;
    private final Integer releaseYear;
    private final String artist;
    private Integer likes;

    public Song(final String name, final Integer duration, final String album,
                final ArrayList<String> tags, final String lyrics,
                final String genre, final Integer releaseYear, final String artist) {
        super(name, duration);
        this.album = album;
        this.tags = tags;
        this.lyrics = lyrics;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.artist = artist;
        this.likes = 0;
    }

    public Song(final SongInput song) {
        super(song.getName(), song.getDuration());
        this.album = song.getAlbum();
        this.tags = song.getTags();
        this.lyrics = song.getLyrics();
        this.genre = song.getGenre();
        this.releaseYear = song.getReleaseYear();
        this.artist = song.getArtist();
        this.likes = 0;
    }

    @Override
    public boolean matchesAlbum(final String thealbum) {
        return this.getAlbum().equalsIgnoreCase(thealbum);
    }

    @Override
    public boolean matchesTags(final ArrayList<String> thetags) {
        List<String> songTags = new ArrayList<>();
        for (String tag : this.getTags()) {
            songTags.add(tag.toLowerCase());
        }

        for (String tag : thetags) {
            if (!songTags.contains(tag.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean matchesLyrics(final String thelyrics) {
        return this.getLyrics().toLowerCase().contains(thelyrics.toLowerCase());
    }

    @Override
    public boolean matchesGenre(final String thegenre) {
        return this.getGenre().equalsIgnoreCase(thegenre);
    }

    @Override
    public boolean matchesArtist(final String theartist) {
        return this.getArtist().equalsIgnoreCase(theartist);
    }

    @Override
    public boolean matchesReleaseYear(final String thereleaseYear) {
        return filterByYear(this.getReleaseYear(), thereleaseYear);
    }

    private static boolean filterByYear(final int theyear, final String thequery) {
        if (thequery.startsWith("<")) {
            return theyear < Integer.parseInt(thequery.substring(1));
        } else if (thequery.startsWith(">")) {
            return theyear > Integer.parseInt(thequery.substring(1));
        } else {
            return theyear == Integer.parseInt(thequery);
        }
    }

    /**
     * Increases the number of likes for the song by one.
     */
    public void like() {
        likes++;
    }

    /**
     * Decreases the number of likes for the song by one.
     */
    public void dislike() {
        likes--;
    }
}
