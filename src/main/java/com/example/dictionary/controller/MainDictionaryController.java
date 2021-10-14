package com.example.dictionary.controller;

import com.example.dictionary.model.Dictionary;
import com.example.dictionary.model.Word;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
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
    private TextField searchText;

    @FXML
    private ListView<String> listSearchWords;

    @FXML
    private TextArea explainTextArea;

    @FXML
    void onChangeInputText() {
        listSearchWords.getItems().clear();
        ArrayList<Word> words = Dictionary.dictionarySearcher(searchText.getText());
        for (Word word: words) {
            listSearchWords.getItems().add(word.getWordTarget());
        }
    }

    @FXML
    void showSelectedWord() {
        String targetWord = listSearchWords.getSelectionModel().getSelectedItem();
        Word resWord = Dictionary.dictionaryLookup(targetWord);
        explainTextArea.setText(resWord.getWordTarget() + "\n" + resWord.getWordExplain());
    }

    @FXML
    protected void playWord() {
//        String bip = "test.mp3";
//        Media hit = new Media(new File(bip).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(hit);
//        mediaPlayer.play();
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin");
        ;
        if (voice != null) {
            voice.allocate();
        }
        try {
            voice.setRate(190);
            voice.setPitch(100);
            voice.setVolume(3);
            String[] parts = explainTextArea.getText().split("\n");
            voice.speak(parts[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void openAddDialog(ActionEvent event) throws IOException {

        System.out.print(getClass().getResource(""));
        Parent root = FXMLLoader.load(getClass().getResource("create-word-dialog.fxml"));
        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(root);
        dialog.show();
    }

}