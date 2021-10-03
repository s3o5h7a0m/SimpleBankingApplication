package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class createUserFromManager {
    private List<User> userList = MainController.getUserList();
    ManagerOperations managerOperations = new ManagerOperations();
    private ListView<String> userListView = managerOperations.getUserListView();

    @FXML
    DialogPane createUserDialogPane;

    @FXML
    private TextField nameTf, accountTf, passwordTf;

    @FXML
    public void createUser() {
        User newUser = new User(nameTf.getText().trim(), accountTf.getText().trim(), passwordTf.getText().trim(),
                500);
        userList.add(newUser);
    }
}