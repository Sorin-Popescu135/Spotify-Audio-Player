package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.LibraryEntry;
import lombok.Getter;

@Getter
public abstract class AudioCollection extends LibraryEntry {
    private final String owner;

    public AudioCollection(final String name, final String owner) {
        super(name);
        this.owner = owner;
    }

    /**
     * Retrieves the number of tracks in the audio collection.
     *
     * @return The number of tracks in the audio collection.
     */
    public abstract int getNumberOfTracks();

    /**
     * Retrieves a track from the audio collection by its index.
     *
     * @param index The index of the track to be retrieved.
     * @return The AudioFile object representing the track if found, null otherwise.
     */
    public abstract AudioFile getTrackByIndex(int index);

    @Override
    public final boolean matchesOwner(final String user) {
        return this.getOwner().equals(user);
    }
}
