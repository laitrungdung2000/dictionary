package com.example.dictionary.controller;

import com.example.dictionary.DictionaryApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button searchModeButton;

    @FXML
    private Button modifyModeButton;

    @FXML
    private Button apiModeButton;

    @FXML
    void onApiModeButton( ) {

    }

    @FXML
    void onModifyModeButtonClick( ) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DictionaryApplication.class.getResource("modifyWindow.fxml"));
        Stage searchWindow = (Stage) searchModeButton.getScene().getWindow();
        searchWindow.setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    void onSearchModeButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DictionaryApplication.class.getResource("searchWindow.fxml"));
        Stage searchWindow = (Stage) searchModeButton.getScene().getWindow();
        searchWindow.setScene(new Scene(fxmlLoader.load()));
    }

}
