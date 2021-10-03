/*===================================================================================================
This is the controller class for userLogin.fxml file.
It manages actions on the user login scene and validates the user.
===================================================================================================*/

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class UserController {
    private static int currentUser;
    private static List<User> userList = MainController.getUserList();

    @FXML
    private TextField accNumTf;
    @FXML
    private PasswordField userPf;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;

    //===============================Switch scene with userWindow=======================================
    private void switchToUserWindow(ActionEvent event) throws IOException {
        Parent newPage = FXMLLoader.load(getClass().getResource("userWindow.fxml"));
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(newPage);
        ((Stage) scene.getWindow()).setMaximized(true);
    }

    //================================Handles user validation============================================
    @FXML
    public boolean doLogin(ActionEvent e) throws IOException {
        int temp = -1;
        String bankNum = accNumTf.getText();
        String password = userPf.getText();
        for(User user : userList) {
            temp++;
            if(user.getAccountNumber().equals(bankNum)) {
                if(user.getPassword().equals(password)) {
                    currentUser = temp;
                    System.out.println(currentUser);
                    switchToUserWindow(e);
                    return true;
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Invalid credentials");
                    errorAlert.setContentText("Please check your account number or password and try again!");
                    errorAlert.showAndWait();
                    return false;
                }
            }
        }
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        Stage stage = (Stage) errorAlert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("toolbarButtonGraphics/development/Host24.gif"));
        errorAlert.setHeaderText("Invalid credentials");
        errorAlert.setContentText("Please check your account number or password and try again!");
        errorAlert.showAndWait();
        return false;
    }

    //=====================Enlarges the buttons on User Menu when mouse is entered==========================
    @FXML
    public void onMouseEnteredForButtons(MouseEvent mouseEvent) {
        if(mouseEvent.getSource().equals(loginButton)) {
            loginButton.setScaleX(1.1);
            loginButton.setScaleY(1.1);
        } else if(mouseEvent.getSource().equals(cancelButton)) {
            cancelButton.setScaleX(1.1);
            cancelButton.setScaleY(1.1);
        }
    }

    //================Sets the buttons on User Menu to default size when mouse is exited=====================
    @FXML
    public void onMouseExitedForButtons(MouseEvent mouseEvent) {
        if(mouseEvent.getSource().equals(loginButton)) {
            loginButton.setScaleX(1);
            loginButton.setScaleY(1);
        } else if(mouseEvent.getSource().equals(cancelButton)) {
            cancelButton.setScaleX(1);
            cancelButton.setScaleY(1);
        }
    }

    //================================Handles closing of stage============================================
    @FXML
    public void onCancelPressed() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    //===========================================Getters===================================================
    public List<User> getUserList() {
        return userList;
    }
    public int getCurrentUser() {
        return currentUser;
    }
}