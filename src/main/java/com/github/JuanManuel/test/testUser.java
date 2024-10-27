package com.github.JuanManuel.test;

import com.github.JuanManuel.model.entity.User;
import com.github.JuanManuel.model.entity.UserList;
import com.github.JuanManuel.model.entity.UserList_Singleton;
import com.github.JuanManuel.model.utils.XMLManager;
import com.github.JuanManuel.view.WelcomeController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class testUser {
    public static void main(String[] args) {
        List<User> userList = UserList_Singleton.getInstance().getUsers();
        userList.sort(new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                LocalDateTime lastMessageDate1 = u1.getLastMessageDate();
                LocalDateTime lastMessageDate2 = u2.getLastMessageDate();

                return lastMessageDate2.compareTo(lastMessageDate1);
            }
        });

        System.out.println(userList);
    }
}
