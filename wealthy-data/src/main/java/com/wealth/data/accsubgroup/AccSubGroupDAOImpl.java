package com.wealth.data.accsubgroup;

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

import com.wealth.common.acctsubgroup.AccSubGroupDAO;
import com.wealth.common.acctsubgroup.AccSubGroupDTO;
import com.wealth.data.GenericDaoImpl;

@Transactional
@Component("accSubGroupDaoImpl")
public class AccSubGroupDAOImpl extends GenericDaoImpl implements AccSubGroupDAO {

	@Autowired
    DozerBeanMapper dozerBeanMapper;
	
	@Override
	public AccSubGroupDTO create(AccSubGroupDTO subGroup) throws SQLException {
		return convertToDTO((AccountSubGroup) super.gravar(convertToDAO(subGroup))) ;
	}
	
	@Override
	public AccSubGroupDTO update(AccSubGroupDTO subGroup) throws SQLException {
		return convertToDTO((AccountSubGroup) super.alterar(convertToDAO(subGroup)));
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<AccSubGroupDTO> searchAll(AccSubGroupDTO subGroup) throws SQLException {
		List<AccSubGroupDTO>  ac = null;
		StringBuilder query = new StringBuilder(20);
		query.append("select acg from AccountSubGroup acg where acg.userId = ").append(subGroup.getUserId());
		try {
			ac = convertToListDTO(getEm().createQuery(query.toString()).getResultList());
		} catch (NoResultException noResultException) {
			throw noResultException;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		return ac;

	}

	@Override
	public void delete(AccSubGroupDTO subGroup) throws SQLException {
		StringBuilder query = new StringBuilder(20);
		query.append("delete AccountSubGroup acg ");
		query.append(" where acg.id = " ).append(subGroup.getId());
		query.append(" and acg.userId = ").append(subGroup.getUserId());
		try {
			 getEm().createQuery(query.toString()).executeUpdate();
		} catch (NoResultException noResultException) {
			throw noResultException;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public AccSubGroupDTO searchById(AccSubGroupDTO subGroup) throws SQLException {
		AccSubGroupDTO ac = null;
		StringBuilder query = new StringBuilder(20);
		query.append("select acg from AccountSubGroup acg ");
		query.append(" where acg.id = " ).append(subGroup.getId());
		query.append(" and acg.userId = ").append(subGroup.getUserId());
		try {
			ac = convertToDTO((AccountSubGroup) getEm().createQuery(query.toString()).getSingleResult());
		} catch (NoResultException noResultException) {
			throw noResultException;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		return ac;
	}	
	
	public AccountSubGroup convertToDAO(AccSubGroupDTO subGroupDTO){
		return dozerBeanMapper.map(subGroupDTO, AccountSubGroup.class);
	}
	
	public AccSubGroupDTO convertToDTO(AccountSubGroup subGroup){
		return dozerBeanMapper.map(subGroup, AccSubGroupDTO.class);
	}
	
	public List<AccSubGroupDTO> convertToListDTO(Collection<AccountSubGroup> subGroups){
		List<AccSubGroupDTO> dtos = new ArrayList<AccSubGroupDTO>();
		for(AccountSubGroup asg: subGroups){
			dtos.add(dozerBeanMapper.map(asg, AccSubGroupDTO.class));	
		}	
		return dtos;
	}		
}
