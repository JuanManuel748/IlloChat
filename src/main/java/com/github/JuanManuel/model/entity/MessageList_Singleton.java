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
        // Constructor privado que inicializa la lista de mensajes
        msgList = new ArrayList<>();
    }

    public static MessageList_Singleton getInstance() {
        // Devuelve la instancia singleton, cargando desde XML si es necesario
        if (_instance == null) {
            _instance = XMLManager.readXML(new MessageList_Singleton(), FILENAME);
        }
        return _instance;
    }

    public boolean save() {
        // Guarda el estado actual de la lista de mensajes en XML
        return XMLManager.writeXML(this, FILENAME);
    }

    public List<Message> getMessages() {
        // Devuelve la lista de mensajes
        return this.msgList;
    }

    public void setUsers(List<Message> msgLS) {
        // Establece la lista de mensajes
        this.msgList = msgLS;
    }

    public boolean addUser(Message msg) {
        // Añade un mensaje a la lista y guarda los cambios
        boolean result = this.msgList.add(msg);
        save();
        return result;
    }

    @Override
    public String toString() {
        // Representa la lista de mensajes como una cadena
        return "MessageList{" +
                "msgList=" + msgList +
                '}';
    }
}
