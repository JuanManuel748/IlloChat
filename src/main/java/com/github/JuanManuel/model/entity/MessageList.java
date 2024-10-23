package com.github.JuanManuel.model.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
public class MessageList {
    private List<Message> messages;

    public MessageList() {
        MessageList_Singleton msgLS_ST = MessageList_Singleton.getInstance();
        this.messages = msgLS_ST.getMessages();
    }

    @XmlElement
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }
}