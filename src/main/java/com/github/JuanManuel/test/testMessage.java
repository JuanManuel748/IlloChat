package com.github.JuanManuel.test;

import com.github.JuanManuel.model.entity.*;
import com.github.JuanManuel.model.utils.XMLManager;
import com.github.JuanManuel.view.WelcomeController;

import java.util.ArrayList;
import java.util.List;

public class testMessage {
    public static void main(String[] args) {
        User franfu = new User("Franfu", "fran", "fran@gmail.com", "12345678");
        User Andrea = new User("Andrea", "ande", "andrea@gmail.com", "asdasd12");
        String contentMSG = "Buenos dias loca";

        //        //XMLManager.writeXML(msg1, WelcomeController.messageXML);
        Message msg1 = new Message(franfu, Andrea, contentMSG);

        MessageList msgLS = new MessageList();
        msgLS.addMessage(msg1);


        boolean result = XMLManager.writeXML(msgLS, WelcomeController.messageXML);
        if (result) {
            System.out.println("Escrito correctamente");
        } else {
            System.out.println("No se ha podido escribir");
        }
        System.out.println(msgLS.toString());
    }
}
