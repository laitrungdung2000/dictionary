package com.example.dictionary.controller;

import com.example.dictionary.model.Dictionary;
import com.example.dictionary.model.Word;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    protected void playWord() {
        String bip = "test.mp3";
        Media hit = new Media(new File(bip).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }

    @FXML
    protected void searchWord() {
        String textSearch = searchText.getText();
        ArrayList<Word> wordSearchResults = Dictionary.dictionarySearcher(textSearch);
        System.out.print(wordSearchResults);
        String list_results = "Các từ tìm được là: \n";
        for(Word item: wordSearchResults) {
            list_results = list_results + item.getWordExplain() + "   " + item.getWordTarget() + "\n";
        }
        results.setText(list_results);
    }

    @FXML
    protected void getSearchInput() {
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