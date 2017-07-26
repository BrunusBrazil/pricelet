package com.wealth.data.xtransaction;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wealth.data.accountgroup.AccountGroup;
import com.wealth.data.accsubgroup.AccountSubGroup;

@Entity
@Table(name="xtransaction")
public class XTransaction {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	private String description;
	
	private BigDecimal valor;
	
	private Boolean entrada;
	
	@Column(name="datatrans")
	private Date dateTransaction;

    @ManyToOne
	@JoinColumn(name="acc_subgroup_id")
	private AccountSubGroup accSubGroup;	

    @ManyToOne
	@JoinColumn(name="acc_group_id")
	private AccountGroup account;

	@Column(name="transaction_type")
	private String type;

	@Column(name="create_date")
	private Date crete;	

	@Column(name="last_update")
	private Date lastUpdate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	
	public AccountSubGroup getAccSubGroup() {
		return accSubGroup;
	}
	public void setAccSubGroup(AccountSubGroup accSubGroup) {
		this.accSubGroup = accSubGroup;
	}
	public AccountGroup getAccount() {
		return account;
	}
	public void setAccount(AccountGroup account) {
		this.account = account;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCrete() {
		return crete;
	}
	public void setCrete(Date crete) {
		this.crete = crete;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}	
}
