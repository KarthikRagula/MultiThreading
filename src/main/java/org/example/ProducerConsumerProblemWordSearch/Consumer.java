package org.example.ProducerConsumerProblemWordSearch;

import org.example.ProducerConsumerProblemWordSearch.WordSearch.WordInput;
import org.example.ProducerConsumerProblemWordSearch.WordSearch.WordLinePosAndOccurrences;
import org.example.ProducerConsumerProblemWordSearch.WordSearch.WordOutput;

import java.util.List;
import java.util.concurrent.BlockingQueue;


public class Consumer implements Runnable {
    private String word;
    private BlockingQueue<String> queue;
    private List<List<WordOutput>> ans;
    private List<WordOutput> locationOfWord;

    public Consumer(String word, BlockingQueue<String> queue, List<List<WordOutput>> ans, List<WordOutput> locationOfWord) {
        this.word = word;
        this.queue = queue;
        this.ans = ans;
        this.locationOfWord = locationOfWord;
    }

    @Override
    public void run() {
        try {
            WordLinePosAndOccurrences wordSearch = new WordLinePosAndOccurrences();
            while (true) {
                String filePath = queue.take();
                if ("STOP".equals(filePath)) {
                    queue.put("STOP");
                    break;
                }
                WordInput input = new WordInput(filePath, word);
                ans.add(wordSearch.getOccurrences(input));
                locationOfWord.add(wordSearch.getLinesAndPostionsOfWord(input));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Consumer interrupted");
        }
    }
}