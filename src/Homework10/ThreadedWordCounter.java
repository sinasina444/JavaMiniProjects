package Homework10;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: blangel
 * Date: 11/16/14
 * Time: 3:16 PM
 */
public class ThreadedWordCounter extends AbstractConcurrencyFactorProvider implements WordCounter {

	private AtomicLong count;
	private WordThread[] wordThreads;
	private final int concurrencyFactor;
	
    public ThreadedWordCounter(int concurrencyFactor) {
        super(concurrencyFactor);
        this.concurrencyFactor = concurrencyFactor;
        count.set(0);
        wordThreads = new WordThread[concurrencyFactor];
    }
    
    private class WordThread extends Thread{
    	private String[] strContent;
    	private String word;
    	@Override
    	public void run(){
    		if(strContent == null) {
    			return;
    		}
    		for(String data:strContent) {
    			if(data.equals(word)) {
    				synchronized(this) {
    					count.getAndAdd(1);
    				}
    			}
    		}
    		this.interrupt();
    	}
    	public void setContent(String[] strContent){
    		this.strContent = strContent;
    	}
    	public void setWord(String word){
    		this.word = word;
    	}
    }

    @Override public void count(String fileContents, String word, Callback callback) {
        // TODO - implement this class using Thread objects; one Thread per {@link #concurrencyFactor}
        // HINT - break up {@linkplain fileContents} and distribute the work across the threads
        // HINT - do not create the Thread objects in this method
    	if(fileContents == null || word == null){
    		return;
    	}
    	final String[] lineSection = fileContents.split("\n");
    	final int totalLine = lineSection.length;
    	//need to check the if the last line of each thread exists.
    	final int lineEachThread = totalLine/concurrencyFactor+1;
    	for(int i = 0; i < concurrencyFactor; i++) {
    		for(int j = 0; j < lineEachThread; j++){
    			int indexLine = i*lineEachThread + j;
    			if(indexLine >= totalLine){
    				continue;
    			}
    			String[] words = SplitContent(lineSection[indexLine]);
    			synchronized(this){
    	  			wordThreads[i].setContent(words);
    				wordThreads[i].setWord(word);
    	    		wordThreads[i].start();
    			}
    		}
    	}
    	callback.counted(count.get());
    }
    
    public String[] SplitContent(String fileContents) {
    	String strContents = new String(fileContents);
    	strContents = strContents.replace(","," ");
    	strContents = strContents.replace(".", " ");
    	strContents = strContents.replace("-", " ");
    	strContents = strContents.replace("(", " ");
    	strContents = strContents.replace(")", " ");
    	strContents = strContents.replace("!", " ");
    	strContents = strContents.replace(";", " ");
    	strContents = strContents.replace("\"", " ");
    	strContents = strContents.replace("\n", " ");
    	strContents = strContents.replace("\'", " ");
    	strContents = strContents.replace(":", " ");
    	String[] strSplited = strContents.split("\\s+");
    	return strSplited;
    }

    @Override public void stop() {
        // TODO - stop the threads
    	for(WordThread thread: wordThreads){
    		if(thread != null){
    			thread.interrupt();
    		}
    	}
    }

}
