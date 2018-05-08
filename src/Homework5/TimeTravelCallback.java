package Homework5;

public interface TimeTravelCallback {

    /**
     * Invoked after time traveling is complete.
     * @param unit of time traveled
     * @param amount of time (in {@code unit}) traveled
     * @param ahead true if the time travel went into the future or false if it went into the past
     */
    void leaped(Time unit, int amount, boolean ahead);

}
