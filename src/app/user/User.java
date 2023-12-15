package app.user;

import app.Admin;
import app.audio.Collections.Playlist;
import app.audio.Collections.Album;
import app.audio.Collections.Podcast;
import app.audio.Collections.AudioCollection;
import app.audio.Collections.PlaylistOutput;
import app.audio.Files.Episode;
import app.audio.Files.Announcement;
import app.audio.Files.Merch;
import app.audio.Files.Song;
import app.audio.Files.Event;
import app.audio.Files.AudioFile;
import app.audio.LibraryEntry;
import app.player.Player;
import app.player.PlayerStats;
import app.searchBar.Filters;
import app.searchBar.SearchBar;
import app.utils.Enums;
import app.utils.visitor.Visitable;
import app.utils.visitor.Visitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import java.util.List;

/**
 * The type User.
 */
public class User extends LibraryEntry implements Visitable {
    @Getter
    private final Player player;
    private final SearchBar searchBar;
    @Getter
    private int age;
    @Getter
    private String city;
    @Getter
    private ArrayList<Playlist> playlists;
    @Getter
    private ArrayList<Song> likedSongs;
    @Getter
    private ArrayList<Playlist> followedPlaylists;
    private boolean lastSearched;
    @Getter
    private Integer userOrartistOrhost; // 0 = user; 1 = artist; 2 = host
    @Getter
    private boolean online;
    @Getter
    private String currentPage;
    @Getter
    private String pageOwner;
    @Getter
    private ArrayList<Album> albums; // for artist
    @Getter
    private ArrayList<Event> events; // for artist
    @Getter
    private ArrayList<Merch> merches; // for artist
    @Getter
    private ArrayList<Podcast> podcasts; // for host
    @Getter
    private ArrayList<Announcement> announcements; // for host
    private static final int TOP_COUNT = 5;

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param age      the age
     * @param city     the city
     */
    public User(final String username, final int age, final String city) {
        super(username);
        this.age = age;
        this.city = city;
        playlists = new ArrayList<>();
        likedSongs = new ArrayList<>();
        followedPlaylists = new ArrayList<>();
        player = new Player();
        searchBar = new SearchBar(username);
        lastSearched = false;
        userOrartistOrhost = 0;
        online = true;
        albums = new ArrayList<>();
        currentPage = "Home";
        events = new ArrayList<>();
        merches = new ArrayList<>();
        podcasts = new ArrayList<>();
        announcements = new ArrayList<>();
    }

    public User(final String username, final int age, final String city,
                final ArrayList<Album> thealbums, final ArrayList<Event> theevents,
                final ArrayList<Merch> themerches) { //for artist
        super(username);
        this.age = age;
        this.city = city;
        playlists = new ArrayList<>();
        likedSongs = new ArrayList<>();
        followedPlaylists = new ArrayList<>();
        player = new Player();
        searchBar = new SearchBar(username);
        lastSearched = false;
        userOrartistOrhost = 1;
        online = true;
        albums = thealbums;
        currentPage = "Home";
        events = theevents;
        merches = themerches;
        podcasts = new ArrayList<>();
        announcements = new ArrayList<>();
    }

    public User(final String username, final int age, final String city,
                final ArrayList<Podcast> thepodcasts,
                final ArrayList<Announcement> theannouncements) { // for host
        super(username);
        this.age = age;
        this.city = city;
        playlists = new ArrayList<>();
        likedSongs = new ArrayList<>();
        followedPlaylists = new ArrayList<>();
        player = new Player();
        searchBar = new SearchBar(username);
        lastSearched = false;
        userOrartistOrhost = 2;
        online = true;
        albums = new ArrayList<>();
        currentPage = "Home";
        events = new ArrayList<>();
        merches = new ArrayList<>();
        podcasts = thepodcasts;
        announcements = theannouncements;
    }


    public User() {
        super();
        player = new Player();
        searchBar = new SearchBar();
    }

    public final void setEvents(final ArrayList<Event> events) {
        this.events = events;
    }

    public final void setAlbums(final ArrayList<Album> albums) {
        this.albums = albums;
    }

    public final void setUserOrartistOrhost(final Integer userOrartistOrhost) {
        this.userOrartistOrhost = userOrartistOrhost;
    }

    public final void setOnline(final boolean active) {
        this.online = active;
    }

