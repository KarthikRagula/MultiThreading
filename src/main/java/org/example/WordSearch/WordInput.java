package org.example.WordSearch;

public class WordInput {
    private String path;
    private String word;

    public WordInput(String path, String word) {
        this.path = path;
        this.word = word;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
