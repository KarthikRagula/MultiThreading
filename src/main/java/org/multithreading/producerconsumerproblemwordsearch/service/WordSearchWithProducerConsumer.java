package org.multithreading.producerconsumerproblemwordsearch.service;

import org.multithreading.producerconsumerproblemwordsearch.models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class WordSearchWithProducerConsumer {
    public static void main(String[] args) throws InterruptedException {

        File folder = new File("/home/karthikr_700073/Downloads/Karthik");
        String word = "the";

        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        List<String> fileList = new ArrayList<>();
        List<WordOccured> ans = new ArrayList<>();
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

        for (WordOccured occurrences : ans) {
            System.out.println(occurrences.getFile() + " " + occurrences.getOccurred());
        }

        System.out.println();
        for (WordSearchResult list : locationOfWord) {
            List<WordLineNumberAndPos> finalOutput = list.getLineNumberAndPos();
            System.out.println(list.getAbsolutePath());
            for (WordLineNumberAndPos out : finalOutput) {
                System.out.println(out.getLineNumber() + " " + out.getPos());
            }
            System.out.println();
        }
    }
}