    public final void setPageOwner(final String pageOwner) {
        this.pageOwner = pageOwner;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The new username to be set.
     */
    public final void setUsername(final String username) {
        super.setName(username);
    }

    public final void setAge(final int age) {
        this.age = age;
    }

    public final void setCity(final String city) {
        this.city = city;
    }

    public final void setCurrentPage(final String currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Search array list.
     *
     * @param filters the filters
     * @param type    the type
     * @return the array list
     */
    public final ArrayList<String> search(final Filters filters, final String type) {
        searchBar.clearSelection();
        player.stop();

        lastSearched = true;
        ArrayList<String> results = new ArrayList<>();
        List<LibraryEntry> libraryEntries = searchBar.search(filters, type);

        for (LibraryEntry libraryEntry : libraryEntries) {
            results.add(libraryEntry.getName());
        }
        return results;
    }

    /**
     * Select string.
     *
     * @param itemNumber the item number
     * @return the string
     */
    public final String select(final int itemNumber) {
        if (!lastSearched) {
            return "Please conduct a search before making a selection.";
        }

        lastSearched = false;

        LibraryEntry selected = searchBar.select(itemNumber);

        if (selected == null) {
            return "The selected ID is too high.";
        }

        switch (searchBar.getLastSearchType()) {
            case "song", "podcast", "playlist", "album" -> {
                return "Successfully selected %s.".formatted(selected.getName());
            }
            case "artist" -> {
                User user = Admin.getUser(super.getName());
                user.setCurrentPage("artist");
                user.setPageOwner(selected.getName());

                return "Successfully selected %s's page.".formatted(selected.getName());
            }
            case "host" -> {
                User user = Admin.getUser(super.getName());
                user.setCurrentPage("host");
                user.setPageOwner(selected.getName());

                return "Successfully selected %s's page.".formatted(selected.getName());
            }
            default -> {
                return null;
            }
        }
    }

    /**
     * Load string.
     *
     * @return the string
     */
    public final String load() {
        if (searchBar.getLastSelected() == null) {
            return "Please select a source before attempting to load.";
        }

        if (!searchBar.getLastSearchType().equals("song")
                && ((AudioCollection) searchBar.getLastSelected()).getNumberOfTracks() == 0) {
            return "You can't load an empty audio collection!";
        }

        player.setSource(searchBar.getLastSelected(), searchBar.getLastSearchType());
        searchBar.clearSelection();

        player.pause();

        return "Playback loaded successfully.";
    }

    /**
     * Play pause string.
     *
     * @return the string
     */
    public final String playPause() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before attempting to pause or resume playback.";
        }

        player.pause();

        if (player.getPaused()) {
            return "Playback paused successfully.";
        } else {
            return "Playback resumed successfully.";
        }
    }

