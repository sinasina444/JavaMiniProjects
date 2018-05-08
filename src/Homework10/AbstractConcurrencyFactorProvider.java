package Homework10;

/**
 * User: blangel
 * Date: 11/16/14
 * Time: 3:15 PM
 */
abstract class AbstractConcurrencyFactorProvider implements ConcurrencyFactorProvider {

    private final int concurrencyFactor;

    protected AbstractConcurrencyFactorProvider(int concurrencyFactor) {
        this.concurrencyFactor = concurrencyFactor;
    }

    @Override public int getConcurrencyFactor() {
        return concurrencyFactor;
    }

}
