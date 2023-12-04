package app.user;

import app.audio.Collections.Album;
import app.utils.BooleanWrapper;
import app.utils.StringWrapper;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Artist extends User{

    public Artist(){
        super();
    }

    public Artist(User user){
        super(user.getUsername(), user.getAge(), user.getCity(), user.getAlbums());
    }



    @Override
    public void addAlbum(Album album, StringWrapper message) {
        super.getAlbums().add(album);
        message.setValue(this.getUsername() + " has added new album successfully.");
    }


    @Override
    public ArrayList<Album> getUserAlbums(){ return super.getAlbums(); }

    @Override
    public void hasAlbumWithSameName(Album album, BooleanWrapper bool) {
        if(super.getAlbums().isEmpty()){
            bool.setBool(false);
        } else {
            for (Album existingAlbum : super.getAlbums()) {
                if (existingAlbum.getName().equalsIgnoreCase(album.getName())) {
                    bool.setBool(true);
                    return;
                }
            }
        }
    }


}

