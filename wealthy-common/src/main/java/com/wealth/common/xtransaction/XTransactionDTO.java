package com.wealth.common.xtransaction;

import java.math.BigDecimal;
import java.util.Date;

import com.wealth.common.ModelDTO;
import com.wealth.common.accountgroup.AccountGroupDTO;
import com.wealth.common.acctsubgroup.AccSubGroupDTO;

public class XTransactionDTO extends ModelDTO {

	private String description;
	private BigDecimal valor;
	private Boolean entrada;
	private Date dateTransaction;
    private AccSubGroupDTO accSubGroup;	
	private AccountGroupDTO account;
	private String type;
	private Date create;
	private Date lastUpdate;
	
	//getters and setters
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Boolean getEntrada() {
		return entrada;
	}
	public void setEntrada(Boolean entrada) {
		this.entrada = entrada;
	}
	public Date getDateTransaction() {
		return dateTransaction;
	}
	public void setDateTransaction(Date dateTransaction) {
		this.dateTransaction = dateTransaction;
	}
	public AccSubGroupDTO getAccSubGroup() {
		return accSubGroup;
	}
	public void setAccSubGroup(AccSubGroupDTO accSubGroup) {
		this.accSubGroup = accSubGroup;
	}
	public AccountGroupDTO getAccount() {
		return account;
	}
	public void setAccount(AccountGroupDTO account) {
		this.account = account;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreate() {
		return create;
	}
	public void setCreate(Date create) {
		this.create = create;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}	
}
