package org.multithreading.producerconsumerproblemwordsearch.service;

import org.multithreading.producerconsumerproblemwordsearch.models.WordInput;
import org.multithreading.producerconsumerproblemwordsearch.models.WordOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordLinePosAndOccurrences {

    FileUtils file=new FileUtils();
    //input = path and word
    public WordOutput getLinesAndPostionsOfWord(WordInput in) {
        List<WordOutput> listOfFiles = file.getListOfFiles(in);
        Map<String, List<WordOutput>> finalOutput = new HashMap<>();
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
            finalOutput.put(listOfFiles.get(i).getAbsolutePath(), lineNumberAndPos);
        }
        //output =map of(string, list of (line, linenumber and list of (postions)))
        return new WordOutput(finalOutput);
    }

    //input == path and word
    public List<WordOutput> getOccurrences(WordInput in) {
        WordOutput output = getLinesAndPostionsOfWord(in);
        Map<String, List<WordOutput>> finalOutput = output.getFinalOutput();
        List<WordOutput> occurredList = new ArrayList<>();
        int occurred = 0;
        for (Map.Entry<String, List<WordOutput>> map : finalOutput.entrySet()) {
            List<WordOutput> list = map.getValue();
            occurred = 0;
            for (int i = 0; i < list.size(); i++) {
                occurred += list.get(i).getPos().size();
            }
            occurredList.add(new WordOutput(map.getKey(), occurred));
        }
        return occurredList;
    }
}
