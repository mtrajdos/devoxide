package com.fdm.Bank.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdm.Bank.Models.CurrentAccount;
import com.fdm.Bank.Models.SavingsAccount;
import com.fdm.Bank.Models.User;

public interface CurrentAccountDao extends JpaRepository<CurrentAccount, Long>{
	
	

}
