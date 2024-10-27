package com.github.JuanManuel.model.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    // ATRIBUTOS
    @XmlElement
    private String Name;
    @XmlElement
    private String Surname;
    @XmlElement
    private String Email;
    @XmlElement
    private String Password;

    // CONSTRUCTORES
    public User(String name, String surname, String email, String password) {
        this.Name = name;
        this.Surname = surname;
        this.Email = email;
        this.Password = password;
    }

    public User() {
    }

    // GETTERS
    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    // SETTERS
    public void setName(String name) {
        this.Name = name;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }

    // Compara si este usuario es igual a otro
    public boolean Equals(User u) {
        return this.Email.equals(u.getEmail());
    }

    // Obtiene la fecha del último mensaje enviado por este usuario
    public LocalDateTime getLastMessageDate() {
        LocalDateTime result = LocalDateTime.MIN;
        List<Message> msgList = MessageList_Singleton.getInstance().getMessages();

        if (msgList == null || msgList.isEmpty()) {
            return result;
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        List<Message> filtList = new ArrayList<>();

        for (Message msg : msgList) {
            if (msg.getSender().Equals(this)) {
                filtList.add(msg);
            }
        }

        if (filtList.isEmpty()) {
            return result;
        }
        filtList.sort((o1, o2) -> {
            LocalDateTime date1 = LocalDateTime.parse(o1.getDate() + " " + o1.getTime(), dateTimeFormatter);
            LocalDateTime date2 = LocalDateTime.parse(o2.getDate() + " " + o2.getTime(), dateTimeFormatter);
            return date2.compareTo(date1);
        });

        Message lastMessage = filtList.get(0);
        result = LocalDateTime.parse(lastMessage.getDate() + " " + lastMessage.getTime(), dateTimeFormatter);
        return result;
    }
}
