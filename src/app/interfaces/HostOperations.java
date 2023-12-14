package app.interfaces;

import app.audio.Collections.Podcast;
import app.audio.Files.Announcement;

import java.util.ArrayList;

public interface HostOperations {

    /**
     * Adds a podcast to the host's collection.
     *
     * @param podcast The podcast to be added.
     * @return A string message indicating the result of the operation.
     */
    String addPodcast(Podcast podcast);

    /**
     * Checks if the host has a podcast with the same name.
     *
     * @param podcast The podcast to check.
     * @return True if a podcast with the same name exists, false otherwise.
     */
    boolean hasPodcastWithSameName(Podcast podcast);

    /**
     * Adds an announcement for the host.
     *
     * @param announcement The announcement to be added.
     * @return A string message indicating the result of the operation.
     */
    String addAnnouncement(Announcement announcement);

    /**
     * Removes an announcement from the host.
     *
     * @param announcement The announcement to be removed.
     * @return A string message indicating the result of the operation.
     */
    String removeAnnouncement(Announcement announcement);

    /**
     * Checks if the host has an announcement with the same name.
     *
     * @param announcement The announcement to check.
     * @return True if an announcement with the same name exists, false otherwise.
     */
    boolean hasAnnouncementWithSameName(Announcement announcement);

    /**
     * Shows the names of the episodes in a podcast.
     *
     * @param podcast The podcast whose episodes are to be displayed.
     * @return An ArrayList of episode names.
     */
    ArrayList<String> showEpisodesName(Podcast podcast);

    /**
     * Removes a podcast from the host's collection.
     *
     * @param podcast The podcast to be removed.
     * @return A string message indicating the result of the operation.
     */
    String removePodcast(Podcast podcast);
}
