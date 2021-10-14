package com.example.dictionary.model;

import java.io.File;
import java.io.FileWriter;
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
            File f = new File(path) ;
            Scanner sc = new Scanner(f) ;

            while(sc.hasNextLine())
            {
                String line = sc.nextLine() ;
                String[] items = line.split("\t") ;
                dic.add(new Word(items[0], items[1])) ;
            }

            sc.close();
            System.out.println("Doc file thanh cong !") ;
        }
        catch(Exception e)
        {

            System.out.println("Doc file khong thanh cong: " + e) ;
        }
    }
    public static ArrayList<Word> dictionarySearcher(String searchWord) {
        ArrayList<Word> resWords = new ArrayList<Word>();
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
            FileWriter f = new FileWriter("dictionaries.txt") ;

            for(Word i:dic)
                f.write(i.toString()+"\n") ;

            f.close() ;

            System.out.println("Ghi du lieu ra file thanh cong") ;
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