package org.multithreading.producerconsumerproblemwordsearch.service;

import org.multithreading.producerconsumerproblemwordsearch.models.LineNumberAndPos;
import org.multithreading.producerconsumerproblemwordsearch.models.WordInput;
import org.multithreading.producerconsumerproblemwordsearch.models.WordOutput;
import org.multithreading.producerconsumerproblemwordsearch.models.WordSearchResult;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordLinePosAndOccurrences {
    FileUtils file = new FileUtils();

    //input = path and word
    public WordSearchResult getLinesAndPostionsOfWord(WordInput in) {
        List<WordSearchResult> finalOutput = new ArrayList<>();
        List<WordOutput> lines = getLines(in);
        List<LineNumberAndPos> lineNumberAndPos = new ArrayList<>();
        for (int j = 0; j < lines.size(); j++) {
            List<Integer> li = new ArrayList<>();
            String st = lines.get(j).getLine();
            int index = st.indexOf(in.getWord());
            while (index != -1) {
                li.add(index);
                index = st.indexOf(in.getWord(), index + 1);
            }
            if (!li.isEmpty()) {
                lineNumberAndPos.add(new LineNumberAndPos(j, li));
            }
        }
        //output =list (string, list of ( linenumber and list of (postions)
        return new WordSearchResult(in.getPath(), lineNumberAndPos);
    }

    //input == path and word
    public WordOutput getOccurrences(WordInput in) {
        WordSearchResult output = getLinesAndPostionsOfWord(in);
        int occurred = 0;
        List<LineNumberAndPos> list = output.getLineNumberAndPos();
        for (int i = 0; i < list.size(); i++) {
            occurred += list.get(i).getPos().size();
        }
        return new WordOutput(output.getAbsolutePath(), occurred);
    }

    private List<WordOutput> getLines(WordInput in) {
        List<WordOutput> lines = new ArrayList<>();
        try {
            File f0 = new File(in.getPath());
            if (!f0.exists()) {
                throw new FileNotFoundException("File not found");
            }
            BufferedReader bf = new BufferedReader(new FileReader(in.getPath()));
            String line;
            int lineNumber = 1;
            while ((line = bf.readLine()) != null) {
                lines.add(new WordOutput(lineNumber++, line));
            }
        } catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //output = line number and line
        return lines;
    }
}
