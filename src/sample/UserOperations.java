/*===================================================================================================
This is the controller class for userWindow.fxml file.
It handles all the operations for User Window.
===================================================================================================*/

package sample;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.List;
import java.util.StringTokenizer;

public class UserOperations {
    private UserController uc = new UserController();
    private List<User> userList = uc.getUserList();
    private int currentUser = uc.getCurrentUser();

    @FXML
    private String currentUserName = getFirstNameAndGreet(userList.get(currentUser).getName());

    @FXML
    private TextField balanceTf, withdrawTf, depositTf;

    @FXML
    private Label welcomeLabel, errorLabelForWithdraw;

    @FXML
    private Button balanceButton, withdrawButton, depositButton, exitButton;

    @FXML
    public void initialize() {
        welcomeLabel.setText(currentUserName);
    }

    //=========================Returns name of current user along with greetings==============================
    private String getFirstNameAndGreet(String fullName) {
        StringTokenizer firstName = new StringTokenizer(fullName, " ", false);
        return "Welcome " + firstName.nextToken() + "!";
    }

    //==============================Method to check balance of current user===================================
    @FXML
    public void checkBalance() {
        balanceTf.setText("Rs." + userList.get(currentUser).getBalance());
    }

    //=======================Method to perform withdraw operation for current user============================
    @FXML
    public void withdraw() {
        double withdrawAmt = Double.parseDouble(withdrawTf.getText());
        if(withdrawAmt > userList.get(currentUser).getBalance()) {
            errorLabelForWithdraw.setText(" \u274C Insufficient balance");
            PauseTransition visiblePause = new PauseTransition(
                    Duration.seconds(3)
            );
            visiblePause.setOnFinished(
                    event -> errorLabelForWithdraw.setText("")
            );
            visiblePause.play();
        } else {
            userList.get(currentUser).setBalance(userList.get(currentUser).getBalance() - withdrawAmt);
        }
    }

    //=======================Method to perform withdraw operation for current user============================
    @FXML
    public void deposit() {
        double depositAmt = Double.parseDouble(depositTf.getText());
        userList.get(currentUser).setBalance(userList.get(currentUser).getBalance() + depositAmt);
    }

    //======================Enlarges the buttons on User Menu when mouse is entered===========================
    @FXML
    public void onMouseEnteredForButtons(MouseEvent mouseEvent) {
        if(mouseEvent.getSource().equals(balanceButton)) {
            balanceButton.setScaleX(1.1);
            balanceButton.setScaleY(1.1);
        } else if(mouseEvent.getSource().equals(withdrawButton)) {
            withdrawButton.setScaleX(1.1);
            withdrawButton.setScaleY(1.1);
        } else if(mouseEvent.getSource().equals(depositButton)) {
            depositButton.setScaleX(1.1);
            depositButton.setScaleY(1.1);
        } else if(mouseEvent.getSource().equals(exitButton)) {
            exitButton.setScaleX(1.1);
            exitButton.setScaleY(1.1);
        }
    }

    //=================Sets the buttons on User Menu to default size when mouse is exited======================
    @FXML
    public void onMouseExitedForButtons(MouseEvent mouseEvent) {
        if(mouseEvent.getSource().equals(balanceButton)) {
            balanceButton.setScaleX(1);
            balanceButton.setScaleY(1);
        } else if(mouseEvent.getSource().equals(withdrawButton)) {
            withdrawButton.setScaleX(1);
            withdrawButton.setScaleY(1);
        } else if(mouseEvent.getSource().equals(depositButton)) {
            depositButton.setScaleX(1);
            depositButton.setScaleY(1);
        } else if(mouseEvent.getSource().equals(exitButton)) {
            exitButton.setScaleX(1);
            exitButton.setScaleY(1);
        }

    }

    //=======================The user window is closed when exitButton is pressed===========================
    public void closeUserWindow() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
