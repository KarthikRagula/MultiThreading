package org.example.WordSearch;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordLinePosAndOccurrences {

    //input = file path
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

    //input = path and word
    public List<WordOutput> getListOfFiles(WordInput in) {
        List<String> filePaths = new ArrayList<>();
        List<WordOutput> listOfFiles = new ArrayList<>();
        try {
            File filePath = new File(in.getPath());
            if (!filePath.exists()) {
                throw new RuntimeException("The provided path does not exist");
            }
            if (filePath.isDirectory()) {
                listFilesRecursive(filePath, filePaths);
            } else if (filePath.isFile()) {
                filePaths.add(filePath.getAbsolutePath());
            } else {
                throw new RuntimeException("The provided path is neither a file nor a directory");
            }
            for (int i = 0; i < filePaths.size(); i++) {
                File f1 = new File(filePaths.get(i));
                listOfFiles.add(new WordOutput(f1.getAbsolutePath(), getLines(new WordInput(f1.getAbsolutePath(), null))));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while listing files", e);
        }
        //output = list of (absolutePath and Lines)
        return listOfFiles;
    }

    //input = path and word
    public WordOutput getLinesAndPostionsOfWord(WordInput in) {
        List<WordOutput> listOfFiles = getListOfFiles(in);
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
                    lineNumberAndPos.add(new WordOutput(st, lines.get(j).getLineNumber(), li));
                }
            }
            finalOutput.put(listOfFiles.get(i).getAbsolutePath(), lineNumberAndPos);
        }
        //output =map of(string, list of (line, linenumber and list of (postions)))
        return new WordOutput(finalOutput);
    }

    private void listFilesRecursive(File directory, List<String> filePaths) {
        File[] filesAndDirs = directory.listFiles();
        if (filesAndDirs == null) {
            return;
        }
        for (File file : filesAndDirs) {
            if (file.isDirectory()) {
                listFilesRecursive(file, filePaths);
            } else {
                filePaths.add(file.getAbsolutePath());
            }
        }
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
