package Homework5;

import java.util.Random;

/**
 * User: blangel
 * Date: 9/21/14
 * Time: 5:36 PM
 */
public class TimeMachine {

    public void travel(TimeTraveler timeTraveler, final TimeTravelCallback callback) {
        if (timeTraveler.getRemainingYearsOfTravel() <= 0d) {
            throw new IllegalArgumentException("Time traveler can no longer travel.");
        }
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                Random random = new Random();
                int amount = random.nextInt(100);
                int unit = random.nextInt(2);
                boolean future = (random.nextInt(2) == 0);
                Time time;
                switch (unit) {
                    case 0:
                        time = Time.Days;
                        break;
                    default:
                        time = Time.Hours;
                }
                int sleep = random.nextInt(10);
                try {
                    Thread.sleep((sleep * 1000L));
                    callback.leaped(time, amount, future);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(ie);
                }
            }
        });
        thread.start();
    }

}

