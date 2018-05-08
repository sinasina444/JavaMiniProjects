package classroom_newfeature;

import java.util.List;

public class Suburban implements Suv,Car {

	private  String foo;
	
	public void addRunnable(List<Runnable> runnables){
		runnables.add(new Runnable(){
			private final String foo = "sss";
			@Override
			public void run(){
				//打印里面和外面的foo
				System.out.printf("%s and %s%n", this.foo, Suburban.this.foo);
			}
		});
	}
	
	@Override
	public Integer getNumberOfDoors() {
		// TODO Auto-generated method stub
		return Suv.super.getNumberOfDoors();	//因为这里的方法属于Suv的父类，而不是Suv自己的
		//return 6;	//这样写没有invoke Suv的方法
		//return Suv.this.getNumberOfDoors(); //No enclosing instance of the type Suv is accessable in scope报错
	}
	
}
