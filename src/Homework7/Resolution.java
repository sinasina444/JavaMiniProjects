package Homework7;

public class Resolution {
	
}
/*
public class Vault<T>{
	private static final int DEFAULT_ARRAY_LENGTH=10;

	private final AtomicReference<T[]> atomic;
	private final ArrayCreator<T> arrayCreator;
	private final AtomicInteger index;
	public Vault(ArrayCreator<T> arrayCreator){
		if(arrayCreator ==null){
		throw new ....
		}
		this.array = new AtomicReference<T[]>(null);
		
	}
	
	public boolean add(T add){
		if(add==null){
			throw new
		}
		if(isFull()){
			resize();
		}
		array[index++]=add;
	}
	
	private boolean isFull(){
		return index== array.length;
	}
	
	private void resize(){
	//需要检查最小界和最大界
	 * long newLength = (array.length*2L);
	 * if(newLength > (long)Integer.MAX_VALUE){
	 * }
	 */
		int newLength = (array.length*2);
		T[] newArray = arrayCreator.create(newLength);
		//重要//source--startposition--desitination--startposition---length;
		System.arraycopy(array,0,newArray,0,array.length);
		array = newArray;
	}
}

//TIme-----------------
//Thread A: add#1(first) add#4 (race condition, some time CPU yield 转换别的资源）
//Thread B: add#1 add#4(第4行） add#7


public class BankVault extends Vault<Money>{
	public 
}
*/