package app.utils.visitor;

import app.Admin;
import app.audio.Collections.Album;
import app.audio.Collections.AudioCollection;
import app.audio.Collections.Playlist;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.user.Artist;
import app.user.Host;
import app.user.User;

public final class UsageVisitor implements Visitor {

    @Override
    public boolean visit(final User user) {
        if (user.getPlayer().getCurrentAudioFile() != null) {
            return true;
        }
        if (user.getCurrentPage().equals("artist") || user.getCurrentPage().equals("host")) {
            return true;
        }

        for (User theuser : Admin.getUsers()) {
            AudioCollection aux = theuser.getPlayer().getCurrentAudioCollection();

            if (aux != null) {
                if (aux.getOwner().equals(user.getName())) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean visit(final Artist artist) {
        for (User user : Admin.getUsers()) {
            if (user.getPlayer().getCurrentAudioFile() != null) {
                Song loadedSong = Admin.getSong(user.getPlayer().getCurrentAudioFile().getName());
                if (loadedSong != null && loadedSong.getArtist()
                        .equalsIgnoreCase(artist.getName())) {
                    return true;
                }
            }

            if (user.getCurrentPage().equals("artist")
                    && user.getPageOwner().equals(artist.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean visit(final Host host) {
        for (User user : Admin.getUsers()) {
            if (user.getPlayer().getCurrentAudioFile() != null) {
                Episode loadedEpisode = Admin.getEpisode(user.getPlayer()
                        .getCurrentAudioFile().getName());
                if (loadedEpisode != null) {
                    for (Podcast podcast : host.getPodcasts()) {
                        if (podcast.getEpisodes().contains(loadedEpisode)) {
                            return true;
                        }
                    }
                }
            }
            if (user.getCurrentPage().equals("host")
                    && user.getPageOwner().equals(host.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean visit(final Album album) {
        for (User user : Admin.getUsers()) {
            if (user.getPlayer().getCurrentAudioFile() != null) {
                Song loadedSong = Admin.getSong(user.getPlayer()
                        .getCurrentAudioFile().getName());
                if (loadedSong != null && loadedSong.getAlbum().equals(album.getName())) {
                    return true;
                }
            }

            AudioCollection x = user.getPlayer().getCurrentAudioCollection();
            if (x != null && user.getPlayer().getType().equals("playlist")) {
                for (Song i : ((Playlist) x).getSongs()) {
                    if (album.getSongs().contains(i)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean visit(final Podcast podcast) {
        for (User user : Admin.getUsers()) {
            if (user.getPlayer().getCurrentAudioFile() != null) {
                Episode loadedEpisode = Admin.getEpisode(user.getPlayer()
                        .getCurrentAudioFile().getName());
                if (loadedEpisode != null && podcast.getEpisodes().contains(loadedEpisode)) {
                    return true;
                }
            }
        }
        return false;
    }
}
