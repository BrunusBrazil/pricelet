package com.wealthy.common.user;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

	UserDTO create(UserDTO user) throws SQLException;

	UserDTO update(UserDTO user) throws SQLException;

	List<UserDTO> searchAll() throws SQLException;

	void delete(Integer id) throws SQLException;

	UserDTO searchByEmail(String email) throws SQLException;

}
