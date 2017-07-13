package com.wealth.common.acctsubgroup;

import java.util.List;

import com.wealth.common.exception.BusinessException;

public interface AccSubGroupService {
	AccSubGroupDTO merge(AccSubGroupDTO accSubGroupDTO) throws BusinessException;
	AccSubGroupDTO update(AccSubGroupDTO subGroup) throws BusinessException;
	List<AccSubGroupDTO> delete(AccSubGroupDTO account) throws BusinessException;
	List<AccSubGroupDTO> searchAll() throws BusinessException;
	AccSubGroupDTO searchById(Integer account)throws BusinessException;	
}
