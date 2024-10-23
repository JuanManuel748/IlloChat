package com.github.JuanManuel.view;

public enum Scenes {
    /*
    Pantallas:
        -WELCOME
        -LOG IN
        -REGISTER/SIGN IN
        -Lista con todos los chats
        -Chat con otro usuario
        -resumen de conversacion
    */
    ROOT("view/layout.fxml"),//PAGINA PRINCIPAL
    WELCOME("view/Welcome.fxml"), //ELEGIR ROL
    //MAINPAGE("view/mainPage.fxml"), //PAGINA PRINCIPAL
    LOGIN("view/login.fxml"), // PAGINA LOGIN
    REGISTER("view/register.fxml"), // PAGINA SIGN IN
    CHATROOM("view/chatRoom.fxml"), // PAGINA MENU CON TODOS LOS CHATS DEL USUARIO
    CHAT("view/chat.fxml"); // PAIGNA PARA CHATEAR CON OTRO USUARIO


    private final String url;

    Scenes(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}