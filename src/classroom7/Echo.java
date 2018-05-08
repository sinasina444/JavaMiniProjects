package classroom7;

public class Echo<T> {
	public T echo(T value) {
		return value;
	}
	
	public Echo<T> echo(Echo<T> value) {
		return value;
	}
	
	//public static T reference;
}
