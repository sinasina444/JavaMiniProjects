package Homework4;

public class main {

	public static void main(String[] args) {
		
		//String[] arr = {"EQUATORIAL","AA","BOREAL"};
		//Biome.PrintDescription(arr);
		Tree tree1 = new Magnolia("name1",14);
		Tree tree2 = new Magnolia("name1",14);
		Conifer tree3 = new Magnolia("name2",13);
		System.out.println(tree1.equals(tree2));
		System.out.println(tree2.equals(tree1));
		System.out.println(tree1.hashCode());
		System.out.println(tree2.hashCode());
		System.out.println(tree3.hashCode());
		System.out.println(Math.pow(Math.E, -1.567));
		System.out.println(Math.exp(-1.567));
	}

}
