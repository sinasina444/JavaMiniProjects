package Homework6;

/**
 * User: blangel
 * Date: 10/11/17
 * Time: 8:05 AM
 *
 * Represents a diagram of the wiring for a {@linkplain Bomb}, specifically giving access to the trip {@linkplain Wire}
 * so that it can be cut to disengage the {@linkplain Bomb}
 */
public interface WireDiagram {

    TripWire getTripWires();

}
