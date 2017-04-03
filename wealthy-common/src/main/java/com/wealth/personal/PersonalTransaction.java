package com.wealth.personal;

import java.sql.Date;

import com.wealth.common.account.Account;
import com.wealth.common.model.TransactionType;



public class PersonalTransaction {

	private Integer id;
	private Account account;
	private TransactionType typeTransaction;
	private Date dateTime;
	private Double value;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public TransactionType getTypeTransaction() {
		return typeTransaction;
	}
	public void setTypeTransaction(TransactionType typeTransaction) {
		this.typeTransaction = typeTransaction;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}

	
	
}
