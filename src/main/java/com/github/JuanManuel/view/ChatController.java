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
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
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
    private static ArrayList<Message> ListFilt = new ArrayList<>();
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
        XMLManager.writeXML(messageList, WelcomeController.messageXML);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
            if ((message.getSender().Equals(currentUser) && message.getReceiver().Equals(selectedContact)) ||
                (message.getSender().Equals(selectedContact) && message.getReceiver().Equals(currentUser))) {
                    ListFilt.add(message);
                    addMessageToContainer(message);
            }
        }
    }



    private void addMessageToContainer(Message message) {
        //====================================================
        // HACER QUE PEGUE UN INTRO CUANDO SEA MUY LARGO
        //====================================================
        Label messageLabel = new Label(message.toString());
        VBox messageBox = new VBox(messageLabel);
        messageContainer.getStyleClass().add("message-container");
        if (message.getSender().Equals(currentUser)) {
            messageBox.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
            messageBox.setMinHeight(Region.USE_PREF_SIZE);
            messageLabel.setStyle("-fx-background-color: #228be6; -fx-padding: 10; -fx-background-radius: 5; -fx-border-radius: 5; -fx-margin: 5px 5px 5px 5px; -fx-text-fill: white;");
        } else {
            messageBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
            messageBox.setMinHeight(Region.USE_PREF_SIZE);
            messageLabel.setStyle("-fx-background-color: #232d36; -fx-padding: 10; -fx-background-radius: 5; -fx-border-radius: 5; -fx-margin: 5px 5px 5px 5px; -fx-text-fill: white;");

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


        @FXML
    public void exportToCSV(ActionEvent event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("messages.csv"))) {
            for (Message m : ListFilt) {

                writer.write(m.toCSV());
            }
            showAlert(Alert.AlertType.INFORMATION, "Exportación exitosa", "La conversación ha sido exportada a messages.csv");
        } catch (IOException IOe) {
            showAlert(Alert.AlertType.ERROR, "ERROR AL EXPORTAR", "Se ha detenido la exportación de la conversación");
        }
    }


}