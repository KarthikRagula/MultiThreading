package org.multithreading.producerconsumerproblemwordsearch.models;

import javax.sound.sampled.Line;
import java.util.List;

public class WordLineNumberAndPos {
    private List<LineAndPos> lineAndPos;
    private String absolutePath;

    public WordLineNumberAndPos(String absolutePath, List<LineAndPos> lineAndPos) {
        this.lineAndPos = lineAndPos;
        this.absolutePath = absolutePath;
    }

    public List<LineAndPos> getLineAndPos() {
        return lineAndPos;
    }

    public WordLineNumberAndPos(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }
}
