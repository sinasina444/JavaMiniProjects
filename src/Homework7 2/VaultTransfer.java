package Homework7;

public class VaultTransfer <T>{
	public boolean transfer(Vault<? extends T> vault1, int index1, Vault<? extends T> vault2, int index2){
		if(vault1 == null || vault2 == null){
			return false;
		}
		T element1 = vault1.get(index1);
		T element2 = vault2.get(index2);
		if(element1 == null || element2 == null){
			return false;
		}
		T temp = element1;
		element1 = (T)element2;
		element2 = temp;
		return true;
	}
}
