package org.multithreading.producerconsumerproblemwordsearch.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.multithreading.producerconsumerproblemwordsearch.service.WordLinePosAndOccurrences;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private String word;
    private BlockingQueue<String> queue;
    private List<WordLineNumberAndPos> locationOfWord;
    private Producer producer;

    public Consumer(String word, BlockingQueue<String> queue, List<WordLineNumberAndPos> locationOfWord, Producer producer) {
        this.word = word;
        this.queue = queue;
        this.locationOfWord = locationOfWord;
        this.producer = producer;
    }

    @Override
    public void run() {
        try {
            WordLinePosAndOccurrences wordSearch = new WordLinePosAndOccurrences();
            while (true) {
                String filePath;
                synchronized (queue) {
                    if (queue.isEmpty() && producer.flag()) {
                        logger.info("queue is empty and flag is true");
                        break;
                    }
                    filePath = queue.poll();
                    if (filePath == null) {
                        continue;
                    }
                }
                WordInput input = new WordInput(filePath, word);
                synchronized (locationOfWord) {
                    locationOfWord.add(wordSearch.getLinesAndPostionsOfWord(input));
                }
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            logger.error("consumer interrupted");
        }
    }
}