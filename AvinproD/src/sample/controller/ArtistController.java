package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.model.Artist;
import sample.model.ArtistDao;

import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

/**
 * Created by Carlito on 12/04/2017.
 */

public class ArtistController {

    @FXML
    private TextField artIdText;
    @FXML
    private TextField surnameText;
    @FXML
    private ChoiceBox genderChoice;
    @FXML
    private ChoiceBox typeChoice;
    @FXML
    private TextField emailText;
    @FXML
    private TextField phoneText;
    @FXML
    private DatePicker dob;
    @FXML
    private TextField nameText;
    @FXML
    private ChoiceBox gender;
    @FXML
    private ChoiceBox type;
    @FXML
    private TextField idToDelete;
    @FXML
    private TextArea resultArea;
    @FXML
    private TableView artistTable;
    @FXML
    private TableColumn<Artist, Integer>  artistIdColumn;
    @FXML
    private TableColumn<Artist, String>  artistNameColumn;
    @FXML
    private TableColumn<Artist, String> artistLastNameColumn;
    @FXML
    private TableColumn<Artist, String> artistEmailColumn;
    @FXML
    private TableColumn<Artist, String> artistPhoneNumberColumn;
    @FXML
    private TableColumn<Artist, String> artistGenderColumn;
    @FXML
    private TableColumn<Artist, Date> artistDOBDateColumn;


    ObservableList<String> artistTypeList = FXCollections.observableArrayList("National", "International");

    ObservableList<String> artistGenderList = FXCollections.observableArrayList("Male", "Female");

    ObservableList<String>  genderFilter = FXCollections.observableArrayList("None", "Male", "Female");

    ObservableList<String> typeFilter = FXCollections.observableArrayList("None", "National", "International");

    //Search an Artist
    @FXML
    private void searchArtist (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Artist information
            Artist art = ArtistDao.searchArtist(artIdText.getText());
            //Populate Artist on TableView and Display on TextArea
            populateAndShowArtist(art);
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error occurred while getting Artist information from DB.\n" + e);
            throw e;
        }
    }

    //Filter Artist Table

    @FXML
    private void filterTable (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Artist information
            if (genderChoice.getValue().toString().equalsIgnoreCase("none") && typeChoice.getValue().toString().equalsIgnoreCase("none")){
                //Get all Artist information
                ObservableList<Artist> artistData = ArtistDao.searchArtists();
                //Populate Artist on TableView
                populateAllArtists(artistData);
                resultArea.setText("All artist are being displayed");
            }
            if (!genderChoice.getValue().toString().equalsIgnoreCase("none")){
                typeChoice.getSelectionModel().selectFirst();
                ObservableList<Artist> art = ArtistDao.searchArtistByGender(genderChoice.getValue().toString());
                //Populate Artist on TableView and Display on TextArea
                populateAllArtists(art);
                resultArea.setText("All artist are that are of gender "+genderChoice.getValue().toString()+ " are being displayed");
                genderChoice.getSelectionModel().selectFirst();
            }
            if(!typeChoice.getValue().toString().equalsIgnoreCase("none")){
                genderChoice.getSelectionModel().selectFirst();
                ObservableList<Artist> art = ArtistDao.searchArtistByType(typeChoice.getValue().toString());
                //Populate Artist on TableView and Display on TextArea
                populateAllArtists(art);
                resultArea.setText("All artist are that are of type "+typeChoice.getValue().toString()+ " are being displayed");
                typeChoice.getSelectionModel().selectFirst();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error occurred while getting Artist information from DB.\n" + e);
            throw e;
        }
    }

    //Search all Artists
    @FXML
    private void searchArtists(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Artist information
            ObservableList<Artist> artistData = ArtistDao.searchArtists();
            //Populate Artist on TableView
            populateAllArtists(artistData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting Artist information from DB.\n" + e);
            throw e;
        }
    }

    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
        /*
        The setCellValueFactory(...) that we set on the table columns are used to determine
        which field inside the Artist objects should be used for the particular column.

        */
        genderChoice.setItems(genderFilter);
        typeChoice.setItems(typeFilter);
        gender.setItems(artistGenderList);
        type.setItems(artistTypeList);
        genderChoice.getSelectionModel().selectFirst();
        typeChoice.getSelectionModel().selectFirst();
        artistIdColumn.setCellValueFactory(cellData -> cellData.getValue().artistIDProperty().asObject());
        artistNameColumn.setCellValueFactory(cellData -> cellData.getValue().artistNameProperty());
        artistLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().artistSurnameProperty());
        artistEmailColumn.setCellValueFactory(cellData -> cellData.getValue().artistEmailProperty());
        artistPhoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().artistPhoneNumberProperty());
        artistGenderColumn.setCellValueFactory(cellData -> cellData.getValue().artistGenderProperty());
        artistDOBDateColumn.setCellValueFactory(cellData -> cellData.getValue().artistDOBProperty());

    }

    //Populate Artist
    @FXML
    private void populateArtist (Artist art) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Artist> artistData = FXCollections.observableArrayList();
        //Add artist to the ObservableList
        artistData.add(art);
        //Set items to the artistTable
        artistTable.setItems(artistData);
    }

    //Set Artist information to Text Area
    @FXML
    private void setArtistInfoToTextArea ( Artist artist) {
        resultArea.setText("First Name: " + artist.getArtistName() + "\n" +
                "Surname: " + artist.getArtistSurname());
    }

    //Populate Artist for TableView and Display Artist on TextArea
    @FXML
    private void populateAndShowArtist(Artist artist) throws ClassNotFoundException {
        if (artist != null) {
            populateArtist(artist);
            setArtistInfoToTextArea(artist);
        } else {
            resultArea.setText("This Artist does not exist!\n");
        }
    }

    //Populate Artist for TableView
    @FXML
    private void populateAllArtists (ObservableList<Artist> artistData) throws ClassNotFoundException {
        //Set items to the artistTable
        artistTable.setItems(artistData);
    }


    //Insert an artist to the DB
    @FXML
    private void createArtistRecord (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            java.sql.Date sqlDate = java.sql.Date.valueOf(dob.getValue());
            System.out.println(nameText.getText());
            System.out.println(gender.getValue().toString());
            ArtistDao.insertArtist(nameText.getText(),surnameText.getText(), phoneText.getText(),emailText.getText(), gender.getValue().toString(), sqlDate,type.getValue().toString());
            resultArea.setText("Artist inserted! \n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while inserting artist " + e);
            throw e;
        }
    }

    //Delete an employee with a given employee Id from DB
    @FXML
    private void deleteArtist (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            try {
                ArtistDao.deleteArtistByID(idToDelete.getText());
                resultArea.setText("Artist deleted! Artist id: " + idToDelete.getText() + "\n");
            } catch (SQLException e){
                resultArea.setText("Problem occurred while deleting artist " + e);
                throw e;
            }
    }

}
