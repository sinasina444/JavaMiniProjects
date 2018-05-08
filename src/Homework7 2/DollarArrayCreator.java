package Homework7;

public class DollarArrayCreator implements ArrayCreator<Dollar>{

	@Override
	public Dollar[] create(int size) {
		Dollar[] array = new Dollar[size];
		return array;
	}

}
