package com.github.JuanManuel.test;

import com.github.JuanManuel.model.entity.*;
import com.github.JuanManuel.model.utils.XMLManager;
import com.github.JuanManuel.view.WelcomeController;
import java.util.*;

public class testMessage {
    public static void main(String[] args) {
        User franfu = new User("Franfu", "fran", "fran@gmail.com", "12345678");
        User Andrea = new User("Andrea", "ande", "andrea@gmail.com", "asdasd12");
        String contentMSG = "Buenos dias loca";

        Message msg1 = new Message(franfu, Andrea, contentMSG);
        //XMLManager.writeXML(msg1, WelcomeController.messageXML);

        System.out.println(msg1.toCSV());
    }
}
