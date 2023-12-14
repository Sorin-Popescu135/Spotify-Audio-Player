package app.utils.visitor;

import app.audio.Collections.Album;
import app.audio.Collections.Podcast;
import app.user.Artist;
import app.user.Host;
import app.user.User;

public interface Visitor {
    /**
     * Visits a User object.
     * Checks if the user isn't used and can be safely deleted.
     * @param user The User object to be visited.
     * @return True if the visit is successful, false otherwise.
     */
    boolean visit(User user);

    /**
     * Visits an Artist object.
     * Checks if the artist isn't used and can be safely deleted.
     * @param artist The Artist object to be visited.
     * @return True if the visit is successful, false otherwise.
     */
    boolean visit(Artist artist);

    /**
     * Visits a Host object.
     * Checks if the host isn't used and can be safely deleted.
     * @param host The Host object to be visited.
     * @return True if the visit is successful, false otherwise.
     */
    boolean visit(Host host);

    /**
     * Visits an Album object.
     * Checks if the album isn't used and can be safely deleted.
     * @param album The Album object to be visited.
     * @return True if the visit is successful, false otherwise.
     */
    boolean visit(Album album);

    /**
     * Visits a Podcast object.
     * Checks if the podcast isn't used and can be safely deleted.
     * @param podcast The Podcast object to be visited.
     * @return True if the visit is successful, false otherwise.
     */
    boolean visit(Podcast podcast);

}
