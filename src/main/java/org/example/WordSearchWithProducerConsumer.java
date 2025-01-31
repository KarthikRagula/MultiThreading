package org.example;

import org.example.WordSearch.WordInput;
import org.example.WordSearch.WordLinePosAndOccurrences;
import org.example.WordSearch.WordOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class WordSearchWithProducerConsumer {

    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private static List<String> fileList = Collections.synchronizedList(new ArrayList<>());
    private static List<List<WordOutput>> ans = Collections.synchronizedList(new ArrayList<>());
    private static List<WordOutput> locationOfWord=Collections.synchronizedList(new ArrayList<>());

    static class Producer extends Thread {
        private File folder;

        public Producer(File folder) {
            this.folder = folder;
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
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer extends Thread {
        private String word;

        public Consumer(String word) {
            this.word = word;
        }

        @Override
        public void run() {
            try {
                WordLinePosAndOccurrences wordSearch = new WordLinePosAndOccurrences();
                while (true) {
                    String filePath = queue.poll(1,TimeUnit.SECONDS);
                    if (filePath==null) {
                        break;
                    }
                    WordInput input = new WordInput(filePath, word);
                    ans.add(wordSearch.getOccurrences(input));
                    locationOfWord.add(wordSearch.getLinesAndPostionsOfWord(input));
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        File folder = new File("/home/karthikr_700073/Downloads/Karthik/temp.txt");
        String word = "the";

        ExecutorService service= Executors.newFixedThreadPool(10);
        service.submit(new Producer(folder));

        for(int i=0;i<9;i++){
            service.submit(new Consumer(word));
        }

        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);

        System.out.println("List of all files \n"+fileList);

        System.out.println();
        for (List<WordOutput> occr : ans) {
            for (WordOutput output : occr) {
                System.out.println(output.getAbsolutePath() + " " + output.getOccurred());
            }
        }

        System.out.println();
        for(WordOutput list : locationOfWord){
            Map<String, List<WordOutput>> finalOutput = list.getFinalOutput();
            for(Map.Entry<String, List<WordOutput>> map: finalOutput.entrySet()) {
                System.out.println(map.getKey());
                for(WordOutput locPos : map.getValue()) {
                    System.out.println(locPos.getLine()+" "+locPos.getPos());
                }
                System.out.println();
            }
        }
    }
}
