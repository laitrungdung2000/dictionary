package com.example.dictionary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import java.io.File;
import java.io.IOException;

import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MainDictionaryController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextArea searchText;

    @FXML
    protected void playWord() {
        String bip = "test.mp3";
        Media hit = new Media(new File(bip).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }

    @FXML
    protected void searchWord() {
        welcomeText.setText(searchText.getText());
    }

    @FXML
    protected void getSearchInput() {
        searchText.getText();
    }

    @FXML
    protected void openAddDialog() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("create-word-dialog.fxml"));
        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(root);
        dialog.show();
    }

}