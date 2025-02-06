package org.multithreading.producerconsumerproblemwordsearch.models;

import java.util.List;
import java.util.Map;

public class WordOutput {
    private int occurred;
    private int lineNumber;
    private String line;
    private String file;

    public String getFile() {
        return file;
    }

    public WordOutput(int lineNumber, String line) {
        this.lineNumber = lineNumber;
        this.line = line;
    }

    public WordOutput(String file, int occurred) {
        this.file = file;
        this.occurred = occurred;
    }

    public WordOutput(String file) {
        this.file = file;
    }

    public int getOccurred() {
        return occurred;
    }

    public String getLine() {
        return line;
    }
}