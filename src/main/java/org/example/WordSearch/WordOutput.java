package org.example.WordSearch;

import java.util.List;
import java.util.Map;

public class WordOutput {
    private int occurred;
    private int lineNumber;
    private List<Integer> pos;
    private String line;
    private String absolutePath;
    private List<WordOutput> lines;
    private Map<String, List<WordOutput>> finalOutput;

    public WordOutput(Map<String, List<WordOutput>> finalOutput) {
        this.finalOutput = finalOutput;
    }

    public WordOutput(int lineNumber, String line) {
        this.lineNumber = lineNumber;
        this.line = line;
    }

    public WordOutput(String absolutePath, int occurred) {
        this.absolutePath = absolutePath;
        this.occurred = occurred;
    }

    public WordOutput(String line, int lineNumber, List<Integer> pos) {
        this.line = line;
        this.lineNumber = lineNumber;
        this.pos = pos;
    }

    public WordOutput(int occured) {
        this.occurred = occured;
    }

    public WordOutput(String absolutePath, List<WordOutput> lines) {
        this.absolutePath = absolutePath;
        this.lines = lines;
    }

    public int getOccurred() {
        return occurred;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public List<Integer> getPos() {
        return pos;
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

    public Map<String, List<WordOutput>> getFinalOutput() {
        return finalOutput;
    }
}
