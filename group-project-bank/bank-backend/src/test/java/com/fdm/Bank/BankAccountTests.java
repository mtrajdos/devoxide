package com.fdm.Bank;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fdm.Bank.Models.CurrentAccount;
import com.fdm.Bank.Models.MortgageAccount;
import com.fdm.Bank.Models.SavingsAccount;
import com.fdm.Bank.Models.User;
import com.fdm.Bank.Services.CurrentAccountService;
import com.fdm.Bank.Services.MortgageAccountService;
import com.fdm.Bank.Services.SavingsAccountService;
import com.fdm.Bank.Services.UserService;

@SpringBootTest
public class BankAccountTests {
	
	@Autowired
	CurrentAccountService currentAccountService;
	
	@Autowired
	SavingsAccountService savingsAccountService;
	
	@Autowired
	MortgageAccountService mortgageAccountService;
	
	@Autowired
	UserService userService;
	
	@Test
	public void testToCreateANewCurrentAccount() {
		
		CurrentAccount account1 = new CurrentAccount("Debit1", new BigDecimal("1000"), new BigDecimal("0.5"), new BigDecimal("400"));
		Optional<User> user = userService.findById(1L);
		currentAccountService.save(account1);
		currentAccountService.addAccount(account1, user);
		Assertions.assertNotNull(account1);
	}
	
	@Test
	public void testToCreateANewSavingAccount() {
		
		SavingsAccount account2 = new SavingsAccount("Savings1", new BigDecimal("100"), new BigDecimal("1.5"), "4 Withdraws");
		Optional<User> user = userService.findById(1L);
		savingsAccountService.save(account2);
		savingsAccountService.addAccount(account2, user);
		Assertions.assertNotNull(account2);
	
	}
	
	@Test
	public void testToCreateANewMortgageAccount() {
		
		MortgageAccount account3 = new MortgageAccount("Mortgage1", new BigDecimal("100000"), new BigDecimal("5"), new BigDecimal("12"), "12th June", new BigDecimal("140.50"));
		Optional<User> user = userService.findById(1L);
		mortgageAccountService.save(account3);
		mortgageAccountService.addAccount(account3, user);
		Assertions.assertNotNull(account3);
	}

}
