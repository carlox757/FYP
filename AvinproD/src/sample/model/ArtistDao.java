package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.util.DBUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Carlito on 12/04/2017.
 */

public class ArtistDao {

    public static Artist searchArtist(String artID) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM artist WHERE artistId = "+artID;

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsArt = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getArtist from resultset method and get Artist object
            Artist artist = getArtistFromResultSet(rsArt);


            //Return Artist object
            return artist;
        } catch (SQLException e) {
            System.out.println("While searching an Artist with " + artID + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    public static ObservableList<Artist> searchArtistByGender(String gender) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement

        String selectStmt = "SELECT * FROM artist WHERE gender = '"+gender+"'" ;

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsArts = DBUtil.dbExecuteQuery(selectStmt);


            //Send ResultSet to the getArtistsList method and get artist object
            ObservableList<Artist> artistList = getArtistsList(rsArts);

            //Return Artist object
            return artistList;

        } catch (SQLException e) {
            System.out.println("While filtering the Artists with " + gender + ", an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    public static ObservableList<Artist> searchArtistByType(String type) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM artist WHERE typeOfArtist = '"+type+"'";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsArts = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getArtistsList method and get artist object
            ObservableList<Artist> artistList = getArtistsList(rsArts);

            //Return Artist object List

            return artistList;

        } catch (SQLException e) {
            System.out.println("While filtering  Artists with " + type + ", an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    private static Artist getArtistFromResultSet(ResultSet rs) throws SQLException
    {
        Artist artist = null;
        if (rs.next()) {
            artist = new Artist();
            artist.setArtistID(rs.getInt("artistId"));
            artist.setArtistName(rs.getString("artistName"));
            artist.setArtistSurname(rs.getString("artistSurname"));
            artist.setArtistEmail(rs.getString("artistEmail"));
            artist.setTypeOfArtist(rs.getString("typeOfArtist"));
            artist.setArtistPhoneNumber(rs.getString("artistPhoneNumber"));
            artist.setDateOfBirth(rs.getDate("dateOfBirth"));
            artist.setGender(rs.getString("gender"));

        }
        return artist;
    }


    //*******************************
    //SELECT All ARTISTS
    //*******************************
    public static ObservableList<Artist> searchArtists() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM artist";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsArts = DBUtil.dbExecuteQuery(selectStmt);


            //Send ResultSet to the getArtistsList method and get artist object
            ObservableList<Artist> artistList = getArtistsList(rsArts);

            //Return Artist object
            return artistList;

        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    //Select * from Artists operation
    private static ObservableList<Artist> getArtistsList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Artists objects
        ObservableList<Artist> artistsList = FXCollections.observableArrayList();

        while (rs.next()) {
            Artist artist = new Artist();
            artist.setArtistID(rs.getInt("artistId"));
            artist.setArtistName(rs.getString("artistName"));
            artist.setArtistSurname(rs.getString("artistSurname"));
            artist.setArtistEmail(rs.getString("artistEmail"));
            artist.setTypeOfArtist(rs.getString("typeOfArtist"));
            artist.setArtistPhoneNumber(rs.getString("artistPhoneNumber"));
            artist.setDateOfBirth(rs.getDate("dateOfBirth"));
            artist.setGender(rs.getString("gender"));
            //Add Artist to the ObservableList
            artistsList.add(artist);

        }

        //return Artist list (ObservableList of artists)
        return artistsList;
    }

    public static void insertArtist (String name, String surname, String phone,String email, String gender, Date dob , String type) throws SQLException, ClassNotFoundException {
        //Declare a Update statement
        String updateStmt = "INSERT INTO artist(artistName, artistSurname, artistPhoneNumber, artistEmail, gender, dateOfBirth,typeOfArtist) VALUES('"+name+"', '"+surname+"','"+phone+"' ,'"+email+"' , '"+gender+"', '"+dob+"','"+type+"')";


        //Execute Update operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while Update Operation: " + e);
            throw e;
        }
    }

    public static void deleteArtistByID (String artID) throws SQLException, ClassNotFoundException {
        //Declare a Update statement
        String updateStmt = "DELETE FROM artist WHERE artistId = "+artID;
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
