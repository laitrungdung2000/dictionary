package com.example.dictionary.model;

import java.io.File;
import java.util.* ;

public final class Dictionary
{
    private static ArrayList<Word> dic = new ArrayList<Word>();

//    public Dictionary()
//    {
//        this.dic = new ArrayList<Word>() ;
//    }
//
//    public Dictionary(ArrayList<Word> dic)
//    {
//        this.dic = dic ;
//    }
    private  Dictionary(){}
    public static ArrayList<Word> getDic()
    {
        return dic ;
    }

    public static void setDic(Word dic)
    {
        Dictionary.dic.add(dic) ;
    }

//    public void insertFromFile()
//    {
//        try
//        {
//            File f = new File("dictionaries.txt") ;
//            Scanner sc = new Scanner(f) ;
//
//            while(sc.hasNextLine())
//            {
//                String line = sc.nextLine() ;
//                String[] items = line.split("\t") ;
//                dic.add(new Word(items[0], items[1])) ;
//            }
//
//            sc.close();
//            System.out.println("Doc file thanh cong !") ;
//        }
//        catch(Exception e)
//        {
//
//            System.out.println("Doc file khong thanh cong: " + e) ;
//        }
//    }
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
}