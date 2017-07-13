package com.wealth.common.accountgroup;

import java.sql.SQLException;
import java.util.List;

public interface AccountGroupDAO {
	AccountGroupDTO create(AccountGroupDTO account)throws SQLException;	
	AccountGroupDTO update(AccountGroupDTO account)throws SQLException;	
	List<AccountGroupDTO> searchAll() throws SQLException;
	void delete(Integer id) throws SQLException;
	AccountGroupDTO searchById(Integer account)throws SQLException;	

}
