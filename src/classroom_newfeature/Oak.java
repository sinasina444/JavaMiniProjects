package classroom_newfeature;

public interface Oak extends Trees{
	/*
	@Override
	default void printDescription(){
		System.out.printf("Oak %s%n",getName());	//redefine it,注意default也要加
	}*/
	
	//redeclare the method ，重新变成抽象
	void printDescription();
}
