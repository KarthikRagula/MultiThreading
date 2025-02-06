package org.multithreading.producerconsumerproblemwordsearch.models;

import org.multithreading.producerconsumerproblemwordsearch.service.FileUtils;
import org.multithreading.producerconsumerproblemwordsearch.service.WordLinePosAndOccurrences;

import java.io.File;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private File folder;
    private BlockingQueue<String> queue;
    private List<String> fileList;

    public Producer(File folder, BlockingQueue<String> queue, List<String> fileList) {
        this.folder = folder;
        this.queue = queue;
        this.fileList = fileList;
    }

    @Override
    public void run() {
        try {
            FileUtils fileObj=new FileUtils();
            WordInput input = new WordInput(folder.getAbsolutePath());
            List<WordOutput> listOfFiles = fileObj.getListOfFiles(input);
            for (WordOutput file : listOfFiles) {
                fileList.add(file.getFile());
                queue.put(file.getFile());
            }
            for (int i = 0; i < 9; i++) {
                queue.put("STOP");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Producer interrupted");
        }
    }
}