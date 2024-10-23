package com.github.JuanManuel.view;

import com.github.JuanManuel.App;
import com.github.JuanManuel.model.entity.User;
import com.github.JuanManuel.model.entity.UserList_Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChatRoomController extends Controller implements Initializable {

    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    public void goToChat(ActionEvent actionEvent) {
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


    private void SortByName () {
        // Función para ordenar la lista de usuarios por el nombre
    }

    private void SortByDate () {
        // Funcion para ordenar la lista de usuarios por la fecha del último mensaje
    }

    private void printAllUsers() {
        List <User> userList = UserList_Singleton.getInstance().getUsers();

    }

}
