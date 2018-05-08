package Classes;

import java.sql.Date;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Animals[] animal = new Animals[]{new Dogs(),new Dogs()};
		Dogs[] dogs = new Dogs[]{new Dogs(),new Dogs()};
		animal[0] = new Cats();
		//animal = dogs;
		//animal[0] = new Cats();
		Dogs dog = dogs[0];
		animal[0].makeNoise();
		animal[1].makeNoise();
		System.out.println("-------------------");
		
		
		Creature[] creature = animal;
		creature[0].makeNoise();
		creature[1].makeNoise();
		
		animal[0] = new Dogs();
		//System.out.println(animal[0].getClass());
		creature[0].makeNoise();
		creature[1].makeNoise();
		
		main cbv = new main();
		Date date = new Date(0);
		date.setTime(0L);
		cbv.invoke(date);
		System.out.println(date.getTime());
		String c = "aa";
		cbv.invoke(c);
		System.out.println(c);
	}
	
	public void invoke(Date date){
			date.setTime(1L);
			date = new Date(2L);
	}
		
	public void invoke(String x){
		
	}
}


