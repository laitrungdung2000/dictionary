package com.example.dictionary.controller;

import com.example.dictionary.DictionaryApplication;
import com.example.dictionary.model.Dictionary;
import com.example.dictionary.model.Word;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ModifyWindowController {

    @FXML
    private TextField searchField;

    @FXML
    private TextArea meaningTextArea;

    @FXML
    private TextField englishTextField;

    @FXML
    private TextField pronounceTextField;

    @FXML
    private ListView<String> searchedWordsList;

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
        statusNotification.setWrapText(true);
        String englishWord = englishTextField.getText();
        StringBuilder explain = new StringBuilder();
        explain.append(englishWord).append("\n")
                .append(pronounceTextField.getText())
                .append("\n").append(meaningTextArea.getText());
        if (Dictionary.addWord(new Word(englishWord, explain.toString()))) {
            statusNotification.setStyle("-fx-text-fill: #36ff0a");
            statusNotification.setText('\"' + englishWord.toLowerCase() + '\"' + " added successfully!");
        } else {
            statusNotification.setStyle("-fx-text-fill: #ff1206");
            statusNotification.setText('\"' + englishWord.toLowerCase() + '\"' + " already exists!");
        }
    }

    @FXML
    void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DictionaryApplication.class.getResource("mainMenu.fxml"));
        Stage searchWindow = (Stage) backButton.getScene().getWindow();
        searchWindow.setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    void onChangingSearchField() {
        searchedWordsList.getItems().clear();
        meaningTextArea.setText("");
        englishTextField.setText("");
        pronounceTextField.setText("");
        ArrayList<Word> words = Dictionary.dictionarySearcher(searchField.getText());
        for (Word word : words) {
            searchedWordsList.getItems().add(word.getWordTarget());
        }
        addButton.setDisable(true);
        removeButton.setDisable(true);
        modifyButton.setDisable(true);
        statusNotification.setText("");
    }

    @FXML
    void onModifyButtonClick() {
        int pos = -1;
        String targetword = searchedWordsList.getSelectionModel().getSelectedItem();
        for (int i = 0; i < Dictionary.getDic().size(); i++) {
            if (targetword.equals(Dictionary.getDic().get(i).getWordTarget())) {
                pos = i;
            }
        }
        statusNotification.setWrapText(true);
        String englishWord = englishTextField.getText();
        StringBuilder explain = new StringBuilder();
        explain.append(englishWord).append("\n")
                .append(pronounceTextField.getText())
                .append("\n").append(meaningTextArea.getText());
        if (Dictionary.modifyWord(pos, new Word(englishWord, explain.toString()))) {
            statusNotification.setStyle("-fx-text-fill: #36ff0a");
            statusNotification.setText('\"' + englishWord.toLowerCase() + '\"' + " modified successfully!");
        } else {
            statusNotification.setStyle("-fx-text-fill: #ff1206");
            statusNotification.setText('\"' + englishWord.toLowerCase() + '\"' + " already exists!");
        }

        searchedWordsList.getItems().clear();
        ArrayList<Word> words = Dictionary.dictionarySearcher(searchField.getText());
        for (Word word : words) {
            searchedWordsList.getItems().add(word.getWordTarget());
        }
    }

    @FXML
    void onRemoveButtonClick() {
        statusNotification.setWrapText(true);
        System.out.println("Click");
        String targetword = searchedWordsList.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Do you want to delete \"" + targetword + "\".");
        if (alert.showAndWait().get() == ButtonType.OK) {
            Word selectedWord = Dictionary.dictionaryLookup(targetword);
            Dictionary.removeWord(selectedWord);
            searchedWordsList.getItems().clear();
            ArrayList<Word> words = Dictionary.dictionarySearcher(searchField.getText());
            for (Word word : words) {
                searchedWordsList.getItems().add(word.getWordTarget());
            }
            meaningTextArea.setText("");
            englishTextField.setText("");
            pronounceTextField.setText("");

            statusNotification.setStyle("-fx-text-fill: #36ff0a");
            statusNotification.setText('\"' + targetword.toLowerCase() + '\"' + " is removed successfully!");
        }
    }

    @FXML
    void showSelectedWord() {
        String targetword = searchedWordsList.getSelectionModel().getSelectedItem();
        Word selectedWord = Dictionary.dictionaryLookup(targetword);
        meaningTextArea.setWrapText(true);
        String splitStrings[] = selectedWord.getWordExplain().split("\n", 3);
        englishTextField.setText(splitStrings[0]);
        pronounceTextField.setText(splitStrings[1]);
        meaningTextArea.setText(splitStrings[2]);

        addButton.setDisable(true);
        removeButton.setDisable(false);
        statusNotification.setText("");
    }

    @FXML
    void onTypingWord() {

            if (!englishTextField.getText().equals("") && !meaningTextArea.getText().equals("")) {
                addButton.setDisable(false);
                if (searchedWordsList.getSelectionModel().getSelectedItem() != null) {
                    modifyButton.setDisable(false);
                }
            } else {
                addButton.setDisable(true);
                modifyButton.setDisable(true);
            }
            statusNotification.setText("");
        if (!modifyButton.isDisable()) {
            addButton.setDisable(true);
        }
        if (searchedWordsList.getSelectionModel().getSelectedItem() != null) {
            String targetword = searchedWordsList.getSelectionModel().getSelectedItem();
            Word selectedWord = Dictionary.dictionaryLookup(targetword);
            String englishWord = englishTextField.getText();
            StringBuilder explain = new StringBuilder();
            explain.append(englishWord).append("\n")
                    .append(pronounceTextField.getText())
                    .append("\n").append(meaningTextArea.getText());
            if (selectedWord.getWordExplain().equals(explain.toString())) {
                modifyButton.setDisable(true);
            }
        }
    }
}
