package com.wealth.common.acctsubgroup;

import java.util.List;

import com.wealth.common.exception.BusinessException;

public interface AccSubGroupService {
	AccSubGroupDTO merge(AccSubGroupDTO accSubGroupDTO) throws BusinessException;
	List<AccSubGroupDTO> delete(AccSubGroupDTO accSubGroupDTO) throws BusinessException;
	List<AccSubGroupDTO> searchAll(AccSubGroupDTO accSubGroupDTO) throws BusinessException;
	AccSubGroupDTO searchById(AccSubGroupDTO accSubGroupDTO)throws BusinessException;	
}
