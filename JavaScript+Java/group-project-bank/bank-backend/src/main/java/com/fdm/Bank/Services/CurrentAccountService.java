package com.fdm.Bank.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.Bank.Models.CurrentAccount;
import com.fdm.Bank.Models.User;
import com.fdm.Bank.Repository.AccountServiceRepository;
import com.fdm.Bank.Repository.CurrentAccountDao;

@Service
public class CurrentAccountService implements AccountServiceRepository<CurrentAccount>{
	
	@Autowired
	CurrentAccountDao currentAccountDao;
	
	public void save(CurrentAccount currentAcount) {
		currentAccountDao.save(currentAcount);
	}
	
	public void addAccount(CurrentAccount currentAccount, Optional<User> user) {
		user.get().getUserAccount().add(currentAccount);
	}

}
