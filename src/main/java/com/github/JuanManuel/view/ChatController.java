package com.github.JuanManuel.view;

import com.github.JuanManuel.App;
import com.github.JuanManuel.model.entity.Message;
import com.github.JuanManuel.model.entity.MessageList;
import com.github.JuanManuel.model.entity.User;
import com.github.JuanManuel.model.utils.XMLManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.io.*;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class ChatController extends Controller implements Initializable {

    @FXML
    private Button exitButton;
    @FXML
    private Button resumeButton;
    @FXML
    private Button downLoadButton;
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
        for (Message me : messageList.getMessages()) {
            if ((me.getSender().Equals(currentUser) && me.getReceiver().Equals(selectedContact)) ||
                    (me.getSender().Equals(selectedContact) && me.getReceiver().Equals(currentUser))) {
                ListFilt.add(me);
            }
        }
        try (OutputStream os = new FileOutputStream("messages.csv")) {
            for (Message m : ListFilt) {

                os.write(m.toCSV().getBytes());
            }
            showAlert(Alert.AlertType.INFORMATION, "Exportación exitosa", "La conversación ha sido exportada a messages.csv");
        } catch (IOException IOe) {
            showAlert(Alert.AlertType.ERROR, "ERROR AL EXPORTAR", "Se ha detenido la exportación de la conversación");
        }
    }


    @FXML
    public void resumeConver(ActionEvent event) {
        int userMessageCount = 0;
        int contactMessageCount = 0;
        Map<String, Integer> wordFrequency = new HashMap<>();
        String longestWord = "";
        Map<Integer, Integer> hourFrequency = new HashMap<>();

        for (Message message : messageList.getMessages()) {
            if (message.getSender().Equals(currentUser) && message.getReceiver().Equals(selectedContact)) {
                userMessageCount++;
            } else if (message.getSender().Equals(selectedContact) && message.getReceiver().Equals(currentUser)) {
                contactMessageCount++;
            }

            String[] words = message.getContent().split("\\s+");
            if ((message.getSender().Equals(currentUser) && message.getReceiver().Equals(selectedContact)) ||
                    (message.getSender().Equals(selectedContact) && message.getReceiver().Equals(currentUser))) {
                for (String word : words) {
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                    if (word.length() > longestWord.length()) {
                        longestWord = word;
                    }
                }
            }

            int hour = LocalTime.parse(message.getTime(), DateTimeFormatter.ofPattern("HH:mm:ss")).getHour();
            hourFrequency.put(hour, hourFrequency.getOrDefault(hour, 0) + 1);
        }

        String mostCommonWord = wordFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");


        String summary = String.format("Mensajes enviados: %d\nMensajes recibidos: %d\nPalabra más común: %s\nPalabra más larga: %s",
                userMessageCount, contactMessageCount, mostCommonWord, longestWord);

        try (OutputStream os = new FileOutputStream("summary.txt")) {
            os.write(summary.getBytes());
            showAlert(Alert.AlertType.INFORMATION, "Exportación exitosa", "El resumen ha sido exportado a summary.txt");
        } catch (IOException IOe) {
            showAlert(Alert.AlertType.ERROR, "ERROR AL EXPORTAR", "Se ha detenido la exportación del resumen");
        }

        showSummaryModal(summary);
    }

    private void showSummaryModal(String summary) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resumen de la conversación");
        alert.setHeaderText(null);
        alert.setContentText(summary);
        alert.showAndWait();
    }

}


