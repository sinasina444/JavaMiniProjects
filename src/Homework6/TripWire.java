package Homework6;
/**
 * User: blangel
 * Date: 10/11/17
 * Time: 8:25 AM
 */
public class TripWire {

    private final Wire blueWire;

    private final Wire redWire;

    public TripWire(Wire blueWire, Wire redWire) {
        this.blueWire = blueWire;
        this.redWire = redWire;
    }

    public Wire getBlueWire() {
        return blueWire;
    }

    public Wire getRedWire() {
        return redWire;
    }
}
