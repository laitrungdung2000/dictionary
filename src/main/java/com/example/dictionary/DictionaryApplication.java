package com.example.dictionary;

import com.example.dictionary.model.Dictionary;
import com.example.dictionary.model.Word;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class DictionaryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DictionaryApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Dictionary");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("dictionaries.txt") ;
        Scanner sc = new Scanner(f) ;

        while(sc.hasNextLine())
        {
            String line = sc.nextLine() ;
            String[] items = line.split("\t") ;
            Dictionary.setDic(new Word(items[0], items[1])) ;
        }

        sc.close();
        System.out.println("Doc file thanh cong !") ;
        launch();
    }
}