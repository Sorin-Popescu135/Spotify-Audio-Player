package app;

import app.audio.Collections.Album;
import app.audio.Collections.Podcast;
import app.audio.Collections.AlbumResult;
import app.audio.Collections.PodcastResult;
import app.audio.Collections.PlaylistOutput;
import app.audio.Files.Song;
import app.audio.Files.Event;
import app.audio.Files.Merch;
import app.audio.Files.Announcement;
import app.audio.Files.Episode;
import app.player.PlayerStats;
import app.searchBar.Filters;
import app.user.Artist;
import app.user.Host;
import app.user.User;
import app.utils.VerifyDate;
import app.utils.visitor.UsageVisitor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.CommandInput;
import fileio.input.EpisodeInput;
import fileio.input.SongInput;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Command runner.
 */
public final class CommandRunner {
    /**
     * The Object mapper.
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private CommandRunner() {

    }

    /**
     * Search object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode search(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        Filters filters = new Filters(commandInput.getFilters());
        String type = commandInput.getType();

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        String message = null;

        if (!user.isOnline()) {
            message = user.getName() + " is offline.";
            objectNode.put("message", message);
            ArrayList<String> results = new ArrayList<>();
            objectNode.put("results", OBJECT_MAPPER.valueToTree(results));

        } else {
            ArrayList<String> results = user.search(filters, type);
            message = "Search returned " + results.size() + " results";
            objectNode.put("message", message);
            objectNode.put("results", OBJECT_MAPPER.valueToTree(results));

        }

        return objectNode;
    }

    /**
     * Select object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode select(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        String message = user.select(commandInput.getItemNumber());

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Load object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode load(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.load();

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Play pause object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode playPause(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.playPause();

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Repeat object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode repeat(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.repeat();

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Shuffle object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode shuffle(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        Integer seed = commandInput.getSeed();
        String message = user.shuffle(seed);

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Forward object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode forward(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.forward();

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Backward object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode backward(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.backward();

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Like object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode like(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.like();

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Next object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode next(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.next();

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Prev object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode prev(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.prev();

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Create playlist object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode createPlaylist(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.createPlaylist(commandInput.getPlaylistName(),
                commandInput.getTimestamp());

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Add remove in playlist object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addRemoveInPlaylist(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.addRemoveInPlaylist(commandInput.getPlaylistId());

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Switch visibility object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode switchVisibility(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.switchPlaylistVisibility(commandInput.getPlaylistId());

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Show playlists object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showPlaylists(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        ArrayList<PlaylistOutput> playlists = user.showPlaylists();

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", OBJECT_MAPPER.valueToTree(playlists));

        return objectNode;
    }

    /**
     * Follow object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode follow(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.follow();

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Status object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode status(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        PlayerStats stats = user.getPlayerStats();

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("stats", OBJECT_MAPPER.valueToTree(stats));

        return objectNode;
    }

    /**
     * Show liked songs object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showLikedSongs(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        ArrayList<String> songs = user.showPreferredSongs();

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", OBJECT_MAPPER.valueToTree(songs));

        return objectNode;
    }

    /**
     * Gets preferred genre.
     *
     * @param commandInput the command input
     * @return the preferred genre
     */
    public static ObjectNode getPreferredGenre(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String preferredGenre = user.getPreferredGenre();

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", OBJECT_MAPPER.valueToTree(preferredGenre));

        return objectNode;
    }

    /**
     * Gets top 5 songs.
     *
     * @param commandInput the command input
     * @return the top 5 songs
     */
    public static ObjectNode getTop5Songs(final CommandInput commandInput) {
        List<String> songs = Admin.getTop5Songs();

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", OBJECT_MAPPER.valueToTree(songs));

        return objectNode;
    }

    /**
     * Gets top 5 playlists.
     *
     * @param commandInput the command input
     * @return the top 5 playlists
     */
    public static ObjectNode getTop5Playlists(final CommandInput commandInput) {
        List<String> playlists = Admin.getTop5Playlists();

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", OBJECT_MAPPER.valueToTree(playlists));

        return objectNode;
    }

    /**
     * Adds an user.
     *
     * @param commandInput the command input
     * @return the object node.
     */
    public static ObjectNode addUser(final CommandInput commandInput) {

        String username = commandInput.getUsername();
        String type = commandInput.getType();
        String city = commandInput.getCity();
        Integer age = commandInput.getAge();

        String message = Admin.addUser(username, city, age, type);

        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", username);
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", OBJECT_MAPPER.valueToTree(message));

        return objectNode;
    }

