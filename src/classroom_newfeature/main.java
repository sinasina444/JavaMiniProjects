package classroom_newfeature;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FunctionInterface<?,Integer> imp = (a,b) -> a+b;
		System.out.println(imp.add(3, 5));
		//使用系统提供的functional interface
		//BiFunction<Integer,String,Long> sub = (a,b) -> System.out.println(b + a);
	}

}