    /**
     * Repeat string.
     *
     * @return the string
     */
    public final String repeat() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before setting the repeat status.";
        }

        Enums.RepeatMode repeatMode = player.repeat();
        String repeatStatus = "";

        switch (repeatMode) {
            case NO_REPEAT -> {
                repeatStatus = "no repeat";
            }
            case REPEAT_ONCE -> {
                repeatStatus = "repeat once";
            }
            case REPEAT_ALL -> {
                repeatStatus = "repeat all";
            }
            case REPEAT_INFINITE -> {
                repeatStatus = "repeat infinite";
            }
            case REPEAT_CURRENT_SONG -> {
                repeatStatus = "repeat current song";
            }
            default -> {
                repeatStatus = "";
            }
        }

        return "Repeat mode changed to %s.".formatted(repeatStatus);
    }

    /**
     * Shuffle string.
     *
     * @param seed the seed
     * @return the string
     */
    public final String shuffle(final Integer seed) {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before using the shuffle function.";
        }

        if (!(player.getType().equals("playlist") || player.getType().equals("album"))) {
            return "The loaded source is not a playlist or an album.";
        }
        player.shuffle(seed);

        if (player.getShuffle()) {
            return "Shuffle function activated successfully.";
        }
        return "Shuffle function deactivated successfully.";
    }

    /**
     * Forward string.
     *
     * @return the string
     */
    public final String forward() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before attempting to forward.";
        }

        if (!player.getType().equals("podcast")) {
            return "The loaded source is not a podcast.";
        }

        player.skipNext();

        return "Skipped forward successfully.";
    }

    /**
     * Backward string.
     *
     * @return the string
     */
    public final String backward() {
        if (player.getCurrentAudioFile() == null) {
            return "Please select a source before rewinding.";
        }

        if (!player.getType().equals("podcast")) {
            return "The loaded source is not a podcast.";
        }

        player.skipPrev();

        return "Rewound successfully.";
    }

    /**
     * Like string.
     *
     * @return the string
     */
    public final String like() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before liking or unliking.";
        }

        if (!player.getType().equals("song")
                && !player.getType().equals("playlist") && !player.getType().equals("album")) {
            return "Loaded source is not a song.";
        }

        if (!this.isOnline()) {
            return this.getName() + " is offline.";
        }

        Song song = (Song) player.getCurrentAudioFile();

        if (likedSongs.contains(song)) {
            likedSongs.remove(song);
            song.dislike();

            return "Unlike registered successfully.";
        }

        likedSongs.add(song);
        song.like();

        return "Like registered successfully.";
    }

    /**
     * Next string.
     *
     * @return the string
     */
    public final String next() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before skipping to the next track.";
        }

        player.next();

        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before skipping to the next track.";
        }

        return "Skipped to next track successfully. The current track is %s."
                .formatted(player.getCurrentAudioFile().getName());
    }

    /**
     * Prev string.
     *
     * @return the string
     */
    public final String prev() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before returning to the previous track.";
        }

        player.prev();

        return "Returned to previous track successfully. The current track is %s."
                .formatted(player.getCurrentAudioFile().getName());
    }

    /**
     * Create playlist string.
     *
     * @param name      the name
     * @param timestamp the timestamp
     * @return the string
     */
    public final String createPlaylist(final String name, final int timestamp) {
        if (playlists.stream().anyMatch(playlist -> playlist.getName().equals(name))) {
            return "A playlist with the same name already exists.";
        }

        playlists.add(new Playlist(name, super.getName(), timestamp));

        return "Playlist created successfully.";
    }

    /**
     * Add remove in playlist string.
     *
     * @param id the id
     * @return the string
     */
    public final String addRemoveInPlaylist(final int id) {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before adding to or removing from the playlist.";
        }

        if (player.getType().equals("podcast")) {
            return "The loaded source is not a song.";
        }

        if (id > playlists.size()) {
            return "The specified playlist does not exist.";
        }

        Playlist playlist = playlists.get(id - 1);

        if (playlist.containsSong((Song) player.getCurrentAudioFile())) {
            playlist.removeSong((Song) player.getCurrentAudioFile());
            return "Successfully removed from playlist.";
        }

        playlist.addSong((Song) player.getCurrentAudioFile());
        return "Successfully added to playlist.";
    }

    /**
     * Switch playlist visibility string.
     *
     * @param playlistId the playlist id
     * @return the string
     */
    public final String switchPlaylistVisibility(final Integer playlistId) {
        if (playlistId > playlists.size()) {
            return "The specified playlist ID is too high.";
        }

        Playlist playlist = playlists.get(playlistId - 1);
        playlist.switchVisibility();

        if (playlist.getVisibility() == Enums.Visibility.PUBLIC) {
            return "Visibility status updated successfully to public.";
        }

        return "Visibility status updated successfully to private.";
    }

    /**
     * Show playlists array list.
     *
     * @return the array list
     */
    public final ArrayList<PlaylistOutput> showPlaylists() {
        ArrayList<PlaylistOutput> playlistOutputs = new ArrayList<>();
        for (Playlist playlist : playlists) {
            playlistOutputs.add(new PlaylistOutput(playlist));
        }

        return playlistOutputs;
    }

    /**
     * Follow string.
     *
     * @return the string
     */
    public final String follow() {
        LibraryEntry selection = searchBar.getLastSelected();
        String type = searchBar.getLastSearchType();

        if (selection == null) {
            return "Please select a source before following or unfollowing.";
        }

        if (!type.equals("playlist")) {
            return "The selected source is not a playlist.";
        }

        Playlist playlist = (Playlist) selection;

        if (playlist.getOwner().equals(super.getName())) {
            return "You cannot follow or unfollow your own playlist.";
        }

        if (followedPlaylists.contains(playlist)) {
            followedPlaylists.remove(playlist);
            playlist.decreaseFollowers();

            return "Playlist unfollowed successfully.";
        }

        followedPlaylists.add(playlist);
        playlist.increaseFollowers();


        return "Playlist followed successfully.";
    }

    /**
     * Gets player stats.
     *
     * @return the player stats
     */
    public final PlayerStats getPlayerStats() {
        return player.getStats();
    }

    /**
     * Show preferred songs array list.
     *
     * @return the array list
     */
    public final ArrayList<String> showPreferredSongs() {
        ArrayList<String> results = new ArrayList<>();
        for (AudioFile audioFile : likedSongs) {
            results.add(audioFile.getName());
        }

        return results;
    }

    /**
     * Gets preferred genre.
     *
     * @return the preferred genre
     */
    public final String getPreferredGenre() {
        String[] genres = {"pop", "rock", "rap"};
        int[] counts = new int[genres.length];
        int mostLikedIndex = -1;
        int mostLikedCount = 0;

        for (Song song : likedSongs) {
            for (int i = 0; i < genres.length; i++) {
                if (song.getGenre().equals(genres[i])) {
                    counts[i]++;
                    if (counts[i] > mostLikedCount) {
                        mostLikedCount = counts[i];
                        mostLikedIndex = i;
                    }
                    break;
                }
            }
        }

        String preferredGenre = mostLikedIndex != -1 ? genres[mostLikedIndex] : "unknown";
        return "This user's preferred genre is %s.".formatted(preferredGenre);
    }

    /**
     * Simulate time.
     *
     * @param time the time
     */
    public final void simulateTime(final int time) {
        if (this.isOnline()) {
            player.simulatePlayer(time);
        }
    }

    /**
     * Switches the connection status of the user.
     *
     * @return A string message indicating the result of the operation.
     */
    public final String switchConnectionStatus() {
        if (this.getUserOrartistOrhost() != 0) {
            return this.getName() + " is not a normal user.";
        }

        this.setOnline(!this.isOnline());
        return this.getName() + " has changed status successfully.";
    }

    /**
     * Sorts a list of songs in descending order based on the number of likes each song has.
     *
     * @param songs The list of songs to be sorted.
     */
    public final void sortSongsByLikes(final ArrayList<Song> songs) {
        songs.sort(Comparator.comparingInt(Song::getLikes).reversed());
    }

    /**
     * Sorts a list of playlists in descending order based on the number of likes.
     *
     * @param theplaylists the list of playlist to be sorted
     */
    public final void sortPlaylistsByLikes(final ArrayList<Playlist> theplaylists) {
        for (Playlist playlist : theplaylists) {
            playlist.getTotalSongsLikes();
        }

        theplaylists.sort(Comparator.comparingInt(Playlist::getTotallikes).reversed());
    }

    /**
     * Prints the homepage of the user
     *
     * @return return the page
     */
    public final String printHomePage() {
        StringBuilder songsBuilder = new StringBuilder();
        StringBuilder playlistsBuilder = new StringBuilder();

        ArrayList<Song> sortedsongs = new ArrayList<>(likedSongs);
        ArrayList<Playlist> sortedplaylists = new ArrayList<>(followedPlaylists);

        this.sortSongsByLikes(sortedsongs);
        this.sortPlaylistsByLikes(sortedplaylists);

        int count = 0;
        Iterator<Song> songsiterator = sortedsongs.iterator();

        while (songsiterator.hasNext() && count < TOP_COUNT) {
            Song song = songsiterator.next();
            songsBuilder.append(song.getName());
            count++;

            if (songsiterator.hasNext() && count < TOP_COUNT) {
                songsBuilder.append(", ");
            }
        }

        String songs = songsBuilder.toString();

        count = 0;

        Iterator<Playlist> playlistsiterator = sortedplaylists.iterator();

        while (playlistsiterator.hasNext() && count < TOP_COUNT) {
            Playlist playlist = playlistsiterator.next();
            playlistsBuilder.append(playlist.getName());
            count++;

            if (playlistsiterator.hasNext() && count < TOP_COUNT) {
                playlistsBuilder.append(", ");
            }
        }

        String theplaylists = playlistsBuilder.toString();


        String message = "Liked songs:\n\t[" + songs
                + "]\n\nFollowed playlists:\n\t[" + theplaylists + "]";
        return message;
    }

    /**
     * Prints the artist's page the user is on
     *
     * @return return the page
     */
    public final String printArtistPage() {
        StringBuilder albumsBuilder = new StringBuilder();
        StringBuilder merchBuilder = new StringBuilder();
        StringBuilder eventsBuilder = new StringBuilder();

        User artist = Admin.getUser(pageOwner);

        for (Album album : artist.getAlbums()) {
            albumsBuilder.append(album.getName()).append(", ");
        }

        for (Merch merch : artist.getMerches()) {
            merchBuilder.append(merch.getName()).append(" - ").append(merch.getPrice())
                    .append(":\n\t").append(merch.getDescription()).append(", ");
        }

        for (Event event : artist.getEvents()) {
            eventsBuilder.append(event.getName()).append(" - ").append(event.getDate())
                    .append(":\n\t").append(event.getDescription()).append(", ");
        }

        if (!albumsBuilder.isEmpty()) {
            albumsBuilder.setLength(albumsBuilder.length() - 2);
        }
        if (!merchBuilder.isEmpty()) {
            merchBuilder.setLength(merchBuilder.length() - 2);
        }
        if (!eventsBuilder.isEmpty()) {
            eventsBuilder.setLength(eventsBuilder.length() - 2);
        }

        return String.format("Albums:\n\t[%s]\n\nMerch:\n\t[%s]\n\nEvents:\n\t[%s]",
                albumsBuilder, merchBuilder, eventsBuilder);

    }

    /**
     * Prints the host's page the user is on
     *
     * @return return the page
     */
    public final String printHostPage() {
        StringBuilder podcastsBuilder = new StringBuilder();
        StringBuilder announcementsBuilder = new StringBuilder();

        User host = Admin.getUser(pageOwner);


        for (Podcast podcast : host.getPodcasts()) {
            podcastsBuilder.append(podcast.getName()).append(":\n\t[");
            for (Episode episode : podcast.getEpisodes()) {
                podcastsBuilder.append(episode.getName()).append(" - ")
                        .append(episode.getDescription()).append(", ");
            }
            if (!podcast.getEpisodes().isEmpty()) {
                podcastsBuilder.setLength(podcastsBuilder.length() - 2);
            }
            podcastsBuilder.append("]\n, ");
        }
        if (!podcastsBuilder.isEmpty()) {
            podcastsBuilder.setLength(podcastsBuilder.length() - 2);
        }

        for (Announcement announcement : host.getAnnouncements()) {
            announcementsBuilder.append(announcement.getName()).append(":\n\t")
                    .append(announcement.getDescription()).append("\n").append(", ");
        }
        if (!announcementsBuilder.isEmpty()) {
            announcementsBuilder.setLength(announcementsBuilder.length() - 2);
        }

        return String.format("Podcasts:\n\t[%s]\n\nAnnouncements:\n\t[%s]",
                podcastsBuilder, announcementsBuilder);
    }

    /**
     * Removes a song from the user's list of liked songs.
     *
     * @param name The name of the song to be removed.
     */
    public final void removeLikedSong(final String name) {
        Iterator<Song> songIterator = this.likedSongs.iterator();

        while (songIterator.hasNext()) {
            Song song = songIterator.next();

            if (song.getName().equals(name)) {
                songIterator.remove();
                song.dislike();
            }
        }
    }


    /**
     * Removes a playlist from the user's list of followed playlists.
     *
     * @param name The name of the song to be removed.
     */
    public final void removeFollowedPlaylist(final String name) {
        Iterator<Playlist> playlistIterator = this.followedPlaylists.iterator();

        while (playlistIterator.hasNext()) {
            Playlist playlist = playlistIterator.next();

            if (playlist.getName().equals(name)) {
                playlistIterator.remove();

            }
        }
    }


    /**
     * Prints the LikedContent page of the user
     *
     * @return return the page
     */
    public final String printLikedContentPage() {
        StringBuilder songsBuilder = new StringBuilder();
        StringBuilder playlistsBuilder = new StringBuilder();

        for (Song song : likedSongs) {
            songsBuilder.append(song.getName()).append(" - ")
                    .append(song.getArtist()).append(", ");
        }

        for (Playlist playlist : followedPlaylists) {
            playlistsBuilder.append(playlist.getName()).append(" - ")
                    .append(playlist.getOwner()).append(", ");
        }

        if (!songsBuilder.isEmpty()) {
            songsBuilder.setLength(songsBuilder.length() - 2);
        }

        if (!playlistsBuilder.isEmpty()) {
            playlistsBuilder.setLength(playlistsBuilder.length() - 2);
        }

        return String.format("Liked songs:\n\t[%s]\n\nFollowed playlists:\n\t[%s]",
                songsBuilder, playlistsBuilder);
    }

    /**
     * Accepts a visitor to perform operations on the User object.
     * This method is part of the Visitor design pattern.
     *
     * @param visitor the visitor that is performing operations on the User object
     * @return the result of the visitor's visit method
     */
    @Override
    public boolean accept(final Visitor visitor) {
        return visitor.visit(this);
    }

}
