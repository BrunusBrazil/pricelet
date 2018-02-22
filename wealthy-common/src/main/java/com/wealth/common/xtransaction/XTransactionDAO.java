package com.wealth.common.xtransaction;

import java.sql.SQLException;
import java.util.List;

public interface XTransactionDAO {
	XTransactionDTO create(XTransactionDTO transactionDTO) throws SQLException;
	XTransactionDTO update(XTransactionDTO transactionDTO) throws SQLException;
	List<XTransactionDTO> searchAll(XTransactionDTO transactionDTO) throws SQLException;
	void delete(XTransactionDTO transactionDTO) throws SQLException;
	XTransactionDTO searchById(XTransactionDTO transactionDTO) throws SQLException; 
	
}
