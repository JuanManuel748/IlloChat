package com.github.JuanManuel.view;

import com.github.JuanManuel.App;
import java.io.IOException;


public abstract class Controller {

    App app;


    public void setApp(App app) {
        this.app = app;
    }


    public abstract void onOpen(Object input) throws Exception;


    public abstract void onClose(Object output);

}


