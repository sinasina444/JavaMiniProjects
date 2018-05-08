package Homework9;

/**
 * User: blangel
 *
 */
public interface Producer {

    /**
     * Creates a new {@link HotDog} and places it into {@code onto} only if there's currently room on it
     *
     * @param onto to place the new {@link HotDog} if any
     * @return the produced {@link HotDog} or null if none could be placed
     */
    HotDog produce(Tray onto);

}
