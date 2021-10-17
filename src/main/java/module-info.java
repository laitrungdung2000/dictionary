module com.example.dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.compiler;
    requires freetts;
    requires commons.lang3;

    opens com.example.dictionary to javafx.fxml;
    exports com.example.dictionary;
    exports com.example.dictionary.controller;
    opens com.example.dictionary.controller to javafx.fxml;
}