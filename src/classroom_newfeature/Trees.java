package classroom_newfeature;

public interface Trees {
	String getName();
	
	default void printDescription(){
		System.out.printf("%s%n", getName());
	}
}
