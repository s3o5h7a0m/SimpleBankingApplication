/*===================================================================================================
The Main is the starting class of the program as it contains the main method which is responsible
for calling the primary stage of this program.
===================================================================================================*/

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        Image primaryIcon = new Image("toolbarButtonGraphics/development/Host24.gif");
        primaryStage.getIcons().add(primaryIcon);

        primaryStage.setTitle("Soham's Bank: Main Menu");
        primaryStage.setMaximized(true);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}