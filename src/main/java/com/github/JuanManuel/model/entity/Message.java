package com.github.JuanManuel.model.entity;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
    @XmlElement
    private User sender;
    @XmlElement
    private User recipient;
    @XmlElement
    private String content;
    @XmlElement
    private int messageID;
    @XmlElement
    private LocalDate date;   // Date attribute
    @XmlElement
    private LocalTime time;   // Time attribute

    // Full constructor
    public Message(User sender, User recipient, String content, int messageID) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.messageID = messageID;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    // Empty constructor
    public Message() {
    }

    // GETTERS
    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    public int getMessageID() {
        return messageID;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    // SETTERS
    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    // toString method
    @Override
    public String toString() {
        String recipientName = (recipient != null) ? recipient.getName() : "Unknown";
        return String.format("From: %s To: %s\n%s\n%s %s", sender.getName(), recipientName, content, date, time);
    }
}