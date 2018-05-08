package Homework7;

import java.math.BigDecimal;

public class main {

	public static void main(String[] args) {
		MoneyArrayCreator moneycreator = new MoneyArrayCreator();
		DollarArrayCreator dollarcreator = new DollarArrayCreator();
		Vault<Money> vault1 = new Vault<>(5,moneycreator);
		BankVault<Money> bankvault = new BankVault<>(3,moneycreator);
		Money money1 = new Money(new BigDecimal(10.0));
		Money money2 = new Money(new BigDecimal(15.0));
		Dollar dollar1 = new Dollar(new BigDecimal(20.0));
		Dollar dollar2 = new Dollar(new BigDecimal(25.0));
		vault1.add(money1);
		vault1.add(money2);
		vault1.add(dollar1);
		vault1.add(dollar2);
		bankvault.add(dollar1);
		bankvault.add();
		

		System.out.println(bankvault.get(0).getAmount());
		VaultTransfer<Money> trans = new VaultTransfer<>();
		
	}

}
