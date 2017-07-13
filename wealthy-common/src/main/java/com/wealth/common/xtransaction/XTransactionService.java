package com.wealth.common.xtransaction;

import java.util.List;
import com.wealth.common.exception.BusinessException;

public interface XTransactionService {
	List<XTransactionDTO> searchAll() throws BusinessException;
	XTransactionDTO merge(XTransactionDTO dto) throws BusinessException;
	XTransactionDTO update(XTransactionDTO subGroup) throws BusinessException;
	List<XTransactionDTO> delete(XTransactionDTO dto) throws BusinessException;
	XTransactionDTO searchById(Integer id)throws BusinessException;	

}
