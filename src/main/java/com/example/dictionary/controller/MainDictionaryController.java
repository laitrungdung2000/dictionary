package com.example.dictionary.controller;

import com.example.dictionary.model.Dictionary;
import com.example.dictionary.model.Word;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.* ;
import java.io.* ;
public class MainDictionaryController {
//    public Dictionary dictionaries = new Dictionary();
    @FXML
    private Label results;

    @FXML
    private TextField searchText;

    @FXML
    private ListView<String> listSearchWords;

    @FXML
    void onChangeInputText() {
        listSearchWords.getItems().clear();
        ArrayList<Word> words = Dictionary.dictionarySearcher(searchText.getText());
        for (Word word: words) {
            listSearchWords.getItems().add(word.getWordTarget());
        }
    }

    @FXML
    protected void playWord() {
        String bip = "test.mp3";
        Media hit = new Media(new File(bip).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }

    @FXML
    protected void openAddDialog() throws IOException {
        System.out.print(getClass().getResource(""));
        Parent root = FXMLLoader.load(getClass().getResource("create-word-dialog.fxml"));
        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(root);
        dialog.show();
    }

}