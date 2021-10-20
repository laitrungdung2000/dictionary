package com.example.dictionary.controller;

import com.example.dictionary.DictionaryApplication;
import com.example.dictionary.model.Dictionary;
import com.example.dictionary.model.Word;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ApiTranslateController {
    @FXML
    private TextArea searchField;

    @FXML
    private TextArea explainTextArea;

    @FXML
    private Button backButton;

    @FXML
    private Button translateButton;

    @FXML
    private ComboBox<String> inputLanguage;

    @FXML
    private ComboBox<String> explainLanguage;

    @FXML
    void initialize() {
        inputLanguage.getItems().addAll("Auto", "English", "Vietnamese");
        inputLanguage.getSelectionModel().selectFirst();
        explainLanguage.getItems().addAll("Vietnamese", "English");
        explainLanguage.getSelectionModel().selectFirst();

    }

    @FXML
    void onTranslateButtonClick() throws IOException {
        explainTextArea.setText("Wait...");
        String inputLang = inputLanguage.getSelectionModel().getSelectedItem();
        String explainLang = explainLanguage.getSelectionModel().getSelectedItem();

        String langFrom = "";
        if (inputLang.equals("Auto")) {
            langFrom = "";
        } else if (inputLang.equals("English")) {
            langFrom = "en";
        } else if (inputLang.equals("Vietnamese")) {
            langFrom = "vi";
        }

        String langTo = "vi";
        if (explainLang.equals("English")) {
            langTo = "en";
        } else if (explainLang.equals("Vietnamese")) {
            langTo  = "vi";
        }
        String response = translate(langFrom, langTo, searchField.getText());

        explainTextArea.setText(response);
    }

    @FXML
    void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DictionaryApplication.class.getResource("mainMenu.fxml"));
        Stage searchWindow = (Stage) backButton.getScene().getWindow();
        searchWindow.setScene(new Scene(fxmlLoader.load()));
    }

    private static String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbyTom3_qIZb0DX5VYahGAnupKQurlpns-6FUitQYw/exec" +
                "?q=" + URLEncoder.encode(text, StandardCharsets.UTF_8) +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            inputLine = StringEscapeUtils.unescapeHtml4(inputLine);
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    @FXML
    void onTypingWord() {
        explainTextArea.setText("");
    }

}
