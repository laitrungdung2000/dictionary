package com.example.dictionary.model;

import java.util.* ;

public class Dictionary
{
    private ArrayList<Word> dic ;

    public Dictionary()
    {
        this.dic = new ArrayList<Word>() ;
    }

    public Dictionary(ArrayList<Word> dic)
    {
        this.dic = dic ;
    }

    public ArrayList<Word> getDic()
    {
        return this.dic ;
    }

    public void setDic(ArrayList<Word> dic)
    {
        this.dic = dic ;
    }
}