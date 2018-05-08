package classroom_newfeature;

import java.util.List;

//见PPT lecture12 的23页
@FuncionalInterface
public interface Logic{
	boolean meetsCriteria(double average);
}

private List<Student> getStudents(Logic logic){
	List<Student> selected = new ArrayList<>(students.size()/4);
	for(Student student: students){
		double average = getAverage(student);
	}
}


//这个方法每次提供新的logic就可以
public interface StudentTop {
	public List<Student> getTopStudents(){
		return getStudents(new Logic(){
			@Override public boolean meetsCriteria(double average){
				return (average >= Top_GRADE_AVG);
			}
		})
	}
}

//按照新的java8方法实现这个就可以
public LIst<Student> getTopStudents(){
	return getStudents(average -> average >= TOP_GRADE_AVG);
}

public LIst<Student> getTopStudents(){
	return getStudents(average -> average <= LOW_GRADE_AVG);
}