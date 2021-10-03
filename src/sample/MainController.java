/*===================================================================================================
This is the controller class for mainWindow.fxml file.
===================================================================================================*/

package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    //Buttons present on the Main Menu
    @FXML
    private Button mainManagerButton, mainUserButton, mainQuitButton;

    private static List<User> userList = new ArrayList<>();

    public void initialize() {
        User user1 = new User("Dennis Ritchie", "A8977657689", "zatchbell", 2003020);
        User user2 = new User("Rahul Pawar", "U9090121443", "iamrahul", 1566880);
        User user3 = new User("James Gosling", "A129663044", "java633", 730000);
        User user4 = new User("TestUser", "s", "asd", 71890);

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
    }

    //=====================Enlarges the buttons on Main Menu when mouse is entered==========================
    @FXML
    public void onMouseEnteredForButtons(MouseEvent mouseEvent) {
        if(mouseEvent.getSource().equals(mainManagerButton)) {
            mainManagerButton.setScaleX(1.1);
            mainManagerButton.setScaleY(1.1);
        } else if(mouseEvent.getSource().equals(mainUserButton)) {
            mainUserButton.setScaleX(1.1);
            mainUserButton.setScaleY(1.1);
        } else if(mouseEvent.getSource().equals(mainQuitButton)) {
            mainQuitButton.setScaleX(1.1);
            mainQuitButton.setScaleY(1.1);
        }
    }

    //================Sets the buttons on Main Menu to default size when mouse is exited=====================
    @FXML
    public void onMouseExitedForButtons(MouseEvent mouseEvent) {
        if(mouseEvent.getSource().equals(mainManagerButton)) {
            mainManagerButton.setScaleX(1);
            mainManagerButton.setScaleY(1);
        } else if(mouseEvent.getSource().equals(mainUserButton)) {
            mainUserButton.setScaleX(1);
            mainUserButton.setScaleY(1);
        } else if(mouseEvent.getSource().equals(mainQuitButton)) {
            mainQuitButton.setScaleX(1);
            mainQuitButton.setScaleY(1);
        }
    }

    //=========================Opens the User menu when mainUserButton is pressed==============================
    @FXML
    public void openUserMenu() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("userLogin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            Stage userStage = new Stage();
            userStage.setMaximized(true);
            Image primaryIcon = new Image("toolbarButtonGraphics/development/Host24.gif");
            userStage.getIcons().add(primaryIcon);
            userStage.setTitle("Soham's Bank: User menu");
            userStage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            userStage.initModality(Modality.APPLICATION_MODAL);//It is not possible to interact with main menu
            userStage.showAndWait();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //======================Opens the Manager menu when mainManagerButton is pressed==========================
    @FXML
    public void openManagerMenu() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("managerLogin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            Stage userStage = new Stage();
            userStage.setMaximized(true);
            Image primaryIcon = new Image("toolbarButtonGraphics/development/Host24.gif");
            userStage.getIcons().add(primaryIcon);
            userStage.setTitle("Soham's Bank: Manager menu");
            userStage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            userStage.initModality(Modality.APPLICATION_MODAL);
            userStage.showAndWait();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //======================The primaryStage is closed when mainQuitButton is pressed==========================
    @FXML
    public void handleExitButtonAction() {
        Stage stage = (Stage) mainQuitButton.getScene().getWindow();
        stage.close();
    }

    public static List<User> getUserList() {
        return userList;
    }
}