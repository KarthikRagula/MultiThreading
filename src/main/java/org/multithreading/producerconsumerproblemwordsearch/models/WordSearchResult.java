package org.multithreading.producerconsumerproblemwordsearch.models;

import java.util.List;

public class WordSearchResult {

    private List<WordLineNumberAndPos> wordLineNumberAndPos;

    public WordSearchResult(List<WordLineNumberAndPos> wordLineNumberAndPos) {
        this.wordLineNumberAndPos = wordLineNumberAndPos;
    }
}
