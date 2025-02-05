package org.example.ProducerConsumerProblemWordSearch;

import org.example.ProducerConsumerProblemWordSearch.WordSearch.WordInput;
import org.example.ProducerConsumerProblemWordSearch.WordSearch.WordLinePosAndOccurrences;
import org.example.ProducerConsumerProblemWordSearch.WordSearch.WordOutput;

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
            WordLinePosAndOccurrences wordSearch = new WordLinePosAndOccurrences();
            WordInput input = new WordInput(folder.getAbsolutePath(), null);
            List<WordOutput> listOfFiles = wordSearch.getListOfFiles(input);
            for (WordOutput file : listOfFiles) {
                fileList.add(file.getAbsolutePath());
                queue.put(file.getAbsolutePath());
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