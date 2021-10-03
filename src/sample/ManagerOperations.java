/*===================================================================================================
This is the controller class for managerWindow.fxml file.
It handles all the operations for Manager Window.
===================================================================================================*/
package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

public class ManagerOperations {
    private final ManagerController managerController = new ManagerController();
    private final String managerName = managerController.getManagerName();
    private List<User> userList = MainController.getUserList();

    @FXML
    private Label activeUsersLabel, welcomeLabel;

    @FXML
    private ListView<String> userListView;

    @FXML
    private Tooltip helpToolTip;

    @FXML
    private Button exitButton, btnEmpTable, btnRemoveUser, btnCreateUser;

    public void initialize() {
        StringTokenizer firstName = new StringTokenizer(managerName, " ", false);
        welcomeLabel.setText("Welcome " + firstName.nextToken() + "!");
        activeUsersLabel.setText("     Active Users(" + userList.size() +")     ");

        List<String> tempUserList = new ArrayList<>();
        for(User user : userList) {
            tempUserList.add(user.getName() + "\nAccount number: " + user.getAccountNumber());
        }
        userListView.getItems().addAll(tempUserList);
        userListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        helpToolTip.setText("This is the Manager's Menu.\n\nTo raise any issue or concerns\nplease email us at " +
                "technicalsupport@gmail.com");
        helpToolTip.setShowDelay(Duration.millis(1));
        helpToolTip.setShowDuration(Duration.seconds(10));
    }

    //=======================================Handles create user===========================================
    @FXML
    public void openCreateUserDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Soham's Bank: Add new user");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("createUserFromManager.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setHeaderText("Use this dialog to create a new user");

        dialog.getDialogPane().getButtonTypes().add(ButtonType.FINISH);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.FINISH) {
            createUserFromManager controller = fxmlLoader.getController();
            controller.createUser();
            List<String> tempUserList = new ArrayList<>();
            for(User user : userList) {
                tempUserList.add(user.getName() + "\nAccount number: " + user.getAccountNumber());
            }
            userListView.getItems().setAll(tempUserList);
            activeUsersLabel.setText("     Active Users(" + userList.size() +")     ");
        }
    }

    //=======================================Handles delete user===========================================
    @FXML
    public void openDeleteUserDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Soham's Bank: Delete user");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("deleteUserFromManager.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.FINISH);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        dialog.setHeaderText("Use this dialog to delete an user");
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.FINISH) {
            DeleteUserFromManager controller = fxmlLoader.getController();
            controller.deleteUser(userList);
            List<String> tempUserList = new ArrayList<>();
            for(User user : userList) {
                tempUserList.add(user.getName() + "\nAccount number: " + user.getAccountNumber());
            }
            userListView.getItems().setAll(tempUserList);
            activeUsersLabel.setText("     Active Users(" + userList.size() +")     ");
        }
    }

    public ListView<String> getUserListView() {
        return userListView;
    }

    //====================================Displays the employee table========================================
    @FXML
    public void openEmployeeTable() throws Throwable {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("staffInfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Employee Table");
        stage.setScene(scene);
        stage.setResizable(false);
        /*StaffInfo controller = fxmlLoader.getController();
        controller.initializeTable();*/

        stage.showAndWait();
    }

    //=====================Enlarges the buttons on managerWindow when mouse is entered==========================
    @FXML
    public void onMouseEnteredForButtons(MouseEvent mouseEvent) {
        if(mouseEvent.getSource().equals(btnCreateUser)) {
            btnCreateUser.setScaleX(1.1);
            btnCreateUser.setScaleY(1.1);
        } else if(mouseEvent.getSource().equals(btnEmpTable)) {
            btnEmpTable.setScaleX(1.1);
            btnEmpTable.setScaleY(1.1);
        } else if(mouseEvent.getSource().equals(btnRemoveUser)) {
            btnRemoveUser.setScaleX(1.1);
            btnRemoveUser.setScaleY(1.1);
        }
    }

    //================Sets the buttons on managerWindow to default size when mouse is exited=====================
    @FXML
    public void onMouseExitedForButtons(MouseEvent mouseEvent) {
        if(mouseEvent.getSource().equals(btnCreateUser)) {
            btnCreateUser.setScaleX(1);
            btnCreateUser.setScaleY(1);
        } else if(mouseEvent.getSource().equals(btnEmpTable)) {
            btnEmpTable.setScaleX(1);
            btnEmpTable.setScaleY(1);
        } else if(mouseEvent.getSource().equals(btnRemoveUser)) {
            btnRemoveUser.setScaleX(1);
            btnRemoveUser.setScaleY(1);
        }
    }

    //====================================Closes manager window========================================
    @FXML
    public void closeManagerWindow() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}