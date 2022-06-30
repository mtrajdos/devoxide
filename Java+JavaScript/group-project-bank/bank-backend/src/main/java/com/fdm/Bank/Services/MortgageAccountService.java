package com.fdm.Bank.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.Bank.Models.MortgageAccount;
import com.fdm.Bank.Models.SavingsAccount;
import com.fdm.Bank.Models.User;
import com.fdm.Bank.Repository.AccountServiceRepository;
import com.fdm.Bank.Repository.MortgageAccountDao;

@Service
public class MortgageAccountService implements AccountServiceRepository<MortgageAccount>{
	
	@Autowired
	MortgageAccountDao mortgageAccountDao;
	
	public void save(MortgageAccount mortgageAccount) {
		mortgageAccountDao.save(mortgageAccount);
	}
	
	public void addAccount(MortgageAccount mortgageAccount, Optional<User> user) {
		user.get().getUserAccount().add(mortgageAccount);
	}

}
