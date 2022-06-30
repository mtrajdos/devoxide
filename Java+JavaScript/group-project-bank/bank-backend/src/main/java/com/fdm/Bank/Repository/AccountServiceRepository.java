package com.fdm.Bank.Repository;

public interface AccountServiceRepository<T> {
	
	void save(T t);

}
