package com.dalian.sea.py4j;


/**
 * @author Ricky Fung
 */
public interface Converter {

    String[] getPinyin(char ch) throws IllegalPinyinException;

    String getPinyin(String chinese) throws IllegalPinyinException;
}
