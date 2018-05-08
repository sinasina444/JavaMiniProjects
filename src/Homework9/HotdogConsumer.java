package Homework9;

import java.util.concurrent.Semaphore;

public class HotdogConsumer implements Consumer {

    private final Semaphore binarySemaphore;

    public HotdogConsumer(Semaphore binarySemaphore) {
        this.binarySemaphore = binarySemaphore;
    }

    @Override
    public HotDog consume(Tray from) {

    	try{
    		binarySemaphore.acquire();
    		try {
    			//TODO
    	    	if(from == null || from.isEmpty()) {
    	            return null;
    	        }
    			HotDog hotdog;
    			hotdog = from.getHotDog();
    			return hotdog;
    		}finally {
    			binarySemaphore.release();
    		}
    	} catch (InterruptedException ie) {
    			Thread.currentThread().interrupt();
    			throw new RuntimeException(ie);
    	} 	
    }
}
