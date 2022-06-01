package com.fdm.Bank.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.Bank.Models.SavingsAccount;
import com.fdm.Bank.Models.User;
import com.fdm.Bank.Repository.AccountServiceRepository;
import com.fdm.Bank.Repository.SavingsAccountDao;
	
@Service
public class SavingsAccountService implements AccountServiceRepository<SavingsAccount>{
		
	@Autowired
	SavingsAccountDao savingsAccountDao;
	
		
	public void save(SavingsAccount savingsAccount) {
		savingsAccountDao.save(savingsAccount);
	}
	
	public void addAccount(SavingsAccount savingsAccount, Optional<User> user) {
		user.get().getUserAccount().add(savingsAccount);
	}

}
