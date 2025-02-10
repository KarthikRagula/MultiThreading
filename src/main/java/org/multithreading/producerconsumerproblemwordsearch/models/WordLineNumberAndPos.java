package org.multithreading.producerconsumerproblemwordsearch.models;

import java.util.List;

public class WordLineNumberAndPos {
    private int lineNumber;
    private List<Integer> pos;
    private String absolutePath;
    private List<WordLineNumberAndPos> lineNumberAndPos;
    private String file;
    private int occurred;

    public WordLineNumberAndPos(String file, int occurred) {
        this.file = file;
        this.occurred = occurred;
    }

    public WordLineNumberAndPos(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public int getOccurred() {
        return occurred;
    }

    public WordLineNumberAndPos(String absolutePath, List<WordLineNumberAndPos> lineNumberAndPos) {
        this.absolutePath = absolutePath;
        this.lineNumberAndPos = lineNumberAndPos;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public List<WordLineNumberAndPos> getLineNumberAndPos() {
        return lineNumberAndPos;
    }


    public WordLineNumberAndPos(int lineNumber, List<Integer> pos) {
        this.lineNumber = lineNumber;
        this.pos = pos;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public List<Integer> getPos() {
        return pos;
    }
}
