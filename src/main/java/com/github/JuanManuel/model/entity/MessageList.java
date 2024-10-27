package com.github.JuanManuel.model.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
public class MessageList {
    private List<Message> messages;

    public MessageList() {
        // Constructor que inicializa la lista de mensajes usando el singleton
        MessageList_Singleton msgLS_ST = MessageList_Singleton.getInstance();
        this.messages = msgLS_ST.getMessages();
    }

    public MessageList(List<Message> messages) {
        // Constructor que inicializa la lista de mensajes con una lista proporcionada
        this.messages = messages;
    }

    @XmlElement(name = "message")
    public List<Message> getMessages() {
        // Devuelve la lista de mensajes
        return messages;
    }

    public void setMessages(List<Message> messages) {
        // Establece la lista de mensajes
        this.messages = messages;
    }

    public void addMessage(Message message) {
        // Añade un mensaje a la lista
        this.messages.add(message);
    }

    @Override
    public String toString() {
        // Representa la lista de mensajes como una cadena
        return "MessageList{" +
                "msgList=" + this.messages +
                '}';
    }
}
