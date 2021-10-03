package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.List;

public class DeleteUserFromManager {
    @FXML
    private TextField deleteAccNumTf;

    @FXML
    public void deleteUser(List<User> userList) {
        String accountNum = deleteAccNumTf.getText().trim();
        userList.removeIf(user -> user.getAccountNumber().equals(accountNum));
    }
}
