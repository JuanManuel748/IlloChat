package com.github.JuanManuel.view;

import com.github.JuanManuel.App;
import com.github.JuanManuel.model.entity.User;
import com.github.JuanManuel.model.entity.UserList_Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChatRoomController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<User> tableUsers;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private Button nameButton;
    @FXML
    private Button dateButton;

    @Override
    public void onOpen(Object input) throws Exception {
        // Lógica para abrir la vista
        printAllUsers();
    }

    @Override
    public void onClose(Object output) {
        // Lógica para cerrar la vista
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Lógica de inicialización
        nameButton.setOnAction(this::sortByName);
        dateButton.setOnAction(this::sortByDate);
    }

    @FXML
    private void goToChat(ActionEvent event) {
        try {
            App.currentController.changeScene(Scenes.CHAT, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void sortByName(ActionEvent event) {
        // Función para ordenar la lista de usuarios por nombre
        List<User> userList = UserList_Singleton.getInstance().getUsers();
        userList.sort((u1, u2) -> u1.getName().compareToIgnoreCase(u2.getName()));
        tableUsers.getItems().setAll(userList);
    }

    @FXML
    private void sortByDate(ActionEvent event) {
        // Función para ordenar la lista de usuarios por la fecha del último mensaje
        List<User> userList = UserList_Singleton.getInstance().getUsers();
        //userList.sort((u1, u2) -> u1.getLastMessageDate().compareTo(u2.getLastMessageDate()));
        tableUsers.getItems().setAll(userList);
    }

    private void printAllUsers() {
        List<User> userList = UserList_Singleton.getInstance().getUsers();
        tableUsers.getItems().setAll(userList);
    }
}