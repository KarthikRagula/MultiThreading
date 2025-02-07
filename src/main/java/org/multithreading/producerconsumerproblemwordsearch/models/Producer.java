package org.multithreading.producerconsumerproblemwordsearch.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.multithreading.producerconsumerproblemwordsearch.service.FileUtils;

import java.io.File;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private File folder;
    private BlockingQueue<String> queue;
    private List<String> fileList;
    private volatile boolean flag = false;

    public boolean flag() {
        return flag;
    }

    public Producer(File folder, BlockingQueue<String> queue, List<String> fileList) {
        this.folder = folder;
        this.queue = queue;
        this.fileList = fileList;
    }

    @Override
    public void run() {
        try {
            FileUtils fileObj = new FileUtils();
            WordInput input = new WordInput(folder.getAbsolutePath());
            List<WordOccured> listOfFiles = fileObj.getListOfFiles(input);
            for (WordOccured file : listOfFiles) {
                fileList.add(file.getFile());
                queue.put(file.getFile());
            }
            logger.info("files added to the queue");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("producer interrupted");
        } finally {
            flag = true;
        }
    }
}