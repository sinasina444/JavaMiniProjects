package Homework10;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * User: blangel
 * Date: 11/16/14
 * Time: 3:03 PM
 */
public class Counter {

    private static final String USAGE =
            "Usage: Counter x y z where x is either thread or executor, y is the concurrency-factor and z is the word to count";

    public static void main(String[] args) {
        if ((args == null) || (args.length != 3) ||
                (!"thread".equals(args[0]) && !"executor".equals(args[0]))) {
            System.out.printf("%s%n", USAGE);
            return;
        }
        int concurrencyFactor;
        try {
            concurrencyFactor = Integer.parseInt(args[1]);
        } catch (NumberFormatException nfe) {
            System.out.printf("%s%n", USAGE);
            return;
        }
        FilesWordCounter wordCounter;
        if ("thread".equals(args[0])) {
            wordCounter = new ThreadedFilesWordCounter(concurrencyFactor);
        } else {
            wordCounter = new ExecutorFilesWordCounter(concurrencyFactor);
        }
        String[] files = new String[] {
                "src/main/resources/Age of Innocence.txt",
                "src/main/resources/Alice's Adventure in Wonderland.txt",
                "src/main/resources/Count of Monte Cristo.txt",
                "src/main/resources/Don Quixote.txt",
                "src/main/resources/Moby Dick.txt",
                "src/main/resources/Pride and Prejudice.txt",
                "src/main/resources/The Hound of the Baskervilles.txt",
                "src/main/resources/The Jungle Book.txt",
                "src/main/resources/The Scarlet Letter.txt",
                "src/main/resources/The Turn of the Screw.txt"
        };
        Map<String, String> fileMap = createFileMap(files);
        Counter counter = new Counter(fileMap, wordCounter);
        counter.count(args[2]);
    }

    private static Map<String, String> createFileMap(String[] files) {
        Map<String, String> mapping = new HashMap<>(files.length);
        for (String file : files) {
            Path path = FileSystems.getDefault().getPath(file);
            try {
                byte[] bytes = Files.readAllBytes(path);
                mapping.put(file.substring(19), new String(bytes, "UTF-8"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return mapping;
    }

    /**
     * Mapping of file name to file contents
     */
    private final Map<String, String> filesToCount;

    private final FilesWordCounter counter;

    public Counter(Map<String, String> filesToCount, FilesWordCounter counter) {
        this.filesToCount = (filesToCount == null ? Collections.<String, String>emptyMap() : new ConcurrentHashMap<>(filesToCount));
        this.counter = counter;
    }

    /**
     * @param word to count amongst all {@linkplain #filesToCount}
     */
    public void count(final String word) {
        counter.count(filesToCount, word, new FilesWordCounter.Callback() {
            @Override public void counted(String fileName, long count) {
                System.out.printf("Word %s appears %d times within %s%n", word, count, fileName);
            }
        });
        // TODO - fix me - need to ensure that this method doesn't return until all files have had all words counted
        ExecutorFilesWordCounter mycounter = (ExecutorFilesWordCounter)counter;
        while(!mycounter.getTaskFinished()){
    		try{
    			Thread.sleep(200L);
    		}catch(InterruptedException exc){
    			break;
    		}
    	}
        counter.stop();
        System.out.println("All files have been checked!");
    }

}
