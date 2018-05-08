package Homework6;

import java.util.ArrayList;
import java.util.List;

public class MyBombExpert implements BombSquadTechnician{
	private MyBombExpert() {
	}
	
	private final static MyBombExpert instance = new MyBombExpert();
	public static MyBombExpert getMyBombExpert() {
		return instance;
	}
	
	@Override
	public WireDiagram assess(Bomb bomb) {
		// TODO Auto-generated method stub
		System.out.println("start assessing bomb "+ bomb.getBombNumber());
		Toolbox toolbox = new Toolbox();
		Wire[] bombwires = bomb.getWires();
		int wirenum = bombwires.length;
		List<Wire> tripwires = new ArrayList<Wire>();
		
		try {
			for(int i=0;i<wirenum;i++) {
				WireColor color = toolbox.getColor(bombwires[i]);
				if(color == WireColor.Blue || color == WireColor.Red) {
					tripwires.add(bombwires[i]);
				}
				//try to capture the bad guy!
				Object object = toolbox.invokeMethod(bombwires[i],"callingCard");
				if(object.getClass() != bombwires[i].getClass()) {
					System.out.println("I catch you! The chosen wire number is "+ i);
					EvilVillain evilguy = (EvilVillain)object;
					toolbox.setField(evilguy, "free", false);
					//System.out.println("catch over");
				}
			}
		}catch(ToolMisuseException e){
			System.out.println("assess bomb wrong! reason:"+e.getMessage());
		}
		try {
			for(Wire wire:tripwires) {
				WireColor color= toolbox.getColor(wire);
				System.out.println(color);
			}
		}catch(ToolMisuseException e) {
			System.out.println("assess bomb wrong! reason:"+e.getMessage());
		}
		TripWire falsetripwire = new TripWire(tripwires.get(0),tripwires.get(0));
		MyTripWire mytripwire = new MyTripWire(tripwires.get(0),tripwires.get(0),tripwires);
		MyWireDiagram mywirediagram = new MyWireDiagram(falsetripwire,mytripwire);
		return mywirediagram;
	}

	@Override
	public void defuse(Bomb bomb, WireDiagram diagram) {
		// TODO Auto-generated method stub
		System.out.println("start defuse bomb "+ bomb.getBombNumber());
		Toolbox toolbox = new Toolbox();
		MyWireDiagram mywirediagram = (MyWireDiagram)diagram;
		MyTripWire tripwires = mywirediagram.getMyTripWire();
		List<Wire> wirelist = tripwires.getTripwire();
		int size = wirelist.size();
		System.out.println("size="+size);
		System.out.println("start cutting bomb "+bomb.getBombNumber());
		try {
			for(int i=0;i<size;i++) {
				Wire wire = wirelist.get(0);
				wirelist.remove(0);
				System.out.println("cutting bomb "+bomb.getBombNumber()+",wirecolor: "+ toolbox.getColor(wire));
				wire.cut();
			}
			}catch(ToolMisuseException e){
				System.out.println("assess bomb wrong! reason:"+e.getMessage());
		}
	}
}
