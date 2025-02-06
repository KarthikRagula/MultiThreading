package org.multithreading.producerconsumerproblemwordsearch.models;

import java.util.List;

public class WordSearchResult {

    private String absolutePath;
    private List<WordLineNumberAndPos> lineNumberAndPos;

    public WordSearchResult(String absolutePath, List<WordLineNumberAndPos> lineNumberAndPos) {
        this.absolutePath = absolutePath;
        this.lineNumberAndPos = lineNumberAndPos;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public List<WordLineNumberAndPos> getLineNumberAndPos() {
        return lineNumberAndPos;
    }
}
