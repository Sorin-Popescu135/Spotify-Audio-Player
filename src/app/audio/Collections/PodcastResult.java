package app.audio.Collections;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public final class PodcastResult {
    private String name;
    private ArrayList<String> episodes;

    public PodcastResult() {

    }

    public PodcastResult(final String name, final ArrayList<String> episodes) {
        this.name = name;
        this.episodes = episodes;
    }

    public void setName(final String name) {
        this.name = name; }

    public void setEpisodes(final ArrayList<String> episodes) {
        this.episodes = episodes; }
}
