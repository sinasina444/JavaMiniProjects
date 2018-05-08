package Homework7;

public class BankVault <T extends Money> extends Vault<T>{

	public BankVault(int size,ArrayCreator<T> arraycreator) {
		super(size,arraycreator);
	}
}
