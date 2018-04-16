package com.wealth.common.accountgroup;

import java.sql.SQLException;
import java.util.List;

public interface AccountGroupDAO {
	AccountGroupDTO create(AccountGroupDTO account)throws SQLException;	
	AccountGroupDTO update(AccountGroupDTO account)throws SQLException;	
	List<AccountGroupDTO> searchAll(AccountGroupDTO account) throws SQLException;
	void delete(AccountGroupDTO account) throws SQLException;
	AccountGroupDTO searchById(AccountGroupDTO account)throws SQLException;	

}
