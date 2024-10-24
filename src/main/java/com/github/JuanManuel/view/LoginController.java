package com.github.JuanManuel.view;

import com.github.JuanManuel.App;
import com.github.JuanManuel.model.entity.User;
import com.github.JuanManuel.model.entity.UserList;
import com.github.JuanManuel.model.utils.HashPass;
import com.github.JuanManuel.model.utils.XMLManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button loginButton;
    @FXML
    private Button goToRegisterButton;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    private static UserList userContainer = new UserList();

    protected static User Sender = new User();

    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void onLogin(ActionEvent actionEvent) {
        try {
            String email = emailField.getText().trim();
            email = email.toLowerCase();
            String password = passwordField.getText().trim();
            String hashedPassword = HashPass.hashPassword(password);

            // Check if any field is empty
            if (email.isEmpty() || hashedPassword.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Campos Vacíos", "Por favor, complete todos los campos.");
            } else {
                if (!existsEmail(email)) {
                    showAlert(Alert.AlertType.ERROR, "Error correo no existe", "El correo no existe en el archivo xml.");
                } else {
                    boolean loged = false;
                    for(User u: userContainer.getUsers()) {
                        if (u.getEmail().equals(email)) {
                            if (u.getPassword().equals(hashedPassword)) {
                                Sender = u;
                                loged = true;
                                break;
                            }
                        }
                    }
                    // Show success message and navigate to main page
                    if (loged) {
                        showAlert(Alert.AlertType.INFORMATION, "Cliente Logueado", "El cliente se ha loqueado correctamente.");
                        App.currentController.changeScene(Scenes.CHATROOM, null);
                    } else {
                        showAlert(Alert.AlertType.INFORMATION, "Cliente no encontrado", "El email o la contraseña no corresponden con los guardados en nuestros archivos.");
                    }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error al Guardar", "Hubo un error al intentar guardar el cliente.");
        }
    }

    public boolean existsEmail (String email) {

        List<User> users = userContainer.getUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println("Correo encontrado");
                return true;
            }
        }
        return false;

    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public void goToRegister(ActionEvent actionEvent) {
        try {
            App.currentController.changeScene(Scenes.REGISTER, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
