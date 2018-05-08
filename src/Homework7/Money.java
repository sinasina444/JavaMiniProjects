package Homework7;

import java.math.BigDecimal;

public class Money {
	
	private final BigDecimal amount;
	
	Money(BigDecimal amount){
		this.amount = amount;
	}
	
	public BigDecimal getAmount(){
		return amount;
	}
	
	@Override
	public boolean equals(Object o){
		if(this == o){
			return true;
		}
		if(o == null || this.getClass()!=o.getClass()){
			return false;
		}
		Money that = (Money)o;
		return (this.getAmount() == null)?(that.getAmount() == null):(this.getAmount().equals(that.getAmount()));
	}
	
	@Override
	public int hashCode(){
		return this.getAmount().hashCode();
	}
}
