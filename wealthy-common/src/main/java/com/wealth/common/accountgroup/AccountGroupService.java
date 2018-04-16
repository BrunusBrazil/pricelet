package com.wealth.common.accountgroup;

import java.util.List;

import com.wealth.common.exception.BusinessException;

public interface AccountGroupService {
	AccountGroupDTO merge(AccountGroupDTO account) throws BusinessException;
	List<AccountGroupDTO> searchAll(AccountGroupDTO account) throws BusinessException;
	List<AccountGroupDTO> delete(AccountGroupDTO account) throws BusinessException;
	AccountGroupDTO searchById(AccountGroupDTO account) throws BusinessException;
		
}
