package com.fdm.Bank.Models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CurrentAccount extends BankAccount{
	
	@Column
	private BigDecimal overdraftAmount;

	public CurrentAccount(String accountName, BigDecimal balance, BigDecimal interestRate, BigDecimal overdraftAmount) {
		super(accountName, balance, interestRate);
		this.overdraftAmount = overdraftAmount;
	}
	
	public CurrentAccount() {
		super();
	}

	public BigDecimal getOverdraftAmount() {
		return overdraftAmount;
	}

	public void setOverdraftAmount(BigDecimal overdraftAmount) {
		this.overdraftAmount = overdraftAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((overdraftAmount == null) ? 0 : overdraftAmount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrentAccount other = (CurrentAccount) obj;
		if (overdraftAmount == null) {
			if (other.overdraftAmount != null)
				return false;
		} else if (!overdraftAmount.equals(other.overdraftAmount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+ "CurrentAccount [overdraftAmount=" + overdraftAmount + "]";
	}
	
	
	
	
	
}


	