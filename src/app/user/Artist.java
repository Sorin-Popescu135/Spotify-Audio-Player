package app.user;

import app.Admin;
import app.audio.Collections.Album;
import app.audio.Files.Event;
import app.audio.Files.Merch;
import app.audio.Files.Song;
import app.interfaces.ArtistOperations;
import app.utils.visitor.UsageVisitor;
import app.utils.visitor.Visitable;
import app.utils.visitor.Visitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;

@Getter
public final class Artist extends User implements ArtistOperations, Visitable {

    public Artist() {
        super();
    }

    public Artist(final User user) {
        super(user.getName(), user.getAge(), user.getCity(),
                user.getAlbums(), user.getEvents(), user.getMerches());
    }

    @Override
    public String addAlbum(final Album album) {
        super.getAlbums().add(album);

        for (Song song : album.getSongs()) {
            Admin.addSong(song);
        }

        Admin.addAlbum(album);

        return this.getName() + " has added new album successfully.";

    }

    @Override
    public ArrayList<Album> getUserAlbums() {
        return super.getAlbums();
    }

    @Override
    public boolean hasAlbumWithSameName(final Album album) {
        if (super.getAlbums().isEmpty()) {
            return false;
        } else {
            for (Album existingAlbum : super.getAlbums()) {
                if (existingAlbum.getName().equalsIgnoreCase(album.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the artist has an event with the same name.
     *
     * @param event The event to check.
     * @return True if an event with the same name exists, false otherwise.
     */
    public boolean hasEventWithSameName(final Event event) {
        if (super.getEvents().isEmpty()) {
            return false;
        } else {
            for (Event existingevent : super.getEvents()) {
                if (existingevent.getName().equalsIgnoreCase(event.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the artist has merchandise with the same name.
     *
     * @param merch The merchandise to check.
     * @return True if merchandise with the same name exists, false otherwise.
     */
    public boolean hasMerchWithSameName(final Merch merch) {
        if (super.getMerches().isEmpty()) {
            return false;
        } else {
            for (Merch existingmerch : super.getMerches()) {
                if (existingmerch.getName().equalsIgnoreCase(merch.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> showSongsName(final Album album) {
        ArrayList<String> names = new ArrayList<>();
        for (Song song : album.getSongs()) {
            names.add(song.getName());
        }
        return names;
    }

    @Override
    public String addEvent(final Event event) {
        super.getEvents().add(event);

        return this.getName() + " has added new event successfully.";
    }

    @Override
    public String addMerch(final Merch merch) {
        super.getMerches().add(merch);

        return this.getName() + " has added new merchandise successfully.";
    }

    @Override
    public String removeAlbum(final Album album) {
        UsageVisitor isUsed = new UsageVisitor();
        if (!hasAlbumWithSameName(album)) {
            return super.getName() + " doesn't have an album with the given name.";
        } else if (album.accept(isUsed)) {
            return super.getName() + " can't delete this album.";
        } else {
            Iterator<Song> songIterator = album.getSongs().iterator();
            while (songIterator.hasNext()) {
                Song song = songIterator.next();
                Admin.removeSong(song.getName()); // remove songs from admin
                removeLikedSong(song.getName());
            }

            Admin.removeAlbum(album.getName());
            super.getAlbums().remove(album);


            return super.getName() + " deleted the album successfully.";
        }
    }

    @Override
    public String removeEvent(final String name) {
        for (Event existingevent : getEvents()) {
            if (existingevent.getName().equals(name)) {
                getEvents().remove(existingevent);
                return super.getName() + " deleted the event successfully.";
            }
        }
        return null;
    }

    @Override
    public boolean hasEventWithSameName(final String name) {
        if (super.getEvents() == null) {
            return false;
        } else {
            for (Event existingevent : getEvents()) {
                if (existingevent.getName().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Calculates the total number of likes across all albums of the artist.
     *
     * @return The total number of likes.
     */
    public Integer getTotalLikes() {
        Integer totallikes = 0;

        for (Album album : super.getAlbums()) {
            totallikes += album.getTotalLikes();
        }

        return totallikes;
    }


    @Override
    public boolean accept(final Visitor visitor) {
        return visitor.visit(this);
    }

}

