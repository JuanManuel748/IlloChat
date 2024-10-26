package com.github.JuanManuel.test;

import com.github.JuanManuel.model.entity.User;
import com.github.JuanManuel.model.entity.UserList;
import com.github.JuanManuel.model.utils.XMLManager;
import com.github.JuanManuel.view.WelcomeController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class testUser {
    public static void main(String[] args) {
        /*
        User pedro = new User("Pedro", "Picapiedras", "2mac@gmail.com", "antesdecristo");
        User angela = new User("Angela", "pitufa", "pituga@gmail.com", "hola1234");

        List<User> usersL = new ArrayList<>();
        UserList usrLS = new UserList();
        usrLS.add(pedro);
        usrLS.add(angela);

        boolean result = XMLManager.writeXML(usrLS, WelcomeController.userXML);
        if (result) {
            System.out.println("Escrito correctamente");
        } else {
            System.out.println("No se ha podido escribir");
        }

         */

        LocalDate oldest = LocalDate.MIN;
        System.out.println(oldest);
    }
}
