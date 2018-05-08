package Homework6;

public class MyWireDiagram extends BombWireDiagram{
	private final MyTripWire mytripwire;
	MyWireDiagram(TripWire tripwire,MyTripWire mytripwire){
		super(true,tripwire);//not used
		this.mytripwire = mytripwire;
	}
	
	public MyTripWire getMyTripWire() {
		return mytripwire;
	}
}
