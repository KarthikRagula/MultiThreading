package org.multithreading.producerconsumerproblemwordsearch.service;

import org.multithreading.producerconsumerproblemwordsearch.models.WordInput;
import org.multithreading.producerconsumerproblemwordsearch.models.WordLineNumberAndPos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    //input = path and word
    public List<String> getListOfFiles(WordInput in) {
        List<String> filePaths = new ArrayList<>();
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

        } catch (Exception e) {
            throw new RuntimeException("Error while listing files", e);
        }
        //output = list of (absolutePath and Lines)
        return filePaths;
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
