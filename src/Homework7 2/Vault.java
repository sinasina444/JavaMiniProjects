package Homework7;

public class Vault <T>{
	private T[] array;
	private int arrSize;
	private final ArrayCreator<T> arraycreator;
	
	@SuppressWarnings("unchecked")
	public Vault(int size,ArrayCreator<? extends T> arraycreator){
		this.arraycreator = (ArrayCreator<T>) arraycreator;
		arrSize = 0;
		this.array = arraycreator.create(size);
	}
	
	public boolean add(T element){
		if(element == null){
			return false;
		}
		if(this.contains(element)){
			return false;
		}
		//add the element
		if(arrSize<array.length){
			array[arrSize] = element;
		}else{
			//current array is full, need to create another array
			//and copy the current array to new one
			//VaultArrayCreator<T> vaultcreator = new VaultArrayCreator();
			T[] temparray = this.array;
			array = arraycreator.create(arrSize+1);
			for(int i = 0;i<array.length;i++){
				array[i] = temparray[i];
			}
			array[arrSize] = element;
		}
		arrSize++;
		return true;
	}
	
	public boolean contains(T element){
		if(element == null || array == null || array.length == 0){
			return false;
		}
		for(T temp:array){
			if(element.equals(temp)){
				return true;
			}
		}
		return false;
	}
	
	public boolean remove(T element){
		if(element == null || array == null || array.length == 0){
			return false;
		}
		if(!this.contains(element)){
			return false;
		}
		//remove the element
		int len = array.length;
		for(int i=0;i<len;i++){
			if(array[i].equals(element)){
				//remove this element by replacing it with the element after it one by one,
				//and delete the last element in the array
				for(int j=i;j<len-1;j++){
					array[j] = array[j+1];
				}
				array[len-1] = null;
				break;
			}
		}
		arrSize--;
		return true;
	}
	
	public T get(int index){
		if(index >= array.length || index<0){
			return null;
		}
		return array[index];
	}
}
