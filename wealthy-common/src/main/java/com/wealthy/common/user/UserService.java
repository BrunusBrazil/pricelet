package com.wealthy.common.user;

import java.util.List;

import com.wealth.common.exception.BusinessException;

public interface UserService {
	
	UserDTO merge(UserDTO accSubGroupDTO) throws BusinessException;
	
	List<UserDTO> searchAll() throws BusinessException;

	List<UserDTO> delete(Integer id) throws BusinessException;

	UserDTO searchByEmail(String email) throws BusinessException;
}
