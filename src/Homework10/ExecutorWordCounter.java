package Homework10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: blangel
 * Date: 11/16/14
 * Time: 3:33 PM
 */
public class ExecutorWordCounter extends AbstractConcurrencyFactorProvider implements WordCounter {

	private ExecutorService executorService;
	
    public ExecutorWordCounter(int concurrencyFactor) {
        super(concurrencyFactor);
        executorService = Executors.newFixedThreadPool(concurrencyFactor);
    }

    @Override public void count(String fileContents, String word, Callback callback) {
        // TODO - implement this class using calls to an ExecutorService; one call per {@link #concurrencyFactor}
        // HINT - break up {@linkplain fileContents} and distribute the work across the calls
        // HINT - do not create the ExecutorService object in this method
    	//split the text by line
    	final String[] lineSection = fileContents.split("\n");
    	AtomicLong counter = new AtomicLong(0);
    	for(String strLine:lineSection) {
    		String[] strSection = SplitContent(strLine);
    		executorService.execute(new Runnable() {
				@Override
				public void run() {
					for(String str : strSection) {
						if(str.equals(word)){
								counter.getAndAdd(1);
						}
					}
				}   			
    		});
    	}
    	callback.counted(counter.get());
    }

    @Override public void stop() {
    	executorService.shutdown();
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
    	strContents = strContents.replace("\'", " ");
    	strContents = strContents.replace(":", " ");
    	String[] strSplited = strContents.split("\\s+");
    	return strSplited;
    }
}
