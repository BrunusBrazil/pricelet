package com.wealth.data.personal;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="personal_transaction")
public class PersonalDTO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="account_id")
	private AccountDTO account;
	
	@Column(name="type_trans_id")
	private TransactionTypeDTO typeTransaction;
	
	@Column(name="date_trans")
	private Date dateTime;

	@Column(name="value_trans")
	private Double value;
	
	public AccountDTO getAccount() {
		return account;
	}

	public void setAccount(AccountDTO account) {
		this.account = account;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TransactionTypeDTO getTypeTransaction() {
		return typeTransaction;
	}

	public void setTypeTransaction(TransactionTypeDTO typeTransaction) {
		this.typeTransaction = typeTransaction;
	}

}