package com.github.JuanManuel.view;

import com.github.JuanManuel.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button adminWayButton;

    public static final String userXML = "src/main/resources/XML/UserData.xml";
    public static final String messageXML = "src/main/resources/XML/Messages.xml";

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

    public void setAdminWayButton() throws Exception {
        // Cambia a la vista de inicio de sesión
        App.currentController.changeScene(Scenes.LOGIN, null);
    }
}
