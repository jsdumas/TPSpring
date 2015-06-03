package fr.treeptik.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.treeptik.dao.GenericDAO;
import fr.treeptik.exception.DAOException;

@Repository
public abstract class GenericJPADAO<T, K> implements GenericDAO<T, K> {

	@PersistenceContext
	protected EntityManager entityManager;
	private Class<T> entity;

	public GenericJPADAO(Class<T> t) {
		this.entity = t;
	}

	public T save(T t) throws DAOException {
		try {
			entityManager.persist(t);
		} catch (PersistenceException e) {
			throw new DAOException("erreur dans methode save de  " + entity.getSimpleName() , e);
		}

		return t;
	}

	public T update(T t) throws DAOException {
		try {
			entityManager.merge(t);
		} catch (PersistenceException e) {
			throw new DAOException("erreur dans methode update de  " + entity.getSimpleName() , e);
		}
		return t;
	}

	public void remove(T t) throws DAOException{
		try {
			entityManager.remove(t);
		} catch (PersistenceException e){
			throw new DAOException("erreur dans methode remove de  " + entity.getSimpleName() , e);
		}
	}

	public void removeById(K id) throws DAOException{
		try{
			entityManager.createNativeQuery(
					"delete from " + entity.getSimpleName() + " where id=" + id)
					.executeUpdate();
		} catch (PersistenceException e){
			throw new DAOException("erreur dans methode removeById de  " + entity.getSimpleName() , e);
		}
	}

	public T findById(K id) throws DAOException {
		try{
			return entityManager.find(entity, id);
		} catch(PersistenceException e){
			throw new DAOException("erreur dans methode findById de  " + entity.getSimpleName() , e);
		}
		
	}

	public List<T> findAll() throws DAOException {
		try{
			TypedQuery<T> query = entityManager.createQuery("select t from "
					+ entity.getSimpleName() + " t", entity);
			return query.getResultList();
		} catch (PersistenceException e){
			throw new DAOException("erreur dans methode findAll de  " + entity.getSimpleName() , e);
		}
	}

}
