package org.multithreading.producerconsumerproblemwordsearch.service;

import org.multithreading.producerconsumerproblemwordsearch.models.WordInput;
import org.multithreading.producerconsumerproblemwordsearch.models.WordOutput;
import org.multithreading.producerconsumerproblemwordsearch.models.WordSearchResult;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordLinePosAndOccurrences {

    FileUtils file=new FileUtils();
    //input = path and word
    public List<WordSearchResult> getLinesAndPostionsOfWord(WordInput in) {
        List<WordOutput> listOfFiles = file.getListOfFiles(in);
        List<WordSearchResult> finalOutput = new ArrayList<>();
        for (int i = 0; i < listOfFiles.size(); i++) {
            List<WordOutput> lines = listOfFiles.get(i).getLines();
            List<WordOutput> lineNumberAndPos = new ArrayList<>();
            for (int j = 0; j < lines.size(); j++) {
                List<Integer> li = new ArrayList<>();
                String st = lines.get(j).getLine();
                int index = st.indexOf(in.getWord());
                while (index != -1) {
                    li.add(index);
                    index = st.indexOf(in.getWord(), index + 1);
                }
                if (!li.isEmpty()) {
                    lineNumberAndPos.add(new WordOutput(j, li));
                }
            }
            finalOutput.add(new WordSearchResult(listOfFiles.get(i).getAbsolutePath(), lineNumberAndPos));
        }
        //output =list (string, list of ( linenumber and list of (postions)))
        return finalOutput;
    }

    //input == path and word
    public List<WordOutput> getOccurrences(WordInput in) {
        List<WordSearchResult> output = getLinesAndPostionsOfWord(in);
        List<WordOutput> occurredList = new ArrayList<>();
        int occurred = 0;
        for(WordSearchResult out:output) {
            List<WordOutput> list=out.getLineNumberAndPos();
            occurred = 0;
            for (int i = 0; i < list.size(); i++) {
                occurred += list.get(i).getPos().size();
            }
            occurredList.add(new WordOutput(out.getAbsolutePath(), occurred));
        }
        return occurredList;
    }
}
