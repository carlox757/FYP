package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.util.DBUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Carlito on 18/04/2017.
 */
public class SongDao {
    public static Song searchSong(String songId) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM song WHERE songID = "+songId;

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsSong = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getSong from resultset method and get Song object
            Song Song = getSongFromResultSet(rsSong);

            //Return Song object
            return Song;
        } catch (SQLException e) {
            System.out.println("While searching a song with " + songId + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    private static Song getSongFromResultSet(ResultSet rs) throws SQLException
    {
        Song song = null;
        if (rs.next()) {
            song = new Song();
            song.setSongID(rs.getInt("songID"));
            song.setSongName(rs.getString("SongName"));
            song.setAlbum(rs.getString("album"));
            song.setDateCreated(rs.getDate("dateCreated"));

        }
        return song;
    }


    //*******************************
    //SELECT All Songs
    //*******************************
    public static ObservableList<Song> searchAllSongs() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM song";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsSongs = DBUtil.dbExecuteQuery(selectStmt);


            //Send ResultSet to the getSongsList method and get Song object
            ObservableList<Song> SongList = getSongsList(rsSongs);

            //Return Song object
            return SongList;

        } catch (SQLException e) {
            System.out.println("SQL select operation has failed: " + e);
            //Return exception
            throw e;
        }
    }

    //Select * from Songs operation
    private static ObservableList<Song> getSongsList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Songs objects
        ObservableList<Song> songsList = FXCollections.observableArrayList();

        while (rs.next()) {
            Song song = new Song();
            song.setSongID(rs.getInt("songId"));
            song.setSongName(rs.getString("SongName"));
            song.setAlbum(rs.getString("album"));
            song.setDateCreated(rs.getDate("dateCreated"));
            //Add Song to the ObservableList
            songsList.add(song);

        }

        //return Song list (ObservableList of Songs)
        return songsList;
    }

    public static void insertSong (String songName, String album, Date dateCreated  ) throws SQLException, ClassNotFoundException {
        //Declare a Update statement
        String updateStmt = "INSERT INTO song(songName, album, dateCreated) VALUES('"+songName+"', '"+album+"', '"+dateCreated+"')";


        //Execute Update operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while Update Operation: " + e);
            throw e;
        }
    }

    public static void deleteSongByID (String songID) throws SQLException, ClassNotFoundException {
        //Declare a Update statement
        String updateStmt = "DELETE FROM song WHERE songID = "+songID;
        System.out.println(updateStmt);
        //Execute Update operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while Update Operation: " + e);
            throw e;
        }
    }
}
