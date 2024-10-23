package com.github.JuanManuel.model.entity;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    // ATTRIBUTES
    @XmlElement
    private String Name;
    @XmlElement
    private String Surname;
    @XmlElement
    private String Email;
    @XmlElement
    private String Password;


    // CONSTRUCTORS
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

}
