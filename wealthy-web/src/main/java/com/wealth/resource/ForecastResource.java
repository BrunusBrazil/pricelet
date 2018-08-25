package com.wealth.resource;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wealth.common.accountgroup.AccountGroupDTO;
import com.wealth.common.acctsubgroup.AccSubGroupDTO;

public class ForecastResource  extends ResourceSupport{

	private BigDecimal total;
	private Boolean cashin;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date period;
	private AccSubGroupDTO accSubGroup;	
	private AccountGroupDTO account;

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
	public Date getPeriod() {
		return period;
	}
	public void setPeriod(Date period) {
		this.period = period;
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

}
