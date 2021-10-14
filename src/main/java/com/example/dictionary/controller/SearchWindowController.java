package com.example.dictionary.controller;

import com.example.dictionary.DictionaryApplication;
import com.example.dictionary.model.Dictionary;
import com.example.dictionary.model.Word;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SearchWindowController {
//    public Dictionary dictionaries = new Dictionary();
    @FXML
    private TextField searchText;

    @FXML
    private ListView<String> searchedWordsList;

    @FXML
    private TextArea explainTextArea;

    @FXML
    private Button audioButton;

    @FXML
    private Button backButton;

    @FXML
    void onChangeInputText() {
        searchedWordsList.getItems().clear();
        explainTextArea.setText("");
        ArrayList<Word> words = Dictionary.dictionarySearcher(searchText.getText());
        for (Word word : words) {
            searchedWordsList.getItems().add(word.getWordTarget());
        }
        audioButton.setDisable(true);
    }

    @FXML
    void onAudioButtonClick() {
        String targetword = searchedWordsList.getSelectionModel().getSelectedItem();
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
            voice.speak(targetword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DictionaryApplication.class.getResource("mainMenu.fxml"));
        Stage searchWindow = (Stage) backButton.getScene().getWindow();
        searchWindow.setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    void showSelectedWord() {
        String targetword = searchedWordsList.getSelectionModel().getSelectedItem();
        Word selectedWord = Dictionary.dictionaryLookup(targetword);
        explainTextArea.setWrapText(true);
        explainTextArea.setText(selectedWord.getWordExplain());
        audioButton.setDisable(false);
    }

//    @FXML
//    protected void openAddDialog(ActionEvent event) throws IOException {
//
//        System.out.print(getClass().getResource(""));
//        Parent root = FXMLLoader.load(getClass().getResource("create-word-dialog.fxml"));
//        Dialog dialog = new Dialog();
//        dialog.getDialogPane().setContent(root);
//        dialog.show();
//    }

}