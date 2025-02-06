package org.multithreading.producerconsumerproblemwordsearch.service;

import org.multithreading.producerconsumerproblemwordsearch.models.Consumer;
import org.multithreading.producerconsumerproblemwordsearch.models.Producer;
import org.multithreading.producerconsumerproblemwordsearch.models.WordOutput;
import org.multithreading.producerconsumerproblemwordsearch.models.WordSearchResult;

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
        List<WordSearchResult> locationOfWord = new ArrayList<>();

        Producer producer = new Producer(folder, queue, fileList);
        Thread producerThread = new Thread(producer);
        producerThread.start();

        Thread[] consumerThreads = new Thread[9];

        for (int i = 0; i < 9; i++) {
            consumerThreads[i] = new Thread(new Consumer(word, queue, ans, locationOfWord));
            consumerThreads[i].start();
        }
        producerThread.join();

        for (Thread thread : consumerThreads) {
            thread.join();
        }

        System.out.println("List of all files: " + fileList);

        for (List<WordOutput> occurrences : ans) {
            for (WordOutput output : occurrences) {
                System.out.println(output.getAbsolutePath() + " " + output.getOccurred());
            }
        }
        System.out.println();
        for (WordSearchResult list : locationOfWord) {
            List<WordOutput> finalOutput = list.getLineNumberAndPos();
            System.out.println(list.getAbsolutePath());
            for(WordOutput out:finalOutput){
                System.out.print(out.getLineNumber()+" "+out.getPos());
            }
        }
    }
}
