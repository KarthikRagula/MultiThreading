package org.multithreading.producerconsumerproblemwordsearch.service;

import org.multithreading.producerconsumerproblemwordsearch.models.WordLineNumberAndPos;
import org.multithreading.producerconsumerproblemwordsearch.models.WordInput;
import org.multithreading.producerconsumerproblemwordsearch.models.WordOccured;
import org.multithreading.producerconsumerproblemwordsearch.models.WordSearchResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordLinePosAndOccurrences {
    FileUtils file = new FileUtils();

    //input = path and word
    public WordSearchResult getLinesAndPostionsOfWord(WordInput in) {
        List<WordLineNumberAndPos> lineNumberAndPos = new ArrayList<>();
        List<WordOccured> lines = new ArrayList<>();
        try {
            File f0 = new File(in.getPath());
            if (!f0.exists()) {
                throw new FileNotFoundException("File not found");
            }
            BufferedReader bf = new BufferedReader(new FileReader(in.getPath()));
            String line;
            int lineNumber = 1;
            while ((line = bf.readLine()) != null) {
                List<Integer> li=new ArrayList<>();
                String st = line;
                int index = st.indexOf(in.getWord());
                while (index != -1) {
                    li.add(index);
                    index = st.indexOf(in.getWord(), index + 1);
                }
                if (!li.isEmpty()) {
                    lineNumberAndPos.add(new WordLineNumberAndPos(lineNumber, li));
                }
            }
        } catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //output =list (string, list of ( linenumber and list of (postions)
        return new WordSearchResult(in.getPath(), lineNumberAndPos);
    }

    //input == path and word
    public WordOccured getOccurrences(WordInput in) {
        WordSearchResult output = getLinesAndPostionsOfWord(in);
        int occurred = 0;
        List<WordLineNumberAndPos> list = output.getLineNumberAndPos();
        for (int i = 0; i < list.size(); i++) {
            occurred += list.get(i).getPos().size();
        }
        return new WordOccured(output.getAbsolutePath(), occurred);
    }
}
