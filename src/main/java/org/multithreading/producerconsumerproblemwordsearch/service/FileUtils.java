package org.multithreading.producerconsumerproblemwordsearch.service;

import org.multithreading.producerconsumerproblemwordsearch.models.WordInput;
import org.multithreading.producerconsumerproblemwordsearch.models.WordOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
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
}
