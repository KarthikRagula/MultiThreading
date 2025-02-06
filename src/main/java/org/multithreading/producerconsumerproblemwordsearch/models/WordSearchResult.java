package org.multithreading.producerconsumerproblemwordsearch.models;

import java.util.List;

public class WordSearchResult {

    private String absolutePath;
    private List<WordOutput> lineNumberAndPos;

    public WordSearchResult(String absolutePath, List<WordOutput> lineNumberAndPos) {
        this.absolutePath = absolutePath;
        this.lineNumberAndPos = lineNumberAndPos;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public List<WordOutput> getLineNumberAndPos() {
        return lineNumberAndPos;
    }

    public void setLineNumberAndPos(List<WordOutput> lineNumberAndPos) {
        this.lineNumberAndPos = lineNumberAndPos;
    }
}
