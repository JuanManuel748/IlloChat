package com.github.JuanManuel.view;

import com.github.JuanManuel.App;
import com.github.JuanManuel.model.entity.User;
import com.github.JuanManuel.model.entity.UserList;
import com.github.JuanManuel.model.utils.HashPass;
import com.github.JuanManuel.model.utils.XMLManager;
import com.github.JuanManuel.view.WelcomeController;
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

public class RegisterController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    private static UserList userContainer = new UserList();

    @Override
    public void onOpen(Object input) throws Exception {
        // Método que se llama al abrir la vista
    }

    @Override
    public void onClose(Object output) {
        // Método que se llama al cerrar la vista
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Método inicializador
    }

    private boolean validateEmail(String email) {
        // Validar que cumple el formato de email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        // Muestra una alerta en pantalla
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void onRegister(ActionEvent actionEvent) {
        // Maneja el proceso de registro de un nuevo usuario
        try {
            String name = nameField.getText().trim();
            String surname = surnameField.getText().trim();
            String email = emailField.getText().trim();
            email = email.toLowerCase();
            String password = passwordField.getText().trim();
            String hashedPassword = HashPass.hashPassword(password);

            // Verifica si algún campo está vacío
            if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || hashedPassword.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Campos Vacíos", "Por favor, complete todos los campos.");
            } else {
                // Valida el formato del correo electrónico
                if (!validateEmail(email)) {
                    showAlert(Alert.AlertType.ERROR, "Error en formato del correo electronico", "El formato del correo electrónico no es válido.");
                } else if (existsEmail(email)){
                    showAlert(Alert.AlertType.ERROR, "Error correo ya existe", "El correo ya existe en el archivo xml.");
                } else {
                    // Crea un objeto User y lo guarda en el XML
                    User userTemp = new User(name, surname, email, hashedPassword);
                    userContainer.add(userTemp);
                    XMLManager.writeXML(userContainer, WelcomeController.userXML);

                    // Muestra mensaje de éxito y navega a la página de inicio de sesión
                    showAlert(Alert.AlertType.INFORMATION, "Cliente Registrado", "El cliente se ha registrado correctamente.");
                    App.currentController.changeScene(Scenes.LOGIN, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error al Guardar", "Hubo un error al intentar guardar el cliente.");
        }
    }

    public void goToLogin(ActionEvent actionEvent) {
        // Navega a la vista de inicio de sesión
        try {
            App.currentController.changeScene(Scenes.LOGIN, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsEmail(String email) {
        // Verifica si el correo existe en la lista de usuarios
        List<User> users = userContainer.getUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println("Ya existe el correo");
                return true;
            }
        }
        return false;
    }
}
