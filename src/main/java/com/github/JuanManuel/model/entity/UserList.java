package com.github.JuanManuel.model.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement // Indica que esta clase puede ser el elemento raíz en el XML
public class UserList {
    private List<User> users = new ArrayList<>();

    public UserList() {
        UserList_Singleton usrLS_ST = UserList_Singleton.getInstance();
        this.users = usrLS_ST.getUsers();
    }

    public UserList(List<User> users) {
        this.users = users;
    }

    @XmlElement(name = "user") // Define cómo se serializa cada elemento en la lista
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void add(User usr) {users.add(usr);}


}