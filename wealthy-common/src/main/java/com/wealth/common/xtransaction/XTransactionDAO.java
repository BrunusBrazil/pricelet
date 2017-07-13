package com.wealth.common.xtransaction;

import java.sql.SQLException;
import java.util.List;

public interface XTransactionDAO {
	XTransactionDTO create(XTransactionDTO x) throws SQLException;
	XTransactionDTO update(XTransactionDTO x) throws SQLException;
	List<XTransactionDTO> searchAll() throws SQLException;
	void delete(XTransactionDTO x) throws SQLException;
	XTransactionDTO searchById(Integer account) throws SQLException; 
	
}
