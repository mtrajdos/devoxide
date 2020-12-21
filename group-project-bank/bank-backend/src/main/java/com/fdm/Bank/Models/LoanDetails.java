package com.fdm.Bank.Models;

import java.math.BigDecimal;
import java.util.Objects;

public class LoanDetails {

	BigDecimal yearlyWage;
	BigDecimal loan;
	ContractType contractType;
	String loanAffordable;
	BigDecimal calculatedLoanAmount;
	Boolean netWage;

	public LoanDetails(BigDecimal yearlyWage, BigDecimal loan, ContractType contractType, String loanAffordable, BigDecimal calculatedLoanAmount, Boolean netWage) {
		this.yearlyWage = yearlyWage;
		this.loan = loan;
		this.contractType = contractType;
		this.loanAffordable = loanAffordable;
		this.calculatedLoanAmount = calculatedLoanAmount;
		this.netWage = netWage;
	}

	public BigDecimal getYearlyWage() {
		return yearlyWage;
	}

	public void setYearlyWage(BigDecimal yearlyWage) {
		this.yearlyWage = yearlyWage;
	}

	public BigDecimal getLoan() {
		return loan;
	}

	public void setLoan(BigDecimal loan) {
		this.loan = loan;
	}

	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}

	public String getLoanAffordable() {
		return loanAffordable;
	}

	public void setLoanAffordable(String loanAffordable) {
		this.loanAffordable = loanAffordable;
	}

	public BigDecimal getCalculatedLoanAmount() {
		return calculatedLoanAmount;
	}

	public void setCalculatedLoanAmount(BigDecimal calculatedLoanAmount) {
		this.calculatedLoanAmount = calculatedLoanAmount;
	}

	public Boolean getNetWage() {
		return netWage;
	}

	public void setNetWage(Boolean netWage) {
		netWage = netWage;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LoanDetails that = (LoanDetails) o;
		return Objects.equals(yearlyWage, that.yearlyWage) &&
				Objects.equals(loan, that.loan) &&
				contractType == that.contractType &&
				Objects.equals(loanAffordable, that.loanAffordable) &&
				Objects.equals(calculatedLoanAmount, that.calculatedLoanAmount) &&
				Objects.equals(netWage, that.netWage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(yearlyWage, loan, contractType, loanAffordable, calculatedLoanAmount, netWage);
	}

	@Override
	public String toString() {
		return "LoanDetails{" +
				"yearlyWage=" + yearlyWage +
				", loan=" + loan +
				", contractType=" + contractType +
				", loanAffordable='" + loanAffordable + '\'' +
				", calculatedLoanAmount=" + calculatedLoanAmount +
				", isNetWage=" + netWage +
				'}';
	}
}
