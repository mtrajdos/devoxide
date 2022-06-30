package com.fdm.Bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdm.Bank.Models.SavingsAccount;

public interface SavingsAccountDao extends JpaRepository<SavingsAccount, Long> {

}
