package com.github.JuanManuel.test;

import com.github.JuanManuel.model.entity.*;
import com.github.JuanManuel.model.utils.XMLManager;
import com.github.JuanManuel.view.WelcomeController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class testMessage {
    public static void main(String[] args) {
        List<Message> msgList = MessageList_Singleton.getInstance().getMessages();
        msgList.sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                LocalDateTime date1 = LocalDateTime.parse(o1.getDate() + " " + o1.getTime(), dateTimeFormatter);
                LocalDateTime date2 = LocalDateTime.parse(o2.getDate() + " " + o2.getTime(), dateTimeFormatter);

                return date2.compareTo(date1);
            }
        });

        System.out.println(msgList);
    }
}
