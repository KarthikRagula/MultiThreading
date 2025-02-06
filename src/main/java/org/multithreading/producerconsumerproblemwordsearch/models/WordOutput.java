package org.multithreading.producerconsumerproblemwordsearch.models;

import java.util.List;
import java.util.Map;

public class WordOutput {
    private int occurred;
    private int lineNumber;
    private List<Integer> pos;
    private String line;
    private String absolutePath;
    private List<WordOutput> lines;
    private String file;

    public String getFile() {
        return file;
    }

    public WordOutput(int lineNumber, String line) {
        this.lineNumber = lineNumber;
        this.line = line;
    }

    public WordOutput(String absolutePath, int occurred) {
        this.absolutePath = absolutePath;
        this.occurred = occurred;
    }

    public WordOutput(int occured) {
        this.occurred = occured;
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

    public String getAbsolutePath() {
        return absolutePath;
    }

    public List<WordOutput> getLines() {
        return lines;
    }
}
