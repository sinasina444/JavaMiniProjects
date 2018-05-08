

import java.util.Collection;
import java.util.Iterator;

/**
 * User: blangel
 *
 */
public class Tray {

    private static final int DEFAULT_SIZE = 100;

    public final Collection<HotDog> hotDogs;

    // the amount of hot dogs which can be stored on the tray
    private final int size;

    public Tray(Collection<HotDog> hotDogs) {
        this(hotDogs, DEFAULT_SIZE);
    }

    public Tray(Collection<HotDog> hotDogs, int size) {
        this.hotDogs = hotDogs;
        this.size = Math.max(size, hotDogs.size());
    }

    public HotDog getHotDog() {
        Iterator<HotDog> iterator = hotDogs.iterator();
        if (!iterator.hasNext()) {
            throw new UnsupportedOperationException();
        }
        HotDog hotDog = iterator.next();
        iterator.remove();
        return hotDog;
    }

    public boolean isEmpty() {
        return hotDogs.isEmpty();
    }

    public boolean full() {
        return (hotDogs.size() == size);
    }

    public void addHotDog(HotDog hotDog) {
        if (full()) {
            throw new UnsupportedOperationException();
        }
        hotDogs.add(hotDog);
    }
    
    public  int size(){
    	return size;
    }
}
