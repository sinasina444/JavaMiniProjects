package Classes;

public class Cats extends Animals{
	Cats(){
		catType = Type.type1;
	}
	Type catType;
	public void makeNoise(){
		System.out.println("Cats shout!");
	}
}
