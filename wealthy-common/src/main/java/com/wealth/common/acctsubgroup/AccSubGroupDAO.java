package com.wealth.common.acctsubgroup;

import java.sql.SQLException;
import java.util.List;

public interface AccSubGroupDAO {
	AccSubGroupDTO create(AccSubGroupDTO accSubGroupDTO) throws SQLException;
	AccSubGroupDTO update(AccSubGroupDTO subGroup) throws SQLException;
	List<AccSubGroupDTO> searchAll(AccSubGroupDTO accSubGroupDTO) throws SQLException;
	void delete(AccSubGroupDTO accSubGroupDTO) throws SQLException;
	AccSubGroupDTO searchById(AccSubGroupDTO accSubGroupDTO) throws SQLException; 
}
