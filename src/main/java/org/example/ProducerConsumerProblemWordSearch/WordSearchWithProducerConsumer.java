package org.example.ProducerConsumerProblemWordSearch;

import org.example.ProducerConsumerProblemWordSearch.WordSearch.WordOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class WordSearchWithProducerConsumer {
    public static void main(String[] args) throws InterruptedException {

        File folder = new File("/home/karthikr_700073/Downloads/Karthik");
        String word = "the";

        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        List<String> fileList = new ArrayList<>();
        List<List<WordOutput>> ans = new ArrayList<>();
        List<WordOutput> locationOfWord = new ArrayList<>();

        Producer producer = new Producer(folder, queue, fileList);
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.submit(producer);

        for (int i = 0; i < 9; i++) {
            service.submit(new Consumer(word, queue, ans, locationOfWord));
        }

        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("List of all files: " + fileList);

        for (List<WordOutput> occurrences : ans) {
            for (WordOutput output : occurrences) {
                System.out.println(output.getAbsolutePath() + " " + output.getOccurred());
            }
        }
        System.out.println();
        for (WordOutput list : locationOfWord) {
            Map<String, List<WordOutput>> finalOutput = list.getFinalOutput();
            for (Map.Entry<String, List<WordOutput>> map : finalOutput.entrySet()) {
                System.out.println(map.getKey());
                for (WordOutput locPos : map.getValue()) {
                    System.out.println(locPos.getLine() + " " + locPos.getPos());
                }
                System.out.println();
            }
        }
    }
}
