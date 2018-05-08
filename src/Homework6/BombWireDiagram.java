package Homework6;

/**
 * User: blangel
 * Date: 10/11/17
 * Time: 12:16 PM
 */
public class BombWireDiagram implements WireDiagram {

    private final boolean blueFirst;

    private final TripWire tripWire;

    public BombWireDiagram(boolean blueFirst, TripWire tripWire) {
        this.blueFirst = blueFirst;
        this.tripWire = tripWire;
    }

    @Override public TripWire getTripWires() {
        return tripWire;
    }

    public boolean isBlueFirst() {
        return blueFirst;
    }
}
