package com.github.JuanManuel.view;

import com.github.JuanManuel.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController extends Controller implements Initializable {

    @FXML
    private BorderPane borderPane;
    private Controller centerController;

    // Cambia a la escena de bienvenida al abrir la aplicación
    @Override
    public void onOpen(Object input) throws Exception {
        changeScene(Scenes.WELCOME, null);
    }

    // Método inicializador que se ejecuta al cargar el controlador
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // Aquí se puede añadir lógica de inicialización si es necesario
    }

    // Carga un archivo FXML y su controlador asociado
    public static View loadFXML(Scenes scenes) throws Exception {
        String url = scenes.getURL();
        System.out.println(url);
        FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
        Parent p = loader.load();
        Controller c = loader.getController();
        View view = new View();
        view.scene = p;
        view.controller = c;
        return view;
    }

    // Cambia la escena central del BorderPane
    public void changeScene(Scenes scene, Object data) throws Exception {
        View view = loadFXML(scene);
        borderPane.setCenter(view.scene);
        this.centerController = view.controller;
        this.centerController.onOpen(data);
    }

    // Abre una ventana modal con una nueva escena
    public void openModal(Scenes scene, String title, Controller parent, Object data) throws Exception {
        View view = loadFXML(scene);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(App.stage);
        Scene _scene = new Scene(view.scene);
        stage.setScene(_scene);
        view.controller.onOpen(parent);
        stage.showAndWait();
    }

    // Lógica para manejar el cierre de la aplicación
    @Override
    public void onClose(Object output) {
    }
}
