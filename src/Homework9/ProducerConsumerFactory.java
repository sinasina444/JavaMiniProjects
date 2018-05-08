package Homework9;

import java.util.concurrent.Semaphore;

/**
 * User: blangel
 *
 */
public class ProducerConsumerFactory {

    static final private Semaphore binarySemaphore = new Semaphore(1);

    public static Producer createProducer() {
    	Producer producer = new HotdogProducer(binarySemaphore);
        return producer;
    }

    public static Consumer createConsumer() {
    	Consumer consumer = new HotdogConsumer(binarySemaphore);
        return consumer;
    }

}
