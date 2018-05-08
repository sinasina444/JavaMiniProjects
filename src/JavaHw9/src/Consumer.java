

/**
 * User: blangel
 *
 */
public interface Consumer {

    /**
     * Removes a {@link Tray} from {@code from} only if there's hot dogs on it.
     *
     * @param from to extract a {@link HotDog} if any
     * @return the extracted {@link HotDog} or null if none available to be extracted
     */
    HotDog consume(Tray from);

}
