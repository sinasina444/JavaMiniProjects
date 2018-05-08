
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: blangel
 *
 */
public class HotDogEatingContest {

    private static final int MAX_INITIAL_HOT_DOGS = 1000;

    public static void main(String[] args) {
        List<HotDog> hotDogs = seed();
        Tray tray = new Tray(hotDogs);
        Producer producer = ProducerConsumerFactory.createProducer();
        Consumer consumer = ProducerConsumerFactory.createConsumer();
        HotDogEatingContest contest = new HotDogEatingContest(tray, producer, consumer);
        contest.start();
    }

    private static List<HotDog> seed() {
        Random random = new Random();
        int capacity = random.nextInt(MAX_INITIAL_HOT_DOGS) + 1;
        List<HotDog> initial = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            initial.add(new HotDog(new Bun(random.nextBoolean()), new Sausage(random.nextBoolean()), new Condiment(random.nextBoolean())));
        }
        return initial;
    }

    private final Tray tray;

    private final Producer producer;

    private final Consumer consumer;

    public HotDogEatingContest(Tray tray, Producer producer, Consumer consumer) {
        this.tray = tray;
        this.producer = producer;
        this.consumer = consumer;
    }

    private void start() {
        Thread producerThread = new Thread(new Runnable() {
            @Override public void run() {
            	System.out.println("producer start running!");
                while (!Thread.currentThread().isInterrupted()) {
                    HotDog hotDog = producer.produce(tray);
                    if (hotDog != null) {
                    	try{
                    		System.out.println("tray size:"+tray.size());
                    		System.out.println("size of tray:"+tray.hotDogs.size());
                    		//Thread.sleep(1);
                    	}catch(Exception exc){
                    		System.out.println("producing thread sleep wrong!");
                    	}
                        System.out.printf("Produced %s%n", hotDog);
                    }
                }
            }
        });
        Thread consumerThread = new Thread(new Runnable() {
            @Override public void run() {
            	System.out.println("consumer start running!");
                while (!Thread.currentThread().isInterrupted()) {
                    HotDog hotDog = consumer.consume(tray);
                    if (hotDog != null) {
                    	try{
                    		System.out.println("tray size:"+tray.size());
                    		System.out.println("size of tray:"+tray.hotDogs.size());
                    		//Thread.sleep(2);
                    	}catch(Exception exc){
                    		System.out.println("consuming thread sleep wrong!");
                    	}
                        System.out.printf("Consumed %s%n", hotDog);
                    }
                }
            }
        });
        producerThread.start();
        consumerThread.start();
        while (producerThread.isAlive() && consumerThread.isAlive()) {
            Thread.yield();
        }
        // failure, orderly shutdown and error message print
        producerThread.interrupt();
        consumerThread.interrupt();
        try {
            Thread.sleep(100L);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }
        System.err.printf("Fail! Producer or Consumer not implemented properly");
        System.exit(1);
    }

}
