package com.wealth.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wealth.common.exception.BusinessException;
import com.wealth.common.exception.ErrorDetail;
import com.wealthy.common.user.UserDAO;
import com.wealthy.common.user.UserDTO;
import com.wealthy.common.user.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userDaoImpl")
	private UserDAO dao;

	@Override
	public UserDTO merge(UserDTO userDTO) throws BusinessException {
		UserDTO dto;
		String message = null;

		try {			

			if(dao.searchByUserName(userDTO.getUsername()) != null){
				throw new BusinessException("User already exist");
			}
			
			if(dao.searchByEmail(userDTO.getEmail()) != null){
				throw new BusinessException("Email already exist");
			}
			
			if(userDTO != null && userDTO.getId()== null){
				message = ErrorDetail.DB_DML_INSERT.getDescription();
				dto = dao.create(userDTO);
			}
			else{
				message = ErrorDetail.DB_DML_UPDATE.getDescription();
				dto = dao.update(userDTO);
			}
		}
		catch(PersistenceException e){
			throw new  BusinessException(e);
		}
		catch (SQLException e) {
			throw new  BusinessException(message);
		}
		catch (BusinessException e) {
			throw e;
		}
		catch (Exception e) {
			throw e;
		}
		
		return dto;
	}
	
	@Override
	public List<UserDTO> searchAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> delete(Integer id) throws BusinessException {
		List<UserDTO> list = null;
		try {
			dao.delete(id);
			list = dao.searchAll();
		}catch (SQLException | PersistenceException e) {
				throw new  BusinessException(ErrorDetail.DB_DML_DELETE.getDescription());
		}
		catch (Exception e) {
				throw new  BusinessException(e.getMessage());
		}
		return list;
	}

	@Override
	public UserDTO searchByEmail(String email) throws BusinessException {
		UserDTO dto;
		try {
			dto = dao.searchByEmail(email);
		}catch (SQLException | PersistenceException e) {
				throw new  BusinessException(ErrorDetail.DB_DML_SEARCH.getDescription());
		}
		catch (Exception e) {
				throw new  BusinessException(e.getMessage());
		}
		return dto;	

	}

	@Override
	public UserDTO searchByUserName(String userName) throws BusinessException {
		UserDTO dto;
		try {
			dto = dao.searchByUserName(userName);
		}catch (SQLException | PersistenceException e) {
				throw new  BusinessException(ErrorDetail.DB_DML_SEARCH.getDescription());
		}
		catch (Exception e) {
				throw new  BusinessException(e.getMessage());
		}
		return dto;	

	}

	@Override
	public UserDTO updatePassword(UserDTO userDTO) throws Exception {
		UserDTO dto;
		try {			
			dto = dao.update(userDTO);
		}
		catch(PersistenceException e){
			throw new  BusinessException(e);
		}
		catch (SQLException e) {
			throw new  BusinessException(ErrorDetail.DB_DML_UPDATE.getDescription());
		}
		catch (Exception e) {
			throw e;
		}
		return dto;
	}

	@Override
	public UserDTO resetPassword(UserDTO userDTO) throws Exception {
		UserDTO dto = null;
		try {		
			UserDTO user = searchByEmail(userDTO.getEmail());
			if(user == null)
				throw new BusinessException("Email not found");

			if(userDTO.getPassword().equals(user.getPassword())){
				userDTO.setEmail(userDTO.getNewPassword());
				dto = dao.resetPassword(userDTO);
			}
		}
		catch(PersistenceException e){
			throw new  BusinessException(e);
		}
		catch (SQLException e) {
			throw new  BusinessException(ErrorDetail.DB_DML_UPDATE.getDescription());
		}
		catch (Exception e) {
			throw e;
		}
		return dto;
	}
}
