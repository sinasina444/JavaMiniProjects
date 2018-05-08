package Homework2;

import java.math.BigDecimal;

public class main {

	public static void main(String[] args) {
		
		//System.out.println(Integer.toBinaryString(~14));
		//System.out.println(Math.pow(2, 4));
		System.out.println(14&0b111);
		
		//System.out.println(Integer.toBinaryString(8140000));
		BigDecimal bd1 = BigDecimal.valueOf(1.234567);
		BigDecimal bd2 = BigDecimal.valueOf(7.654321);
		//System.out.println(bd1.add(bd2));
		AnnuityCalculator ac = new AnnuityCalculator();
		//String str = ac.computeFutureValueOfAnnuity(400000,3.5,20);
		System.out.println(ac.computeFutureValueOfAnnuity(400000,3.5,20));
		
		System.out.println(Integer.toBinaryString(8));
		int i = Integer.valueOf("1010", 2);
		System.out.println(Math.round(-1.4999999));
	
	}

}
