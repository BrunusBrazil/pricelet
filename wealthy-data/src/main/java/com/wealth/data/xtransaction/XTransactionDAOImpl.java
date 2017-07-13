package com.wealth.data.xtransaction;

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

import com.wealth.common.xtransaction.XTransactionDAO;
import com.wealth.common.xtransaction.XTransactionDTO;
import com.wealth.data.GenericDaoImpl;

@Transactional
@Component("xtransactionalDaoImpl")
public class XTransactionDAOImpl extends GenericDaoImpl implements XTransactionDAO {

	@Autowired
    private DozerBeanMapper dozerBeanMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<XTransactionDTO> searchAll() throws SQLException {
		List<XTransactionDTO>  ac = null;
		StringBuilder query = new StringBuilder(20);
		query.append("select x from XTransaction x");
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
	public XTransactionDTO create(XTransactionDTO x) throws SQLException {
		return convertToDTO((XTransaction) super.gravar(convertToDAO(x))) ;
	}

	@Override
	public XTransactionDTO update(XTransactionDTO x) throws SQLException {
		return convertToDTO((XTransaction) super.alterar(convertToDAO(x)));
	}

	@Override
	public void delete(XTransactionDTO x) throws SQLException {
		super.excluir(convertToDAO(x));
	}

	@Override
	public XTransactionDTO searchById(Integer id) throws SQLException {
		XTransactionDTO ac = null;
		StringBuilder query = new StringBuilder(20);
		query.append("select x from XTransaction x ");
		query.append(" where x.id = " ).append(id);
		try {
			ac = convertToDTO((XTransaction) getEm().createQuery(query.toString()).getSingleResult());
		} catch (NoResultException noResultException) {
			throw noResultException;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		return ac;
		
	}

	public List<XTransactionDTO> comvertDAOToDTOList(Collection<XTransaction> list){
		 List<XTransactionDTO> dtoList = new ArrayList<XTransactionDTO>();
		for(XTransaction l:list){
			dtoList.add(dozerBeanMapper.map(l, XTransactionDTO.class));
		}
		return dtoList;
	}	
	
	public XTransaction convertToDAO(XTransactionDTO dto){
		return dozerBeanMapper.map(dto, XTransaction.class);
	}
	
	public XTransactionDTO convertToDTO(XTransaction dao){
		return dozerBeanMapper.map(dao, XTransactionDTO.class);
	}


}
