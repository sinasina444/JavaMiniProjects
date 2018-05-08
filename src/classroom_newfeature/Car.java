package classroom_newfeature;

public interface Car {
	default Integer getNumberOfDoors(){
		return 4;
	}
}
