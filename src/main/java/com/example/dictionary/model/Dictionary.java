package com.example.dictionary.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.* ;

public final class Dictionary
{
    private static ArrayList<Word> dic = new ArrayList<Word>();


    public static ArrayList<Word> getDic()
    {
        return dic ;
    }

    public static void setDic(Word dic)
    {
        Dictionary.dic.add(dic) ;
    }

    public static void insertFromFile(String path)
    {
        try
        {
            String filePath = new File(path).getAbsolutePath();
            Scanner scanner = new Scanner(Paths.get(filePath)
                    , StandardCharsets.UTF_8);
            while (scanner.hasNextLine()) {
                StringBuilder explain = new StringBuilder();
                String target = scanner.nextLine();
                explain.append(target + "\n");
                while(scanner.hasNextLine()) {
                    String temp = scanner.nextLine();
                    if (temp.equals("")) {
                        break;
                    }
                    explain.append(temp + "\n");
                }
                Word newWord = new Word(target, explain.toString());
                dic.add(newWord);
            }
            scanner.close();
            System.out.println("Doc file thanh cong !") ;
        }
        catch(Exception e)
        {
            System.out.println("Doc file khong thanh cong: " + e) ;
        }
    }

    public static boolean addWord (Word newWord) {
        for (int i = 0; i < dic.size(); i++) {
            if (newWord.getWordTarget().toLowerCase()
                    .compareTo(dic.get(i).getWordTarget().toLowerCase()) == 0) {
                return false;
            } else if (newWord.getWordTarget().toLowerCase()
                    .compareTo(dic.get(i).getWordTarget().toLowerCase()) < 0) {
                dic.add(i, newWord);
                return true;
            }
        }
        dic.add(newWord);
        return true;
    }

    public static boolean modifyWord(int pos, Word modifyWord) {
        boolean checkModify = true;
        for (int i = 0; i < dic.size(); i++) {
            if (i != pos) {
                if (modifyWord.getWordTarget().toLowerCase()
                        .equals(dic.get(i).getWordTarget().toLowerCase())) {
                    checkModify = false;
                    break;
                }
            }
        }
        if (checkModify) {
            dic.set(pos, modifyWord);
        }
        return checkModify;
    }

    public static void removeWord(Word word) {
        for (int i = 0; i < dic.size(); i++) {
            if (word.getWordTarget().toLowerCase().equals(dic.get(i).getWordTarget())) {
                dic.remove(word);
            }
        }
    }

    public static ArrayList<Word> dictionarySearcher(String searchWord) {
        ArrayList<Word> resWords = new ArrayList<>();
        for (Word item : dic) {
            if (item.getWordTarget().startsWith(searchWord)) {
                resWords.add(item);
            }
        }
        return resWords;
    }


    public static Word dictionaryLookup(String word_lookup) {
        ArrayList<Word> searchedWords = dictionarySearcher(word_lookup);
        Word resWord = new Word();
        for (Word item : searchedWords) {
            if (word_lookup.equals(item.getWordTarget())) {
                resWord = item;
                break;
            }
        }
        return resWord;
    }

    public static void dictionaryExportToFile() {
        try
        {
            String path = new File("dictionaries1.txt").getAbsolutePath();
            PrintWriter printWriter = new PrintWriter(path, StandardCharsets.UTF_8);
            for (Word item :
                    dic) {
                printWriter.println(item.getWordExplain());
            }
            printWriter.close();
        }
        catch(Exception e)
        {
            System.out.println("Ghi du lieu ra file khong thanh cong voi loi: " + e) ;
        }
    }

    public static void insertToFile(Word word) {
        dic.add(word);
        dictionaryExportToFile();
    }

    public static void updateWordInFile(int index, Word word) {
        dic.set(index, word);
        dictionaryExportToFile();
    }

    public static void removeWordInFile(Word word) {
        dic.remove(word);
        dictionaryExportToFile();
    }
}