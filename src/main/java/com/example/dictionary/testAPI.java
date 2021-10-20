package com.example.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringEscapeUtils;

public class testAPI {
    public static void main(String[] args) throws IOException {
        String text = "Tôi là Đạt";
        //Translated text: Hallo Welt!
        System.out.println("Translated text: " + translate("", "en", text));
    }

    private static String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbyaq4XslcVvHUpAZqjIHS0ltX0yvmNQmOc06QREWdIf9zmln08pkWycXILzV9cCYilZrw/exec" +
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
}
