
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
            HotDog hotdog;
            if(from.isEmpty()) {
                return null;
            }
            hotdog = from.getHotDog();
            return hotdog;
        }catch(InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }finally {
            binarySemaphore.release();
        }
    }
}