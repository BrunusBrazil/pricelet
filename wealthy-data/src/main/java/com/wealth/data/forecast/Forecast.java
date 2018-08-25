package com.wealth.data.forecast;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wealth.data.SuperEntity;
import com.wealth.data.accountgroup.AccountGroup;
import com.wealth.data.accsubgroup.AccountSubGroup;

@Entity
@Table(name="forecast")
public class Forecast extends SuperEntity {
	
	private BigDecimal total;
	
	private Boolean cashin;
	
	private Date period;

    @ManyToOne
	@JoinColumn(name="acc_subgroup_id")
	private AccountSubGroup accSubGroup;	

    @ManyToOne
	@JoinColumn(name="acc_group_id")
	private AccountGroup account;

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Boolean getCashin() {
		return cashin;
	}

	public void setCashin(Boolean cashin) {
		this.cashin = cashin;
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

	public Date getPeriod() {
		return period;
	}

	public void setPeriod(Date period) {
		this.period = period;
	}


	
}
