package app.user;

import app.Admin;
import app.audio.Collections.Podcast;
import app.audio.Files.Announcement;
import app.audio.Files.Episode;
import app.interfaces.HostOperations;
import app.utils.visitor.UsageVisitor;
import app.utils.visitor.Visitable;
import app.utils.visitor.Visitor;

import java.util.ArrayList;

public final class Host extends User implements HostOperations, Visitable {

    public Host() {
        super();
    }

    public Host(final User user) {
        super(user.getName(), user.getAge(), user.getCity(),
                user.getPodcasts(), user.getAnnouncements());
    }


    @Override
    public String addPodcast(final Podcast podcast) {
        super.getPodcasts().add(podcast);
        Admin.addPodcast(podcast);


        return super.getName() + " has added new podcast successfully.";
    }

    @Override
    public boolean hasPodcastWithSameName(final Podcast podcast) {
        if (super.getPodcasts() == null) {
            return false;
        } else {
            for (Podcast existingpodcast : super.getPodcasts()) {
                if (existingpodcast.getName().equals(podcast.getName())) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String addAnnouncement(final Announcement announcement) {
        super.getAnnouncements().add(announcement);
        return super.getName() + " has successfully added new announcement.";
    }

    @Override
    public String removeAnnouncement(final Announcement announcement) {
        if (super.getAnnouncements() != null) {
            for (Announcement existingannouncement : super.getAnnouncements()) {
                if (existingannouncement.getName().equals(announcement.getName())) {
                    super.getAnnouncements().remove(existingannouncement);
                    return super.getName() + " has successfully deleted the announcement.";
                }
            }
        }
        return super.getName() + " has no announcement with the given name.";
    }


    @Override
    public boolean hasAnnouncementWithSameName(final Announcement announcement) {
        if (super.getAnnouncements() == null) {
            return false;
        } else {
            for (Announcement existingannouncement : super.getAnnouncements()) {
                if (existingannouncement.getName().equals(announcement.getName())) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public ArrayList<String> showEpisodesName(final Podcast podcast) {
        ArrayList<String> result = new ArrayList<>();

        for (Episode episode : podcast.getEpisodes()) {
            result.add(episode.getName());
        }

        return result;
    }

    @Override
    public String removePodcast(final Podcast podcast) {
        UsageVisitor visitor = new UsageVisitor();
        if (podcast.accept(visitor)) {
            return super.getName() + " can't delete this podcast.";
        } else {
            Admin.removePodcast(podcast.getName());

            super.getPodcasts().remove(podcast);
            return super.getName() + " deleted the podcast successfully.";
        }
    }

    @Override
    public boolean accept(final Visitor visitor) {
        return visitor.visit(this);
    }

}
