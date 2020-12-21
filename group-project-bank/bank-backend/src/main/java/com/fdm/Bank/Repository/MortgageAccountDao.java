package com.fdm.Bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdm.Bank.Models.MortgageAccount;

public interface MortgageAccountDao extends JpaRepository<MortgageAccount, Long>{

}
