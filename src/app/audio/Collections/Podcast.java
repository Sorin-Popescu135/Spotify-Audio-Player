package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Episode;
import app.utils.visitor.Visitable;
import app.utils.visitor.Visitor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Podcast extends AudioCollection implements Visitable {
    private final List<Episode> episodes;

    public Podcast(final String name, final String owner, final List<Episode> episodes) {
        super(name, owner);
        this.episodes = episodes;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    @Override
    public int getNumberOfTracks() {
        return episodes.size();
    }

    @Override
    public AudioFile getTrackByIndex(final int index) {
        return episodes.get(index);
    }

    /**
     * Checks if the podcast contains duplicate episodes.
     *
     * @return true if there are duplicate episodes in the podcast, false otherwise.
     */
    public boolean hasDuplicateEpisodes() {
        Set<String> seenEpisodeNames = new HashSet<>();
        for (Episode episode : episodes) {
            if (seenEpisodeNames.contains(episode.getName())) {
                return true;
            }
            seenEpisodeNames.add(episode.getName());
        }
        return false;
    }

    @Override
    public boolean accept(final Visitor visitor) {
        return visitor.visit(this);
    }
}
