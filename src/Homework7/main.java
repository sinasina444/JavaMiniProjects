package Homework7;

import java.math.BigDecimal;

public class main {

	public static void main(String[] args) {
		MoneyArrayCreator moneycreator = new MoneyArrayCreator();
		DollarArrayCreator dollarcreator = new DollarArrayCreator();
		Vault<Money> vault1 = new Vault<>(5,moneycreator);
		BankVault<Money> bankvault1 = new BankVault<>(3,moneycreator);
		BankVault<Dollar> bankvault2 = new BankVault<>(5,dollarcreator);
		Money money1 = new Money(new BigDecimal(10.0));
		Money money2 = new Money(new BigDecimal(15.0));
		Dollar dollar1 = new Dollar(new BigDecimal(20.0));
		Dollar dollar2 = new Dollar(new BigDecimal(25.0));
		Dollar dollar3 = new Dollar(new BigDecimal(30.0));
		Dollar dollar4 = new Dollar(new BigDecimal(35.0));
		vault1.add(money1);
		vault1.add(money2);
		vault1.add(dollar1);
		vault1.add(dollar2);
		bankvault1.add(dollar1);
		bankvault1.add(dollar2);
		bankvault2.add(dollar3);
		bankvault2.add(dollar4);
		
		//System.out.println(bankvault1.get(0).getAmount());
		VaultTransfer<Money> trans = new VaultTransfer<>();
		trans.transfer(vault1, 1, bankvault1);
		System.out.println(bankvault1.get(2).getAmount());
		
		//System.out.println(bankvault1.get(1).getAmount());
		//System.out.println(vault1.get(1).getAmount());
	}

}
