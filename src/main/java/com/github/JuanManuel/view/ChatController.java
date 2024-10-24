package com.github.JuanManuel.view;

import com.github.JuanManuel.App;
import com.github.JuanManuel.model.entity.Message;
import com.github.JuanManuel.model.entity.MessageList;
import com.github.JuanManuel.model.entity.User;
import com.github.JuanManuel.model.utils.XMLManager;
import com.github.JuanManuel.view.LoginController;
import com.github.JuanManuel.view.WelcomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.github.JuanManuel.App.scene;

public class ChatController extends Controller implements Initializable {

    @FXML
    private Button exitButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField messageField;
    @FXML
    private VBox messageContainer;

    private static MessageList messageList = new MessageList();
    private static MessageList ListFilt = new MessageList();
    private User currentUser;
    private User selectedContact;

    @Override
    public void onOpen(Object input) throws Exception {
        // Initialize current user and selected contact
        this.currentUser = LoginController.Sender;
        if (input instanceof User) {
            this.selectedContact = (User) input;
        }
        // Load messages from XML
        messageList = XMLManager.readXML(new MessageList(), WelcomeController.messageXML);
        displayMessages();
    }

    @Override
    public void onClose(Object output) {
        // Save messages to XML when closing
        XMLManager.writeXML(messageList, WelcomeController.messageXML);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization logic if needed
    }

    @FXML
    private void handleSendMessage() {
        String content = messageField.getText();
        if (!content.isEmpty()) {
            Message message = new Message(currentUser, selectedContact, content);
            messageList.addMessage(message);
            XMLManager.writeXML(messageList, WelcomeController.messageXML);
            addMessageToContainer(message);
            messageField.clear();
        } else {
            showAlert(Alert.AlertType.ERROR, "Mensaje vacio", "Rellena el texto del mensaje.");
        }
    }

    private void displayMessages() {
        messageContainer.getChildren().clear();
        for (Message message : messageList.getMessages()) {
            if ((message.getSender().equals(currentUser) && message.getReceiver().equals(selectedContact)) || (message.getSender().equals(selectedContact) && message.getReceiver().equals(currentUser))) {
                addMessageToContainer(message);
            }

        }
    }



    private void addMessageToContainer(Message message) {
        //====================================================
        // HACER QUE PEGUE UN INTRO CUANDO SEA MUY LARGO
        //====================================================
        ListFilt.addMessage(message);
        Label messageLabel = new Label(message.toString());
        VBox messageBox = new VBox(messageLabel);
        messageContainer.getStyleClass().add("message-container");
        if (message.getSender().equals(currentUser)) {
            messageBox.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
            messageBox.setMinHeight(Region.USE_PREF_SIZE);
            messageLabel.setStyle("-fx-background-color: #228be6; -fx-padding: 10; -fx-background-radius: 5; -fx-border-radius: 5; -fx-margin: 5px 5px 5px 5px;");
        } else {
            messageBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
            messageBox.setMinHeight(Region.USE_PREF_SIZE);
            messageLabel.setStyle("-fx-background-color: #1e90ff; -fx-padding: 10; -fx-background-radius: 5; -fx-border-radius: 5; -fx-margin: 5px 5px 5px 5px;");

        }
        messageContainer.getChildren().add(messageBox);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void goToChatRoom(ActionEvent event) {
        try {
            App.currentController.changeScene(Scenes.CHATROOM, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
    @FXML
    public void exportToCSV(ActionEvent event) {
        try {
            for (Message m: ListFilt) {
                //==========================
                // TERMINAR
            }

        }catch (IOException IOe) {
            showAlert(Alert.AlertType.INFORMATION, "ERROR AL EXPORTAR", "Se ha detenido la exportación de la conservación");
        }
    }
    */

}