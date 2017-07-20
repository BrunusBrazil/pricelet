package com.wealth.resource;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.wealth.common.accountgroup.AccountGroupDTO;
import com.wealth.common.acctsubgroup.AccSubGroupDTO;

public class XTransactionResource extends ResourceSupport{
	private String description;
	private BigDecimal valor;
	private Boolean entrada;
	private Date dateTransaction;
    private AccSubGroupDTO accSubGroup;	
	private AccountGroupDTO account;
	private String type;
	private Date create;
	private Date lastUpdate;
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
	public Date getCrete() {
		return create;
	}
	public void setCrete(Date create) {
		this.create = create;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
	

}
