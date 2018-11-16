package sample.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.model.Song;
import sample.model.SongDao;

import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by Carlito on 10/04/2017.
 */
public class SongController {
    @FXML
    private TextField songToSearch;
    @FXML
    private TextField songNameText;
    @FXML
    private TextField albumText;
    @FXML
    private DatePicker dateCreatedText;
    @FXML
    private TextField idToDelete;
    @FXML
    private TextArea resultArea;
    @FXML
    private TableView songTable;
    @FXML
    private TableColumn<Song, Integer>  songIDColumn;
    @FXML
    private TableColumn<Song, String> songNameColumn;
    @FXML
    private TableColumn<Song, String> albumColumn;
    @FXML
    private TableColumn<Song, Date> dateCreatedColumn;

    @FXML
    private void searchSong(ActionEvent actionEvent) throws ClassNotFoundException, SQLException{
        try {
            //Get Song information
            if (songToSearch.getText().equalsIgnoreCase(" ")){
                resultArea.setText("No value to search for");
            }
            else {
                Song song = SongDao.searchSong(songToSearch.getText());
                //Populate Song on TableView and Display on TextArea
                populateAndShowSong(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error occurred while getting Song information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    private void searchAllSongs(ActionEvent actionEvent) throws ClassNotFoundException, SQLException{
        try {
            //Get all Song information
            ObservableList<Song> songData = SongDao.searchAllSongs();
            //Populate Song on TableView
            populateAllSongs(songData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting Song information from DB.\n" + e);
            throw e;
        }

    }

    @FXML
    private void initialize () {

        songIDColumn.setCellValueFactory(cellData -> cellData.getValue().songIDProperty().asObject());
        songNameColumn.setCellValueFactory(cellData -> cellData.getValue().songNameProperty());
        albumColumn.setCellValueFactory(cellData -> cellData.getValue().albumProperty());
        dateCreatedColumn.setCellValueFactory(cellData -> cellData.getValue().songDateProperty());

    }

    @FXML
    private void populateSong (Song song) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Song> songData = FXCollections.observableArrayList();
        //Add Song to the ObservableList
        songData.add(song);
        //Set items to the SongTable
        songTable.setItems(songData);
    }

    @FXML
    private void setSongInfoToTextArea ( Song song) {
        resultArea.setText("Song Name: " + song.getSongName() + "\n" +
                "Album: " + song.getAlbum());
    }

    @FXML
    private void populateAndShowSong(Song song) throws ClassNotFoundException {
        if (song != null) {
            populateSong(song);
            setSongInfoToTextArea(song);
        } else {
            resultArea.setText("This Song does not exist!\n");
        }
    }

    //Populate Song for TableView
    @FXML
    private void populateAllSongs (ObservableList<Song> songData) throws ClassNotFoundException {
        //Set items to the SongTable
        songTable.setItems(songData);
    }



    @FXML
    private void createSongRecord(ActionEvent actionEvent) throws SQLException, ClassNotFoundException{

        try {
            java.sql.Date sqlDate = java.sql.Date.valueOf(dateCreatedText.getValue());
            System.out.println(songNameText.getText());
            System.out.println(albumText.toString());
            SongDao.insertSong(songNameText.getText(),albumText.getText(), sqlDate);
            resultArea.setText("Song inserted! \n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while inserting song " + e);
            throw e;
        }

    }

    @FXML
    private void deleteSong() throws SQLException, ClassNotFoundException{
        try {
            SongDao.deleteSongByID(idToDelete.getText());
            resultArea.setText("Song deleted! Song id: " + idToDelete.getText() + "\n");
        } catch (SQLException e){
            resultArea.setText("Problem occurred while deleting artist " + e);
            throw e;
        }

    }

}
