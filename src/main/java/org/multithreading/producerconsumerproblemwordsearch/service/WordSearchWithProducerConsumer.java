package org.multithreading.producerconsumerproblemwordsearch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.multithreading.producerconsumerproblemwordsearch.models.*;

import org.multithreading.producerconsumerproblemwordsearch.models.Producer;
import org.multithreading.producerconsumerproblemwordsearch.models.WordLineNumberAndPos;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WordSearchWithProducerConsumer {
    private static final Logger logger = LoggerFactory.getLogger(WordSearchWithProducerConsumer.class);

    public static void main(String[] args) throws InterruptedException {

        File folder = new File("/home/karthikr_700073/Downloads/Karthik/");
        String word = "the";
        int[] consumerCounts = {2, 4, 8, 16, 32};
        File outputFile = new File("output.txt");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            for (int numberOfConsumers : consumerCounts) {
                BlockingQueue<String> queue = new LinkedBlockingQueue<>();
                List<String> fileList = new ArrayList<>();
                List<WordSearchResult> locationOfWord = new ArrayList<>();
                long startTime = System.currentTimeMillis();
                Producer producer = new Producer(folder, queue, fileList);
                Thread producerThread = new Thread(producer);
                producerThread.start();
                logger.info("Producer Thread Started");
                writer.write("Producer Thread Started\n");

                Thread[] consumerThreads = new Thread[numberOfConsumers];

                for (int i = 0; i < numberOfConsumers; i++) {
                    consumerThreads[i] = new Thread(new Consumer(word, queue, locationOfWord, producer));
                    consumerThreads[i].start();
                }

                logger.info("Consumer Threads Started");
                writer.write("Consumer Threads Started\n");

                producerThread.join();
                logger.info("Producing the files from the folder is completed");
                writer.write("Producing the files from the folder is completed\n");

                for (Thread thread : consumerThreads) {
                    thread.join();
                }
                logger.info("Consumer threads are done");
                writer.write("Consumer threads are done\n");

                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                logger.info("Execution Time with " + numberOfConsumers + " consumers: " + duration + " ms");
                writer.write("Execution Time with " + numberOfConsumers + " consumers: " + duration + " ms\n");
                Collections.sort(fileList);
                writer.write("\nList of all files:\n");
                for (String file : fileList) {
                    writer.write(file + "\n");
                }

                locationOfWord.sort(Comparator.comparing(result -> result.getListOfAllFilesOutput().isEmpty() ? "" : result.getListOfAllFilesOutput().get(0).getAbsolutePath()));
                writer.write("\nWord Positions:\n");
                for (WordSearchResult list : locationOfWord) {
                    List<WordLineNumberAndPos> finalOutput = list.getListOfAllFilesOutput();
                    for (WordLineNumberAndPos out : finalOutput) {
                        writer.write(out.getLineNumber() + " " + out.getPos() + " " + out.getAbsolutePath() + "\n");
                    }
                    writer.write("\n");
                }
                logger.info("Results successfully written to file: " + outputFile.getAbsolutePath());
            }
            writer.close();
        } catch (IOException e) {
            logger.error("Error writing to file", e);
        }
    }
}
