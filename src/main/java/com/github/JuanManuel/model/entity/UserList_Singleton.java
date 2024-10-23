package com.github.JuanManuel.model.entity;

import com.github.JuanManuel.model.utils.XMLManager;
import com.github.JuanManuel.view.WelcomeController;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="userList")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserList_Singleton implements Serializable {
    private static final long serialVersionUID=1L;
    private static final String FILENAME = WelcomeController.userXML;
    private static UserList_Singleton _instance;

    @XmlElement(name="user")
    private List<User> usrList;

    private UserList_Singleton() {usrList = new ArrayList<>();}

    public static UserList_Singleton getInstance() {
        if(_instance == null) {
            _instance = XMLManager.readXML(new UserList_Singleton(), FILENAME);
        }
        return _instance;
    }

    public boolean save() {return XMLManager.writeXML(this, FILENAME);}
    public List<User> getUsers() {return usrList;}
    public void setUsers(List<User> usrLS) {this.usrList = usrLS;}

    public boolean addUser(User u) {
        boolean result = this.usrList.add(u);
        save();
        return result;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "usrList=" + usrList +
                '}';
    }


}
