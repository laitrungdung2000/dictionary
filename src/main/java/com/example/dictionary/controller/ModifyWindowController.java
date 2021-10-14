package com.example.dictionary.controller;

import com.example.dictionary.DictionaryApplication;
import com.example.dictionary.model.Word;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyWindowController {

    @FXML
    private TextField searchField;

    @FXML
    private ListView<Word> selectedWordsList;

    @FXML
    private Button backButton;

    @FXML
    private Label statusNotification;

    @FXML
    private Button addButton;

    @FXML
    private Button modifyButton;

    @FXML
    private Button removeButton;

    @FXML
    void onAddButtonClick() {

    }

    @FXML
    void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DictionaryApplication.class.getResource("mainMenu.fxml"));
        Stage searchWindow = (Stage) backButton.getScene().getWindow();
        searchWindow.setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    void onChangingSearchField() {

    }

    @FXML
    void onModifyButtonClick() {

    }

    @FXML
    void onRemoveButtonClick() {

    }

}
