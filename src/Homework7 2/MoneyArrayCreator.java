package Homework7;

public class MoneyArrayCreator implements ArrayCreator<Money>{

	@Override
	public Money[] create(int size) {
		Money[] money = new Money[size];
		return money;
	}
	
}
