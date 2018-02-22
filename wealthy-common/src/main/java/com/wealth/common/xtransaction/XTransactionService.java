package com.wealth.common.xtransaction;

import java.util.List;

import com.wealth.common.exception.BusinessException;

public interface XTransactionService {
	List<XTransactionDTO> searchAll(XTransactionDTO transactionDTO) throws BusinessException;
	XTransactionDTO merge(XTransactionDTO transactionDTO) throws BusinessException;
	List<XTransactionDTO> delete(XTransactionDTO transactionDTO) throws BusinessException;
	XTransactionDTO searchById(XTransactionDTO transactionDTO)throws BusinessException;	

}
