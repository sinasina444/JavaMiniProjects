package Homework10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;


/**
 * User: blangel
 * Date: 11/16/14
 * Time: 3:50 PM
 */
public class ThreadedFilesWordCounter extends AbstractConcurrencyFactorProvider implements FilesWordCounter {

	private AtomicLong count;
	private Thread[] fileThreads;
	private final int concurrencyFactor;
	private AtomicLong Filecounter;
	private boolean taskFinished;
	
    public ThreadedFilesWordCounter(int concurrencyFactor) {
        super(concurrencyFactor);
        this.concurrencyFactor = concurrencyFactor;
        count.set(0);
        fileThreads = new Thread[concurrencyFactor];
        Filecounter = new AtomicLong(0);
        taskFinished = false;
    }

    @Override public void count(Map<String, String> files, String word, Callback callback) {
        // TODO - implement this class using Thread objects; one Thread per {@link #concurrencyFactor} with each Thread handling at most one file at one time
        // HINT - do not create the ExecutorService object in this method
    	if(files == null || word == null){
    		return;
    	}
    	final int filesize = files.size();
    	final int fileEachThread = filesize/concurrencyFactor+1;
    	List<String> keyList = new ArrayList<>(files.keySet());
    	List<String> valueList = new ArrayList<>(files.values());
    	for(int i = 0;i < concurrencyFactor; i++){
    		int initialIndex = i;
    		fileThreads[i] = new Thread(new Runnable(){
				@Override
				public void run() {
					for(int j = initialIndex * fileEachThread; j < (initialIndex+1) * fileEachThread; j++ ){
						final AtomicLong wordSum = new AtomicLong(0);
						final ThreadedWordCounter threadWordCounter = new ThreadedWordCounter(concurrencyFactor);
						int jvalue = j;
						threadWordCounter.count(valueList.get(j), word, new WordCounter.Callback() {					
							@Override
							public void counted(long count) {
								wordSum.getAndAdd(count);
								callback.counted(keyList.get(jvalue), wordSum.get());
								Filecounter.getAndAdd(1);
							}
						});
					}					
				}    			
    		});
    		fileThreads[i].start();
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
        // TODO - stop the threads
       	for(Thread thread: fileThreads){
    		if(thread != null){
    			thread.interrupt();
    		}
    	}
    }
}
