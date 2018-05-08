package Homework10;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: blangel
 * Date: 11/16/14
 * Time: 3:50 PM
 */
public class ExecutorFilesWordCounter extends AbstractConcurrencyFactorProvider implements FilesWordCounter {
	private AtomicLong Filecounter;
	private ExecutorService executorService;
	private final int concurrencyFactor;
	private boolean taskFinished;
	
    public ExecutorFilesWordCounter(int concurrencyFactor) {
        super(concurrencyFactor);
        this.concurrencyFactor = concurrencyFactor;
        executorService = Executors.newFixedThreadPool(concurrencyFactor);
        Filecounter = new AtomicLong(0);
        taskFinished = false;
    }

    @Override public void count(Map<String, String> files, String word, Callback callback) {
        // TODO - implement this class using calls to an ExecutorService; with one call per {@linkplain concurrencyFactor}.
        // HINT - do not create the ExecutorService object in this method
    	if(files == null || word == null) {
    		return;
    	}
    	Iterator fileIter = files.entrySet().iterator();
    	while(fileIter.hasNext()) {
    		AtomicLong wordSum = new AtomicLong(0);
    		Entry<String,String> fileEntry = (Map.Entry)fileIter.next();
    		executorService.execute(new Runnable()  {
				@Override
				public void run() {
					ExecutorWordCounter wordCounter = new ExecutorWordCounter(concurrencyFactor);
					wordCounter.count(fileEntry.getValue(), word, new WordCounter.Callback() {					
						@Override
						public void counted(long count) {
							wordSum.addAndGet(count);
							callback.counted(fileEntry.getKey(), wordSum.get());
							Filecounter.getAndAdd(1);
						}
					});
				}
    		});
    	}
    	while(Filecounter.get() < files.size()){
    		try{
    			Thread.sleep(200L);
    		}catch(InterruptedException exc){
    			break;
    		}
    	}
    	stop();
    	taskFinished = true;
    }
    
    public boolean getTaskFinished(){
    	return taskFinished;
    }

    @Override public void stop() {
    	executorService.shutdown();
    }

}
