package org.multithreading.producerconsumerproblemwordsearch.models;

public class WordOccured {
    private String file;
    private int occurred;

    public WordOccured(String file, int occurred) {
        this.file = file;
        this.occurred = occurred;
    }

    public WordOccured(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public int getOccurred() {
        return occurred;
    }
}