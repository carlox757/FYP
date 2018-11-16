package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controller.RootLayoutController;

import java.io.IOException;

public class Main extends Application {

    //This is our PrimaryStage (It contains everything)
    private Stage primaryStage;

    //This is the BorderPane of RootLayout
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        //1) Declare a primary stage (Everything will be on this stage)
        this.primaryStage = primaryStage;

        //Optional: Set a title for primary stage
        this.primaryStage.setTitle("Avinpro Manager");

        //2) Initialize RootLayout
        initRootLayout();

        //3) Display the artist View
        showArtistView();

    }

    //Initializes the root layout.
    public void initRootLayout() {
        try {
            //First, load root layout from RootLayout.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            //Second, show the scene containing the root layout.
            Scene scene = new Scene(rootLayout); //We are sending rootLayout to the Scene.
            primaryStage.setScene(scene); //Set the scene in primary stage.

            //Give the controller access to the main.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);

            //Third, show the primary stage
            primaryStage.show(); //Display the primary stage
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Shows the artists operations view inside the root layout.
    public void showArtistView() {
        try {
            //First, load artist view  from artistView.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ArtistView.fxml"));
            AnchorPane artistOperations = (AnchorPane) loader.load();

            // Set artist Operations view into the center of root layout.
            rootLayout.setCenter(artistOperations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSongView() {
        try {
            //First, load song view  from songView.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/songView.fxml"));
            AnchorPane artistOperations = (AnchorPane) loader.load();

            // Set artist Operations view into the center of root layout.
            rootLayout.setCenter(artistOperations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
