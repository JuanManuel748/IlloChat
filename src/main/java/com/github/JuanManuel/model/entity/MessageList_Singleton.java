package com.github.JuanManuel.model.entity;

import com.github.JuanManuel.model.utils.XMLManager;
import com.github.JuanManuel.view.WelcomeController;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="messageList")
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageList_Singleton implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILENAME = WelcomeController.messageXML;
    private static MessageList_Singleton _instance;

    @XmlElement(name="message")
    private List<Message> msgList;

    private MessageList_Singleton() {
        msgList = new ArrayList<>();
    }

    public static MessageList_Singleton getInstance() {
        if (_instance == null) {
            _instance = XMLManager.readXML(new MessageList_Singleton(), FILENAME);
        }
        return _instance;
    }

    public boolean save() {
        return XMLManager.writeXML(this, FILENAME);
    }

    public List<Message> getMessages() {
        return this.msgList;
    }

    public void setUsers(List<Message> msgLS) {
        this.msgList = msgLS;
    }

    public boolean addUser(Message msg) {
        boolean result = this.msgList.add(msg);
        save();
        return result;
    }

    @Override
    public String toString() {
        return "MessageList{" +
                "msgList=" + msgList +
                '}';
    }
}