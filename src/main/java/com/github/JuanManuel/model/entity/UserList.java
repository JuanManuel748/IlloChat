package com.github.JuanManuel.model.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class UserList {
    private List<User> users = new ArrayList<>();

    public UserList() {
        // Inicializa la lista de usuarios a partir de la instancia singleton
        UserList_Singleton usrLS_ST = UserList_Singleton.getInstance();
        this.users = usrLS_ST.getUsers();
    }

    public UserList(List<User> users) {
        // Constructor que establece la lista de usuarios
        this.users = users;
    }

    @XmlElement(name = "user")
    public List<User> getUsers() {
        // Devuelve la lista de usuarios
        return users;
    }

    public void setUsers(List<User> users) {
        // Establece la lista de usuarios
        this.users = users;
    }

    public void add(User usr) {
        // Añade un usuario a la lista
        users.add(usr);
    }
}
