package fr.treeptik.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.treeptik.dao.VoitureDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Voiture;
import fr.treeptik.utils.DateUtils;

@Repository
public class VoitureJPADAO extends GenericJPADAO<Voiture, Integer> implements
		VoitureDAO {

	public VoitureJPADAO() {
		super(Voiture.class);
	}

	public List<Voiture> findAllFreeCar(Date dateDePrise, Date dateDeRetour)
			throws DAOException {
		try {
			TypedQuery<Voiture> query = entityManager.createNamedQuery(
					"findAllFreeCar", Voiture.class);
			query.setParameter("dateDePrise", dateDePrise);
			query.setParameter("dateDeRetour", dateDeRetour);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException(
					"Erreur dans findAllFreeCar de VoitureJPADAO", e);
		}
	}

	public List<Voiture> findByColor(String color) throws DAOException {
		try {
			TypedQuery<Voiture> query = entityManager.createNamedQuery(
					"findByColor", Voiture.class);
			query.setParameter("color", color);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException("Erreur de findByColor dans VoitureJPADAO",
					e);
		}
	}

	public List<Voiture> findByBrand(String brand) throws DAOException {
		try {
			TypedQuery<Voiture> query = entityManager.createNamedQuery(
					"findByBrand", Voiture.class);
			query.setParameter("brand", brand);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException("Erreur de findByBrand dans VoitureJPADAO",
					e);
		}
	}

	public List<Voiture> findByDate(Date date) throws DAOException {
		try {
			TypedQuery<Voiture> query = entityManager.createNamedQuery(
					"findByDate", Voiture.class);
			query.setParameter("date", date);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException("Erreur de findByDate dans findByDate", e);
		}
	}
}
