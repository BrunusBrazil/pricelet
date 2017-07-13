package com.wealth.common.accountgroup;

import java.util.List;

import com.wealth.common.exception.BusinessException;

public interface AccountGroupService {
	AccountGroupDTO merge(AccountGroupDTO account) throws BusinessException;
	List<AccountGroupDTO> searchAll() throws BusinessException;
	List<AccountGroupDTO> delete(Integer id) throws BusinessException;
	AccountGroupDTO searchById(Integer id) throws BusinessException;
	
	
}
