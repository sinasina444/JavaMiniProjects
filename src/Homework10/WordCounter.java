package Homework10;

/**
 * User: blangel
 * Date: 11/16/14
 * Time: 3:00 PM
 */
public interface WordCounter {


    static interface Callback {

        void counted(long count);

    }

    /**
     * @param fileContents the contents of a file
     * @param word the word to count
     * @param callback will be invoked with the number of times {@code word} appears in {@code fileContents} irrespective of case (i.e., {@literal The}
     *         is equivalent to {@literal the} with respect to the count returned by this method.
     */
    void count(String fileContents, String word, Callback callback);

    /**
     * Called to indicate that all threads/executors should be shutdown and ended.
     */
    void stop();
}
