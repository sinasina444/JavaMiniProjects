package Homework6;

import java.util.List;

public class MyTripWire extends TripWire{
	private List<Wire> tripwire;
	MyTripWire(Wire redwire,Wire bluewire,List<Wire> tripwire){
		super(redwire,bluewire);
		this.tripwire = tripwire;
	}
	public List<Wire> getTripwire(){
		return tripwire;
	}
}
