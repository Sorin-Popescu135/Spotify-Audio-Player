package app;

import app.audio.Collections.Album;
import app.audio.Collections.Playlist;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.user.Artist;
import app.user.Host;
import app.user.User;
import fileio.input.UserInput;
import fileio.input.SongInput;
import fileio.input.PodcastInput;
import fileio.input.EpisodeInput;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Iterator;

/**
 * The type Admin.
 */
public final class Admin {
    @Getter
    private static List<User> users = new ArrayList<>();
    private static List<Song> songs = new ArrayList<>();
    private static List<Podcast> podcasts = new ArrayList<>();
    @Getter
    private static List<Album> albums = new ArrayList<>();
    private static int timestamp = 0;
    private static final int TOP_COUNT = 5;


    private Admin() {
    }


    /**
     * Sets users.
     *
     * @param userInputList the user input list
     */
    public static void setUsers(final List<UserInput> userInputList) {
        users = new ArrayList<>();
        for (UserInput userInput : userInputList) {
            users.add(new User(userInput.getUsername(), userInput.getAge(), userInput.getCity()));
        }
    }

    /**
     * Sets songs.
     *
     * @param songInputList the song input list
     */
    public static void setSongs(final List<SongInput> songInputList) {
        songs = new ArrayList<>();
        for (SongInput songInput : songInputList) {
            songs.add(new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));
        }
    }


    /**
     * Sets podcasts.
     *
     * @param podcastInputList the podcast input list
     */
    public static void setPodcasts(final List<PodcastInput> podcastInputList) {
        podcasts = new ArrayList<>();
        for (PodcastInput podcastInput : podcastInputList) {
            List<Episode> episodes = new ArrayList<>();
            for (EpisodeInput episodeInput : podcastInput.getEpisodes()) {
                episodes.add(new Episode(episodeInput.getName(),
                        episodeInput.getDuration(),
                        episodeInput.getDescription()));
            }
            podcasts.add(new Podcast(podcastInput.getName(), podcastInput.getOwner(), episodes));
        }
    }

    /**
     * Gets songs.
     *
     * @return the songs
     */
    public static List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    /**
     * Gets podcasts.
     *
     * @return the podcasts
     */
    public static List<Podcast> getPodcasts() {
        return new ArrayList<>(podcasts);
    }

    /**
     * Adds a new album to the list of albums.
     *
     * @param album The album to be added. It should not be null.
     */
    public static void addAlbum(final Album album) {
        albums.add(album);
    }

    /**
     * Adds a new podcast to the list of podcasts.
     *
     * @param podcast The album to be added. It should not be null.
     */
    public static void addPodcast(final Podcast podcast) {
        podcasts.add(podcast);
    }

    /**
     * Retrieves an album from the list of albums by its name.
     *
     * @param name The name of the album to be retrieved. It's case-insensitive.
     * @return The Album object if found, null otherwise.
     */
    public static Album getAlbum(final String name) {
        for (Album album : albums) {
            if (album.getName().equalsIgnoreCase(name)) {
                return album;
            }
        }
        return null;
    }

    /**
     * Retrieves an episode from the list of podcasts by its name.
     *
     * @param name The name of the episode to be retrieved.
     * @return The Episode object if found, null otherwise.
     */
    public static Episode getEpisode(final String name) {
        for (Podcast podcast : podcasts) {
            for (Episode episode : podcast.getEpisodes()) {
                if (episode.getName().equals(name)) {
                    return episode;
                }
            }
        }
        return null;
    }

    /**
     * Retrieves all users who are artists from the list of users.
     *
     * @return A list of Artist objects. If no artists are found, an empty list is returned.
     */
    public static List<Artist> getAllArtists() {
        List<Artist> artists = new ArrayList<>();

        for (User user : users) {
            if (user.getUserOrartistOrhost().equals(1)) {
                user = new Artist(user);
                artists.add((Artist) user);
            }
        }
        return artists;
    }

    /**
     * Retrieves all users who are hosts from the list of users.
     *
     * @return A list of User objects who are hosts.
     * If no hosts are found, an empty list is returned.
     */
    public static List<User> getAllHosts() {
        List<User> hosts = new ArrayList<>();

        for (User user : users) {
            if (user.getUserOrartistOrhost() == 2) {
                hosts.add(user);
            }
        }
        return hosts;
    }

    public static void setAlbums(final List<Album> albums) {
        Admin.albums = albums;
    }

    /**
     * Retrieves all playlists from all users.
     *
     * @return A list of Playlist objects. If no playlists are found, an empty list is returned.
     */
    public static List<Playlist> getPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        for (User user : users) {
            playlists.addAll(user.getPlaylists());
        }
        return playlists;
    }

    /**
     * Retrieves a user from the list of users by their username.
     *
     * @param username The username of the user to be retrieved.
     * @return The User object if found, null otherwise.
     */
    public static User getUser(final String username) {
        for (User user : users) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Retrieves a podcast from the list of podcasts by its name.
     *
     * @param name The name of the podcast to be retrieved.
     * @return The Podcast object if found, null otherwise.
     */
    public static Podcast getPodcast(final String name) {
        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(name)) {
                return podcast;
            }
        }
        return null;
    }

    /**
     * Updates the current timestamp and simulates the time elapsed since the last update.
     * If the elapsed time is zero, the method will return immediately.
     *
     * @param newTimestamp The new timestamp to be set.
     *                    It should be greater than or equal to the current timestamp.
     */
    public static void updateTimestamp(final int newTimestamp) {
        int elapsed = newTimestamp - timestamp;
        timestamp = newTimestamp;
        if (elapsed == 0) {
            return;
        }

        for (User user : users) {
            user.simulateTime(elapsed);
        }
    }

    /**
     * Gets top 5 songs.
     *
     * @return the top 5 songs
     */
    public static List<String> getTop5Songs() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        List<String> topSongs = new ArrayList<>();
        int count = 0;
        for (Song song : sortedSongs) {
            if (count >= TOP_COUNT) {
                break;
            }
            topSongs.add(song.getName());
            count++;
        }
        return topSongs;
    }

    /**
     * Gets top 5 playlists.
     *
     * @return the top 5 playlists
     */
    public static List<String> getTop5Playlists() {
        List<Playlist> sortedPlaylists = new ArrayList<>(getPlaylists());
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getFollowers)
                .reversed()
                .thenComparing(Playlist::getTimestamp, Comparator.naturalOrder()));
        List<String> topPlaylists = new ArrayList<>();
        int count = 0;
        for (Playlist playlist : sortedPlaylists) {
            if (count >= TOP_COUNT) {
                break;
            }
            topPlaylists.add(playlist.getName());
            count++;
        }
        return topPlaylists;
    }

    /**
     * Adds a new user to the system.
     *
     * @param username The username of the new user.
     * @param city The city where the new user lives.
     * @param age The age of the new user.
     * @param type The type of the new user.
     *        It can be "artist", "host", or "user", meaning normal user.
     * @return A string message indicating whether the user was added successfully or not.
     */
    public static String addUser(final String username, final String city,
                                 final Integer age, final String type) {
        User newuser = new User(username, age, city);
        switch (type) {
            case "artist" -> newuser.setUserOrartistOrhost(1);
            case "host" -> newuser.setUserOrartistOrhost(2);
            default -> {
                break;
            }
        }

        for (User i : users) {
            if (i.getName().equalsIgnoreCase(newuser.getName())) {
                return "The username " + newuser.getName() + " is already taken.";
            }
        }

        users.add(newuser);
        return "The username " + newuser.getName() + " has been added successfully.";

    }

    /**
     * Adds a new song to the list of songs.
     *
     * @param song The song to be added. It should not be null.
     */
    public static void addSong(final Song song) {
        songs.add(song);
    }

    /**
     * Retrieves all online users from the list of users.
     *
     * @return An ArrayList of usernames who are online and are not artists or hosts.
     * If no online users are found, an empty list is returned.
     */
    public static ArrayList<String> getOnlineUsers() {
        ArrayList<String> onlineusers = new ArrayList<>();
        for (User i : users) {
            if (i.isOnline() && i.getUserOrartistOrhost() == 0) {
                onlineusers.add(i.getName());
            }
        }
        return onlineusers;
    }

    /**
     * Retrieves all users' names from the list of users.
     * The method distinguishes between normal users, artists, and hosts.
     *
     * @return An ArrayList of usernames.
     */
    public static ArrayList<String> getAllUsers() {
        ArrayList<String> usernames = new ArrayList<>();
        for (User user : users) {
            if (user.getUserOrartistOrhost() == 0) { // user
                usernames.add(user.getName());
            }
        }
        for (User user : users) {
            if (user.getUserOrartistOrhost() == 1) { // artist
                usernames.add(user.getName());
            }
        }
        for (User user : users) {
            if (user.getUserOrartistOrhost() == 2) { // host
                usernames.add(user.getName());
            }
        }

        return usernames;
    }

    /**
     * Retrieves a song from the list of songs by its name.
     *
     * @param name The name of the song to be retrieved. It's case-insensitive.
     * @return The Song object if found, null otherwise.
     */
    public static Song getSong(final String name) {
        for (Song song : Admin.songs) {
            if (song.getName().equalsIgnoreCase(name)) {
                return song;
            }
        }
        return null;
    }

    /**
     * Retrieves an artist from the list of all artists by their name.
     *
     * @param name The name of the artist to be retrieved.
     * @return The User object representing the artist if found, null otherwise.
     */
    public static User getArtist(final String name) {
        for (User user : getAllArtists()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Removes an artist from the system. This includes removing all of their albums and songs.
     *
     * @param name The name of the artist to be removed.
     * @return A string message indicating that the artist was successfully deleted.
     */
    public static String removeArtist(final String name) {
        User artist = getArtist(name);

        assert artist != null;
        artist = new Artist(artist);

        Iterator<Album> albumIterator = artist.getAlbums().iterator();
        while (albumIterator.hasNext()) {
            Album album = albumIterator.next();
            removeAlbum(album.getName());  // remove album from admin

            Iterator<Song> songIterator = album.getSongs().iterator();
            while (songIterator.hasNext()) {
                Song song = songIterator.next();
                removeSong(song.getName()); // remove songs from admin
                removeLikedSong(song.getName());
            }
        }


        return name + " was successfully deleted.";

    }

    /**
     * Removes a host from the system. This includes removing all of their podcasts.
     *
     * @param thehost The User object representing the host to be removed.
     * @return A string message indicating that the host was successfully deleted.
     */
    public static String removeHost(final User thehost) {
        Host host = new Host(thehost);

        Iterator<Podcast> podcastIterator = host.getPodcasts().iterator();
        while (podcastIterator.hasNext()) {
            Podcast podcast = podcastIterator.next();
            removePodcast(podcast.getName()); // remove podcasts from admin
        }


        return host.getName() + " was successfully deleted.";
    }

    /**
     * Removes a liked song from all users.
     *
     * @param name The name of the song to be removed from the liked songs list of all users.
     */
    public static void removeLikedSong(final String name) {
        Iterator<User> userIterator = Admin.getUsers().iterator();

        while (userIterator.hasNext()) {
            User user = userIterator.next();
            user.removeLikedSong(name);
        }
    }

    /**
     * Removes a followed playlist from all users.
     *
     * @param name The name of the playlist to be removed
     *             from the followed playlists list of all users.
     */
    public static void removeFollowedPlaylist(final String name) {
        Iterator<User> userIterator = Admin.getUsers().iterator();

        while (userIterator.hasNext()) {
            User user = userIterator.next();
            user.removeFollowedPlaylist(name);
        }
    }

    /**
     * Removes a user from the system.
     *
     * @param name The name of the user to be removed.
     */
    public static void removeUser(final String name) {

        User user = Admin.getUser(name);
        Iterator<Playlist> playlistIterator = user.getPlaylists().iterator();

        while (playlistIterator.hasNext()) {
            Playlist playlist = playlistIterator.next();
            removeFollowedPlaylist(playlist.getName());
        }

        for (Playlist playlist : user.getFollowedPlaylists()) {
            playlist.decreaseFollowers();
        }

        users.removeIf(user1 -> user1.getName().equals(name));
    }

    /**
     * Removes an album from the list of albums by its name.
     *
     * @param name The name of the album to be removed.
     */
    public static void removeAlbum(final String name) {
        albums.removeIf(album -> album.getName().equals(name));
    }

    /**
     * Removes a song from the list of songs by its name.
     *
     * @param name The name of the song to be removed.
     */
    public static void removeSong(final String name) {
        songs.removeIf(song -> song.getName().equals(name));
    }

    /**
     * Removes a podcast from the list of podcasts by its name.
     *
     * @param name The name of the podcast to be removed.
     */
    public static void removePodcast(final String name) {
        podcasts.removeIf(podcast -> podcast.getName().equals(name));
    }

    /**
     * Retrieves the names of the top 5 albums in the system.
     *
     * @return An ArrayList of the names of the top 5 albums.
     */
    public static ArrayList<String> getTop5Albums() {
        List<Album> allAlbums = Admin.getAlbums();
        allAlbums.sort(Comparator.comparingInt(Album::getTotalLikes).reversed().
                thenComparing(Album::getName));
        ArrayList<String> top5Albums = new ArrayList<>();
        for (int i = 0; i < Math.min(TOP_COUNT, allAlbums.size()); i++) {
            top5Albums.add(allAlbums.get(i).getName());
        }

        return top5Albums;
    }

    /**
     * Retrieves the names of the top 5 artists in the system.
     *
     * @return An ArrayList of the names of the top 5 artists.
     */
    public static ArrayList<String> getTop5Artists() {
        List<Artist> allArtists = Admin.getAllArtists();

        allArtists.sort(Comparator.comparingInt(Artist::getTotalLikes).reversed());
        ArrayList<String> top5Artists = new ArrayList<>();

        for (int i = 0; i < Math.min(TOP_COUNT, allArtists.size()); i++) {
            top5Artists.add(allArtists.get(i).getName());
        }
        return top5Artists;
    }

    /**
     * Reset.
     */
    public static void reset() {
        users = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        albums = new ArrayList<>();
        timestamp = 0;
    }


}
