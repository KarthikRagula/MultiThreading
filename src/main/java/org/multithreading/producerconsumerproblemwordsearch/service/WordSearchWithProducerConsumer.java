package org.multithreading.producerconsumerproblemwordsearch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.multithreading.producerconsumerproblemwordsearch.models.*;

import org.multithreading.producerconsumerproblemwordsearch.models.Producer;
import org.multithreading.producerconsumerproblemwordsearch.models.WordLineNumberAndPos;
import org.multithreading.producerconsumerproblemwordsearch.models.WordOccured;
import org.multithreading.producerconsumerproblemwordsearch.models.WordSearchResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WordSearchWithProducerConsumer {
    private static final Logger logger = LoggerFactory.getLogger(WordSearchWithProducerConsumer.class);

    public static void main(String[] args) throws InterruptedException {

        File folder = new File("/home/karthikr_700073/Downloads/Karthik");
        String word = "the";
        int numberOfConsumers = 5;

        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        List<String> fileList = new ArrayList<>();
        List<WordOccured> ans = new ArrayList<>();
        List<WordSearchResult> locationOfWord = new ArrayList<>();

        Producer producer = new Producer(folder, queue, fileList);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        logger.info("Producer Thread Started");

        Thread[] consumerThreads = new Thread[numberOfConsumers];

        for (int i = 0; i < numberOfConsumers; i++) {
            consumerThreads[i] = new Thread(new Consumer(word, queue, ans, locationOfWord, producer));
            consumerThreads[i].start();
        }
        logger.info( "consumer Threads Started");
        producerThread.join();
        logger.info( "producing the files from the folder is completed");

        for (Thread thread : consumerThreads) {
            thread.join();
        }
        logger.info( "consumer thereads are done");

        System.out.println("List of all files: " + fileList);

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
