module com.github.JuanManuel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.desktop;

    opens com.github.JuanManuel.model.entity to java.xml.bind;

    opens com.github.JuanManuel.view to javafx.fxml;
    exports com.github.JuanManuel.view;
    exports com.github.JuanManuel;
    opens com.github.JuanManuel to javafx.fxml;

}

