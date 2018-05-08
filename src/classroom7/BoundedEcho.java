package classroom7;

public class BoundedEcho<T extends Number> {
	public T echo(T value) {
		return value;
	}
	
	public BoundedEcho<T> echo(BoundedEcho<T> value) {
		return value;
	}
}
