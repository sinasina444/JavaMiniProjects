package Homework7;

public class VaultTransfer <T>{
	@SuppressWarnings("unchecked")
	public boolean transfer(Vault<? extends T> vaultFrom, int index, Vault<? super T> vaultTo){
		if(vaultFrom == null || vaultTo == null){
			return false;
		}
		if(index<0 || index >= vaultFrom.getSize()){
			return false;
		}
		T element = vaultFrom.get(index);
		if(element == null){
			return false;
		}
		//exchange the elements	in vault1 into vault2
		((Vault<T>)vaultFrom).remove(element);
		vaultTo.add(element);
		return true;
	}
}
