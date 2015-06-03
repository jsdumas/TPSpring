package fr.treeptik.service;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.VoitureDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Voiture;

@Service
public class VoitureService {
	
	private Logger logger = Logger.getLogger(VoitureService.class);

	@Autowired
	// Mot clé pour injecter un bean dans un autre
	//@Qualifier("voitureJPADAO") Pas la peine de le préciser lorsqu'on a qu'une seule implementation de JPADAO
	// Mot clé pour switcher l'implémentation du DAO
	private VoitureDAO voitureDAO;
	
	
	@Transactional
	// (propagation=Propagation.MANDATORY)//Transaction par défaut
	public Voiture save(Voiture voiture) throws ServiceException {
		logger.debug("appel de la méthode save client" + voiture.getMarque());
		try {
			return voitureDAO.save(voiture);
		} catch (DAOException e) {
			throw new ServiceException("Erreur save Client Service", e);
		}
	}

	@Transactional
	// (propagation=Propagation.MANDATORY)//Transaction par défaut
	public void remove(Voiture voiture) throws ServiceException {
		logger.debug("appel de la méthode save client" + voiture.getMarque());
		try {
			voitureDAO.remove(voiture);
		} catch (DAOException e) {
			throw new ServiceException("Erreur remove Client Service", e);
		}
	}

	@Transactional
	// (propagation=Propagation.MANDATORY)//Transaction par défaut
	public void update(Integer id, String marque, String couleur, String immatriculation, Date dateDeMiseEnCirculation, Integer prix)
			throws ServiceException {

		try {
			Voiture voitureToUpdate = this.findById(id);
			voitureToUpdate.setMarque(marque);
			voitureToUpdate.setCouleur(couleur);
			voitureToUpdate.setImmatriculation(immatriculation);
			voitureToUpdate.setDateDeMiseEnCirculation(dateDeMiseEnCirculation);
			if (voitureToUpdate != null)
				voitureDAO.update(voitureToUpdate);
		} catch (DAOException e) {
			throw new ServiceException("Erreur update Client Service", e);
		}
	}

	@Transactional
	// (propagation=Propagation.MANDATORY)//Transaction par défaut
	public Voiture removeById(Integer id) throws ServiceException {

		logger.debug("appel de la méthode removeById voiture " + id);
		try {
			Voiture voiture = this.findById(id);
			if (voiture != null)
				voitureDAO.removeById(id);
			return voiture;
		} catch (DAOException e) {
			throw new ServiceException("Erreur removeById Client Service", e);
		}
	}

	public Voiture findById(Integer id) throws ServiceException {
		logger.debug("appel de la méthode findById client" + id);
		try {
			return voitureDAO.findById(id);
		} catch (DAOException e) {
			throw new ServiceException("Erreur findById Client Service", e);
		}
	}

	public List<Voiture> findAll() throws ServiceException {

		try {
			return voitureDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException("Erreur findById Client Service", e);
		}
	}
	
	public List<Voiture> findAllFreeCar(Date dateDePrise, Date dateDeRetour) throws ServiceException{
		try {
			return voitureDAO.findAllFreeCar(dateDePrise, dateDeRetour);
		} catch (DAOException e) {
			throw new ServiceException("Erreur findAllFreeCar Client Service", e);
		}
	}
	
	public Integer calculDuPrixLocation(Date dateDePrise, Date dateDeRetour, Integer id) throws ServiceException{
		
		Integer diffInDays = (int) ((dateDeRetour.getTime() - dateDePrise
				.getTime()) / (1000 * 60 * 60 * 24));
		
		try {
			Integer total = diffInDays
					* this.findById(id)
							.getPrix();
			return total;
		} catch (Exception e) {
			throw new ServiceException("Erreur calculDuPrixLocation dans voitureService", e);
		}
	}
	
	public List<Voiture> findByColor(String color) throws ServiceException {
		try {
			return voitureDAO.findByColor(color);
		} catch (DAOException e) {
			throw new ServiceException("Erreur de findByColor dans voitureService", e);
		}
	}
	
	public List<Voiture> findByBrand (String brand) throws ServiceException{
		try {
			return voitureDAO.findByBrand(brand);
		} catch (DAOException e) {
		throw new ServiceException("Erreur de findByBrand dans voitureService", e);
		}
	}
	
	public List<Voiture> findByDate(Date date) throws ServiceException{
		try {
			return voitureDAO.findByDate(date);
		} catch (DAOException e) {
			throw new ServiceException("Erreur de findByDate dans voitureSertvice", e);
		}
	}

}
