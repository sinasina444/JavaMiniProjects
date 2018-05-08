import java.util.Random;
import java.util.concurrent.Semaphore;

public class HotdogProducer implements Producer {

    private final Semaphore binarySemaphore;

    public HotdogProducer(Semaphore binarySemaphore) {
        this.binarySemaphore = binarySemaphore;
    }

    @Override
    public HotDog produce(Tray onto) {
        try{
            binarySemaphore.acquire();
            HotDog hotdog;
            if(onto.full()) {
                return null;
            }
            Random random = new Random();
            hotdog = new HotDog(new Bun(random.nextBoolean()), new Sausage(random.nextBoolean()), new Condiment(random.nextBoolean()));
            onto.addHotDog(hotdog);
            return hotdog;
        }catch(InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }finally {
            binarySemaphore.release();
        }
    }
}