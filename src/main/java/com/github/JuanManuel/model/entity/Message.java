package com.github.JuanManuel.model.entity;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
    @XmlElement
    private User sender;
    @XmlElement
    private User receiver;
    @XmlElement
    private String content;
    @XmlElement
    private int messageID;
    @XmlElement
    private String date;
    @XmlElement
    private String time;
    private static final DateTimeFormatter Dformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter Tformatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Message(User sender, User recipient, String content) {
        // Constructor para crear un mensaje con los detalles del remitente, destinatario y contenido
        this.sender = sender;
        this.receiver = recipient;
        this.content = content;
        this.messageID = searchID();
        LocalDate Dnow = LocalDate.now();
        this.date = Dnow.format(Dformatter);
        LocalTime Tnow = LocalTime.now();
        this.time = Tnow.format(Tformatter);
    }

    public Message() {
        // Constructor vacío
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public int getMessageID() {
        return messageID;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(User recipient) {
        this.receiver = recipient;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public void setDate(LocalDate date) {
        this.date = date.format(Dformatter);
    }

    public void setTime(LocalTime time) {
        this.time = time.format(Tformatter);
    }

    @Override
    public String toString() {
        // Método para representar el mensaje como una cadena
        return this.date + "\n" + this.content + "\n" + this.time;
    }

    public int searchID() {
        // Busca el siguiente ID disponible para un nuevo mensaje
        List<Message> msgContainer = MessageList_Singleton.getInstance().getMessages();
        int tempID = 0;
        for (Message m: msgContainer) {
            if (m.getMessageID() == tempID)  {
                tempID++;
            } else {
                break;
            }
        }
        return tempID;
    }

    public String toCSV() {
        // Convierte el mensaje a formato CSV
        return "From: " + this.sender.getName() + " " + this.sender.getSurname() + ", To: " + this.receiver.getName() + " " + this.receiver.getSurname() + "\n" +
                this.content + "\n" +
                this.date + "  " + this.time + "\n";
    }
}
