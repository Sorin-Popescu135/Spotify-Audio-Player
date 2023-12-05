package app.interfaces;

import app.audio.Collections.Album;
import app.utils.BooleanWrapper;
import app.utils.StringWrapper;

import java.util.ArrayList;

public interface ArtistOperations {
    void addAlbum(Album album, StringWrapper message);

    ArrayList<Album> getUserAlbums();

    void hasAlbumWithSameName(Album album, BooleanWrapper bool);

    ArrayList<String> showAlbumsName(Album album);
}
