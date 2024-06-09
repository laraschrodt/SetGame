module org.example.setgame {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javafx.media;

    opens org.example.setgame to javafx.fxml;
    exports org.example.setgame;
    opens org.example.setgame.otherScreens to javafx.fxml;
    opens org.example.setgame.singleplayer to javafx.fxml;
    opens org.example.setgame.multiplayer to javafx.fxml;
    exports org.example.setgame.otherScreens;
    exports org.example.setgame.multiplayer;

}