    /**
     * switch connection status for the user
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode switchConnectionStatus(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        String message = null;

        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("user", commandInput.getUsername());

        User user = Admin.getUser(commandInput.getUsername());
        if (user == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
            objectNode.put("message", message);
            return objectNode;
        }

        message = user.switchConnectionStatus();
        objectNode.put("message", OBJECT_MAPPER.valueToTree(message));

        return objectNode;
    }

    /**
     * Gets online users.
     *
     * @param commandInput the command input
     * @return the online users.
     */
    public static ObjectNode getOnlineUsers(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();

        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());

        ArrayList<String> result = Admin.getOnlineUsers();
        objectNode.put("result", OBJECT_MAPPER.valueToTree(result));

        return objectNode;
    }

    /**
     * Adds an album for the artist.
     *
     * @param commandInput the command input
     * @return the object node.
     */
    public static ObjectNode addAlbum(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        User user = Admin.getUser(commandInput.getUsername());
        String message = null;

        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        ArrayList<Song> songs = new ArrayList<>();
        for (SongInput i : commandInput.getSongs()) {
            Song newformat = new Song(i);
            songs.add(newformat);
        }

        Album album = new Album(commandInput.getReleaseYear(), commandInput.getName(),
                songs, commandInput.getDescription(), commandInput.getUsername());

        if (user == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (user.getUserOrartistOrhost() != 1) {
            message = user.getName() + " is not an artist.";
        } else {

            user = new Artist(user);

            if (((Artist) user).hasAlbumWithSameName(album)) {
                message = user.getName() + " has another album with the same name.";
            } else if (album.hasDuplicateSongs()) {
                message = user.getName() + " has the same song at least twice in this album.";
            } else {
                message = ((Artist) user).addAlbum(album);
            }

        }

        objectNode.put("message", OBJECT_MAPPER.valueToTree(message));
        return objectNode;
    }

    /**
     * Shows artist's albums.
     *
     * @param commandInput the command input
     * @return the albums.
     */
    public static ObjectNode showAlbums(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        User user = Admin.getUser(commandInput.getUsername());
        user = new Artist(user);

        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        ArrayList<AlbumResult> result = new ArrayList<>();

        for (Album album : user.getAlbums()) {
            ArrayList<String> names = ((Artist) user).showSongsName(album);
            AlbumResult newresult = new AlbumResult(album.getName(), names);
            result.add(newresult);
        }

        objectNode.put("result", OBJECT_MAPPER.valueToTree(result));


        return objectNode;
    }

    /**
     * Prints the page the user is on.
     *
     * @param commandInput the command input
     * @return the page.
     */
    public static ObjectNode printCurrentPage(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        User user = Admin.getUser(commandInput.getUsername());
        String message = null;

        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());

        if (!user.isOnline()) {
            message = user.getName() + " is offline.";
            objectNode.put("message", message);
        } else {
            switch (user.getCurrentPage()) {
                case "Home" -> objectNode.put("message",
                        OBJECT_MAPPER.valueToTree(user.printHomePage()));
                case "artist" -> objectNode.put("message",
                        OBJECT_MAPPER.valueToTree(user.printArtistPage()));
                case "host" -> objectNode.put("message",
                        OBJECT_MAPPER.valueToTree(user.printHostPage()));
                case "LikedContent" -> objectNode.put("message",
                        OBJECT_MAPPER.valueToTree(user.printLikedContentPage()));
                default -> {

                }
            }
        }

        return objectNode;
    }

    /**
     * Adds an event for the artist.
     *
     * @param commandInput the command input
     * @return the object node.
     */
    public static ObjectNode addEvent(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        User user = Admin.getUser(commandInput.getUsername());

        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        String message;

        if (user == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (user.getUserOrartistOrhost() != 1) {
            message = user.getName() + " is not an artist.";
        } else if (VerifyDate.isDateWrong(commandInput.getDate())) {
            message = "Event for " + user.getName() + " does not have a valid date.";
        } else {
            user = new Artist(user);
            Event event = new Event(commandInput.getName(),
                    commandInput.getDescription(), commandInput.getDate());

            if (((Artist) user).hasEventWithSameName(event)) {
                message = user.getName() + " has another event with the same name.";
            } else {
                message = ((Artist) user).addEvent(event);
            }
        }

        objectNode.put("message", OBJECT_MAPPER.valueToTree(message));

        return objectNode;
    }

    /**
     * Adds a merch for the artist.
     *
     * @param commandInput the command input
     * @return the object node.
     */
    public static ObjectNode addMerch(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        User user = Admin.getUser(commandInput.getUsername());

        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        String message;

        if (user == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (user.getUserOrartistOrhost() != 1) {
            message = user.getName() + " is not an artist.";
        } else if (commandInput.getPrice() < 0) {
            message = "Price for merchandise can not be negative.";
        } else {
            Merch merch = new Merch(commandInput.getName(),
                    commandInput.getDescription(), commandInput.getPrice());
            user = new Artist(user);

            if (((Artist) user).hasMerchWithSameName(merch)) {
                message = user.getName() + " has merchandise with the same name.";
            } else {
                message = ((Artist) user).addMerch(merch);
            }
        }
        objectNode.put("message", OBJECT_MAPPER.valueToTree(message));

        return objectNode;
    }

    /**
     * Gets all users.
     *
     * @param commandInput the command input
     * @return the users.
     */
    public static ObjectNode getAllUsers(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();

        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", OBJECT_MAPPER.valueToTree(Admin.getAllUsers()));

        return objectNode;
    }

    /**
     * Deletes an user.
     *
     * @param commandInput the command input
     * @return the object node.
     */
    public static ObjectNode deleteUser(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        User user = Admin.getUser(commandInput.getUsername());

        String message = null;

        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        UsageVisitor isUsed = new UsageVisitor();

        if (user == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (user.getUserOrartistOrhost() == 0) { // normal user
            if (user.accept(isUsed)) {
                message = user.getName() + " can't be deleted.";
            } else {
                Admin.removeUser(user.getName());
                message = user.getName() + " was successfully deleted.";
            }
        } else if (user.getUserOrartistOrhost() == 1) { // artist
            user = new Artist(user);
            if (((Artist) user).accept(isUsed)) {
                message = user.getName() + " can't be deleted.";
            } else {
                message = Admin.removeArtist(user.getName());
            }
        } else if (user.getUserOrartistOrhost() == 2) { // host
            user = new Host(user);
            if (((Host) user).accept(isUsed)) {
                message = user.getName() + " can't be deleted.";
            } else {
                message = Admin.removeHost(user);
            }
        }

        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Adds a podcast for the host.
     *
     * @param commandInput the command input
     * @return the object node.
     */
    public static ObjectNode addPodcast(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        User user = Admin.getUser(commandInput.getUsername());

        String message = null;

        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        ArrayList<Episode> episodes = new ArrayList<>();

        for (EpisodeInput episodeInput : commandInput.getEpisodes()) {
            Episode ep = new Episode(episodeInput.getName(),
                    episodeInput.getDuration(), episodeInput.getDescription());
            episodes.add(ep);
        }

        Podcast podcast = new Podcast(commandInput.getName(), commandInput.getName(), episodes);

        if (user == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (user.getUserOrartistOrhost() != 2) {
            message = user.getName() + " is not a host.";
        } else if (podcast.hasDuplicateEpisodes()) {
            message = user.getName() + " has the same episode in this podcast.";
        } else {
            user = new Host(user);

            if (((Host) user).hasPodcastWithSameName(podcast)) {
                message = user.getName() + " has another podcast with the same name.";
            } else {
                message = ((Host) user).addPodcast(podcast);
            }
        }

        objectNode.put("message", OBJECT_MAPPER.valueToTree(message));

        return objectNode;
    }

    /**
     * Adds an announcement for the host.
     *
     * @param commandInput the command input
     * @return the object node.
     */
    public static ObjectNode addAnnouncement(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        User user = Admin.getUser(commandInput.getUsername());

        String message = null;

        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        Announcement announcement = new Announcement(commandInput.getName(),
                commandInput.getDescription());


        if (user == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (user.getUserOrartistOrhost() != 2) {
            message = user.getName() + " is not a host.";
        } else {
            user = new Host(user);

            if (((Host) user).hasAnnouncementWithSameName(announcement)) {
                message = user.getName() + " has already added an announcement with this name.";
            } else {
                message = ((Host) user).addAnnouncement(announcement);
            }
        }

        objectNode.put("message", OBJECT_MAPPER.valueToTree(message));

        return objectNode;
    }

    /**
     * remove an announcement for the host.
     *
     * @param commandInput the command input
     * @return the object node.
     */
    public static ObjectNode removeAnnouncement(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        User user = Admin.getUser(commandInput.getUsername());

        String message = null;

        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        Announcement announcement = new Announcement(commandInput.getName(),
                commandInput.getDescription());

        if (user == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (user.getUserOrartistOrhost() != 2) {
            message = user.getName() + " is not a host.";
        } else {
            user = new Host(user);
            message = ((Host) user).removeAnnouncement(announcement);
        }

        objectNode.put("message", OBJECT_MAPPER.valueToTree(message));

        return objectNode;
    }

    /**
     * Shows host's podcasts.
     *
     * @param commandInput the command input
     * @return the podcasts.
     */
    public static ObjectNode showPodcasts(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        User user = Admin.getUser(commandInput.getUsername());
        user = new Host(user);

        ArrayList<PodcastResult> result = new ArrayList<>();

        String message = null;

        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        for (Podcast podcast : user.getPodcasts()) {
            ArrayList<String> epnames = ((Host) user).showEpisodesName(podcast);
            PodcastResult newresult = new PodcastResult(podcast.getName(), epnames);
            result.add(newresult);
        }

        objectNode.put("result", OBJECT_MAPPER.valueToTree(result));

        return objectNode;
    }

    /**
     * remove an album for the host.
     *
     * @param commandInput the command input
     * @return the object node.
     */
    public static ObjectNode removeAlbum(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        User user = Admin.getUser(commandInput.getUsername());

        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        String message = null;

        if (user == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (user.getUserOrartistOrhost() != 1) { // user is not an artist
            message = user.getName() + " is not an artist.";
        } else {
            user = new Artist(user);
            Album album = Admin.getAlbum(commandInput.getName());
            if (album == null) {
                message = commandInput.getUsername()
                        + " doesn't have an album with the given name.";
            } else {
                message = ((Artist) user).removeAlbum(album);
            }
        }

        objectNode.put("message", OBJECT_MAPPER.valueToTree(message));

        return objectNode;
    }

    /**
     * change the page the user is currently on.
     *
     * @param commandInput the command input
     * @return the object node.
     */
    public static ObjectNode changePage(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        User user = Admin.getUser(commandInput.getUsername());

        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        String message = null;

        if (user == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (!user.isOnline()) {
            message = user.getName() + " is offline.";
        } else if (user.getUserOrartistOrhost() != 0) {
            message = user.getName() + " is trying to access a non-existent page.";
        } else {
            switch (commandInput.getNextPage()) {
                case "Home" -> user.setCurrentPage("Home");
                case "LikedContent" -> user.setCurrentPage("LikedContent");
                default -> {
                }
            }
            message = user.getName() + " accessed " + user.getCurrentPage() + " successfully.";
        }

        objectNode.put("message", OBJECT_MAPPER.valueToTree(message));

        return objectNode;
    }

    /**
     * remove a podcast for the host.
     *
     * @param commandInput the command input
     * @return the object node.
     */
    public static ObjectNode removePodcast(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        User user = Admin.getUser(commandInput.getUsername());


        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        String message = null;

        if (user == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (user.getUserOrartistOrhost() != 2) { // user is not a host
            message = user.getName() + " is not a host.";
        } else {
            user = new Host(user);
            Podcast podcast = Admin.getPodcast(commandInput.getName());
            if (podcast == null || !((Host) user).hasPodcastWithSameName(podcast)) {
                message = user.getName() + " doesn't have a podcast with the given name.";
            } else {
                message = ((Host) user).removePodcast(podcast);
            }
        }
        objectNode.put("message", OBJECT_MAPPER.valueToTree(message));

        return objectNode;
    }

    /**
     * remove the event for the artist.
     *
     * @param commandInput the command input
     * @return the object node.
     */
    public static ObjectNode removeEvent(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        User user = Admin.getUser(commandInput.getUsername());


        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        String message = null;

        if (user == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (user.getUserOrartistOrhost() != 1) { // user is not an artist
            message = user.getName() + " is not an artist.";
        } else {
            user = new Artist(user);
            if (!((Artist) user).hasEventWithSameName(commandInput.getName())) {
                message = commandInput.getUsername()
                        + " doesn't have an album with the given name.";
            } else {
                message = ((Artist) user).removeEvent(commandInput.getName());
            }
        }
        objectNode.put("message", OBJECT_MAPPER.valueToTree(message));

        return objectNode;
    }

    /**
     * gets top 5 albums.
     *
     * @param commandInput the command input
     * @return the albums.
     */
    public static ObjectNode getTop5Albums(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();

        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", OBJECT_MAPPER.valueToTree(Admin.getTop5Albums()));

        return objectNode;
    }
    /**
     * gets top 5 artists.
     *
     * @param commandInput the command input
     * @return the artists.
     */
    public static ObjectNode getTop5Artists(final CommandInput commandInput) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();

        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", OBJECT_MAPPER.valueToTree(Admin.getTop5Artists()));

        return objectNode;
    }

}



