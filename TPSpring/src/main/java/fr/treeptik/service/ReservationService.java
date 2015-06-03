package fr.treeptik.service;

import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.ReservationDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Client;
import fr.treeptik.model.Reservation;
import fr.treeptik.model.Voiture;

@Service
public class ReservationService {
	
	private Logger logger = Logger.getLogger(ReservationService.class);
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private VoitureService voitureService;
	@Autowired
	// Mot clé pour injecter un bean dans un autre
	private ReservationDAO reservationDAO;

	@Value("#{config.limitereservation}")
	private Integer limiteReservation;
	
	@Transactional
	// (propagation=Propagation.MANDATORY)//Transaction par défaut
	public Reservation save(Date dateEnregistrement,
							Date dateDePrise,
							Date dateDeRetour,
							Integer total,
							Integer idClient, Integer idVoiture) throws ServiceException {
//		logger.debug("appel de la méthode save client" + reservation.getId());
		
		if(this.isReservervationUnderLimit(idClient)) {

		try {
			Client client = clientService.findById(idClient);
			
			/*****************************************************/

			Voiture voiture = voitureService
					.findById(idVoiture);
			
			/*****************************************************/
			
			Reservation reservation = new Reservation();
			reservation.setDate(dateEnregistrement);
			reservation.setDateDePrise(dateDePrise);
			reservation.setDateDeRetour(dateDeRetour);
			reservation.setTotal(total);
			reservation.setClient(client);
			reservation.setVoiture(voiture);

			reservationDAO.save(reservation);
			
			return reservation;
			
		} catch (DAOException e) {
			throw new ServiceException("Erreur save Reservation Service", e);
		}
		} else {
			System.out.println("La limite de réservation a été atteinte");
			
			return null;
		}
	}
	
	
	
	public List<Reservation> historiqueReservation() throws ServiceException{
		try {
			return reservationDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException("Erreur historiqueReservation Resevation Service", e);
		}
	}
	
	
	
	public boolean isReservervationUnderLimit (Integer id) throws ServiceException{
		
		try {
			Long count = reservationDAO.countReservation(id);
			if(count<limiteReservation)
				return true;
			else
				return false;
		} catch (DAOException e) {
			throw new ServiceException("Erreur save Reservation Service", e);
		}
	}
	
	public List<Reservation> findReservationByClient(Integer id) throws ServiceException{
		try {
			return reservationDAO.findReservationByClient(id);
		} catch (DAOException e) {
			throw new ServiceException("Erreur findReservationByClient dans ReservationService", e);
		}
	}
	
	@Transactional
	public void removeReservation (Reservation res) throws ServiceException{
		try {
			reservationDAO.remove(res);
		} catch (DAOException e) {
			throw new ServiceException("Erreur removeReservation dans ReservationService", e);
		}
	}
	
	@Transactional
	public void removeReservationById (Integer id) throws ServiceException{
		try {
			reservationDAO.removeById(id);
		} catch (DAOException e) {
			throw new ServiceException("Erreur removeReservation dans ReservationService", e);
		}
	}
	
}
