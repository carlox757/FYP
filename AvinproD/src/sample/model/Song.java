package sample.model;

import javafx.beans.property.*;

import java.sql.Date;

/**
 * Created by Carlito on 18/04/2017.
 */
public class Song {
    private IntegerProperty songID;
    private StringProperty songName;
    private StringProperty album;
    private SimpleObjectProperty<Date> dateCreated;




    public Song(){
        this.songID = new SimpleIntegerProperty();
        this.songName = new SimpleStringProperty();
        this.album = new SimpleStringProperty();
        this.dateCreated = new SimpleObjectProperty<>();
    }



    public int getSongID() {
        return songID.get();
    }

    public void setSongID(int song_id) {
        this.songID.set(song_id);
    }

    public IntegerProperty songIDProperty(){
        return songID;
    }


    public String getAlbum() {
        return album.get();
    }

    public void setAlbum(String album) {
        this.album.set(album);
    }

    public StringProperty albumProperty(){
        return album;
    }



    public String getSongName() {
        return songName.get();
    }

    public void setSongName(String songName) {
        this.songName.set(songName);
    }

    public StringProperty songNameProperty(){
        return songName;
    }



    public Object getDateCreated() {
        return dateCreated.get();
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated.set(dateCreated);
    }

    public SimpleObjectProperty<Date> songDateProperty(){
        return dateCreated;
    }



}
