package org.multithreading.producerconsumerproblemwordsearch.models;

import java.util.List;

public class LineAndPos {
    private int lineNumber;
    private List<Integer> pos;

    public LineAndPos(int lineNumber, List<Integer> pos) {
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
