package org.multithreading.producerconsumerproblemwordsearch.models;

import java.util.List;

public class LineNumberAndPos {
    private int lineNumber;
    private List<Integer> pos;

    public LineNumberAndPos(int lineNumber, List<Integer> pos) {
        this.lineNumber = lineNumber;
        this.pos = pos;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public List<Integer> getPos() {
        return pos;
    }

    public void setPos(List<Integer> pos) {
        this.pos = pos;
    }
}
