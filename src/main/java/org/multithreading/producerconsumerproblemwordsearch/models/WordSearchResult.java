package org.multithreading.producerconsumerproblemwordsearch.models;

import java.util.List;

public class WordSearchResult {

    public List<WordLineNumberAndPos> getListOfAllFilesOutput() {
        return listOfAllFilesOutput;
    }

    private List<WordLineNumberAndPos> listOfAllFilesOutput;

    public WordSearchResult(List<WordLineNumberAndPos> listOfAllFilesOutput) {
        this.listOfAllFilesOutput = listOfAllFilesOutput;
    }
}
