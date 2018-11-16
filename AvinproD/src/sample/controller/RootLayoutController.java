package sample.controller;

/**
 * Created by Carlito on 12/04/2017.
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class RootLayoutController {

    //Reference to the main application
    private Main main;

    //Is called by the main application to give a reference back to itself.
    public void setMain (Main main) {
        this.main = main;
    }

    @FXML
    private MenuBar menuBar ;

    //Exit the program
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    //Help Menu button behavior
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information");
        alert.setHeaderText("This is a university prototype!");
        alert.setContentText("You can search, delete, update, insert a new artists with this program.");
        alert.show();
    }

    public void handleArtist(ActionEvent actionEvent)throws IOException {
        main.showArtistView();

    }

    public void handleSong(ActionEvent actionEvent)throws  IOException{
        main.showSongView();
        
    }
}