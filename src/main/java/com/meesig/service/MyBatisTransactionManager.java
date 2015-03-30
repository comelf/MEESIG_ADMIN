package com.meesig.service;

import org.apache.ibatis.transaction.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
@Scope("prototype")
public class MyBatisTransactionManager extends DefaultTransactionDefinition {
	
	private static final long serialVersionUID = -4992740408864033767L;

	@Autowired
	PlatformTransactionManager transactionManager;
	
	TransactionStatus status;
	
	public void start() throws TransactionException {
		status = transactionManager.getTransaction(this);
	}
	
	public void commit() throws TransactionException {
		if(!status.isCompleted()){
			transactionManager.commit(status);
		}
	}
	
	public void rollback() throws TransactionException {
		if(!status.isCompleted()){
			transactionManager.rollback(status);
		}
	}
	
	public void end() throws TransactionException {
		rollback();
	}
	
}
