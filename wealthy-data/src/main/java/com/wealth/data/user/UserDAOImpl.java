package com.wealth.data.user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wealth.data.GenericDaoImpl;
import com.wealthy.common.user.UserDAO;
import com.wealthy.common.user.UserDTO;

@Transactional
@Component("userDaoImpl")
public class UserDAOImpl extends GenericDaoImpl implements UserDAO {

	@Autowired
    private DozerBeanMapper dozerBeanMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> searchAll() throws SQLException {
		List<UserDTO>  ac = null;
		StringBuilder query = new StringBuilder(20);
		query.append("select u from User u");
		try {
			ac = comvertDAOToDTOList(getEm().createQuery(query.toString()).getResultList());
		} catch (NoResultException noResultException) {
			throw noResultException;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		return ac;
	}

	@Override
	public UserDTO create(UserDTO x) throws SQLException {
		return convertToDTO((User) super.gravar(convertToDAO(x))) ;
	}

	@Override
	public UserDTO update(UserDTO x) throws SQLException {
		return convertToDTO((User) super.alterar(convertToDAO(x)));
	}

	@Override
	public void delete(Integer id) throws SQLException {
		StringBuilder query = new StringBuilder(20);
		query.append("delete User x ");
		query.append(" where x.id = '"  ).append(id).append("'");
		try {
			 getEm().createQuery(query.toString()).executeUpdate();
		} catch (NoResultException noResultException) {
			throw noResultException;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public UserDTO searchByEmail(String email) throws SQLException {
		UserDTO ac = null;
		StringBuilder query = new StringBuilder(20);
		query.append("select u from User u ");
		query.append(" where u.email = '" ).append(email).append("'");
		try {
			ac = convertToDTO((User) getEm().createQuery(query.toString()).getSingleResult());
		} catch (NoResultException noResultException) {
			throw noResultException;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		return ac;
		
	}

	public List<UserDTO> comvertDAOToDTOList(Collection<User> list){
		 List<UserDTO> dtoList = new ArrayList<>();
		for(User l:list){
			dtoList.add(dozerBeanMapper.map(l, UserDTO.class));
		}
		return dtoList;
	}	
	
	public User convertToDAO(UserDTO dto){
		return dozerBeanMapper.map(dto, User.class);
	}
	
	public UserDTO convertToDTO(User dao){
		return dozerBeanMapper.map(dao, UserDTO.class);
	}

}
