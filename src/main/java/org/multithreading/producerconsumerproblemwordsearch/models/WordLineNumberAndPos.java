package org.multithreading.producerconsumerproblemwordsearch.models;

import java.util.List;

public class WordLineNumberAndPos {
    private int lineNumber;
    private List<Integer> pos;
    private String absolutePath;

    public WordLineNumberAndPos(int lineNumber, List<Integer> pos, String absolutePath) {
        this.lineNumber = lineNumber;
        this.pos = pos;
        this.absolutePath = absolutePath;
    }

    public WordLineNumberAndPos(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public List<Integer> getPos() {
        return pos;
    }
}