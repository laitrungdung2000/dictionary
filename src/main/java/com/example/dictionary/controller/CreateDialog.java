package com.example.dictionary.controller;

import com.example.dictionary.model.Dictionary;
import com.example.dictionary.model.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CreateDialog {


    @FXML
    private TextField targetWord;

    @FXML
    private TextField explainWord;

    @FXML
    protected void close() {
    }

    @FXML
    protected  void add(ActionEvent event) {
        Dictionary.insertToFile(new Word(targetWord.getText(), explainWord.getText()));
        Node  source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
