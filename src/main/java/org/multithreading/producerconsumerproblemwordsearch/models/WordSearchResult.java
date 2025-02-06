package org.multithreading.producerconsumerproblemwordsearch.models;

import java.util.List;

public class WordSearchResult {

    private String absolutePath;
    private List<LineNumberAndPos> lineNumberAndPos;

    public WordSearchResult(String absolutePath, List<LineNumberAndPos> lineNumberAndPos) {
        this.absolutePath = absolutePath;
        this.lineNumberAndPos = lineNumberAndPos;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public List<LineNumberAndPos> getLineNumberAndPos() {
        return lineNumberAndPos;
    }
}
