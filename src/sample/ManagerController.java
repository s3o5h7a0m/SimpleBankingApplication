/*===================================================================================================
This is the controller class for managerLogin.fxml file.
It handles all the operations for Manager Login Window.
===================================================================================================*/

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.StringTokenizer;

public class ManagerController {
    private final String managerName = "Soham Pande";
    private final String password = "admin123";

    @FXML
    private PasswordField passwordTf;

    @FXML
    private Button cancelButton, loginButton;

    @FXML
    private Label welcomeLabel;

    public void initialize() {
        StringTokenizer firstName = new StringTokenizer(managerName, " ", false);
        welcomeLabel.setText("Welcome " + firstName.nextToken() + "!");
    }

    //========================This window appears after password validation=================================
    private void switchToManagerWindow(ActionEvent event) throws IOException {
        Parent newPage = FXMLLoader.load(getClass().getResource("managerWindow.fxml"));
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(newPage);
        ((Stage) scene.getWindow()).setMaximized(true);
    }

    //====================================Handles password validation========================================
    public void doManagerLogin(ActionEvent e) throws IOException {
        String password = passwordTf.getText();
        if(password.equals(this.password)) {
            switchToManagerWindow(e);
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Invalid credentials");
            errorAlert.setContentText("Please check your password and try again!");
            Stage stage = (Stage) errorAlert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("toolbarButtonGraphics/development/Host24.gif"));
            errorAlert.showAndWait();
        }
    }

    //=======================The managerLogin is closed when cancelButton is pressed===========================
    public void closeManagerLogin() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    //=====================Enlarges the buttons on managerLogin when mouse is entered==========================
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

    //================Sets the buttons on managerLogin to default size when mouse is exited=====================
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

    //=======================================Getter for manager name==========================================
    public String getManagerName() {
        return managerName;
    }
}
