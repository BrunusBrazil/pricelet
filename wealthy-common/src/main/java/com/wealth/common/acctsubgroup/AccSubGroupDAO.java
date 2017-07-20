package com.wealth.common.acctsubgroup;

import java.sql.SQLException;
import java.util.List;

public interface AccSubGroupDAO {
	AccSubGroupDTO create(AccSubGroupDTO accSubGroupDTO) throws SQLException;
	AccSubGroupDTO update(AccSubGroupDTO subGroup) throws SQLException;
	List<AccSubGroupDTO> searchAll() throws SQLException;
	void delete(Integer id) throws SQLException;
	AccSubGroupDTO searchById(Integer account) throws SQLException; 
}
