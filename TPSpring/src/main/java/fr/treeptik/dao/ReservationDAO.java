package fr.treeptik.dao;

import java.util.Date;
import java.util.List;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Client;
import fr.treeptik.model.Reservation;
import fr.treeptik.model.Voiture;

public interface ReservationDAO extends GenericDAO<Reservation, Integer>{
	
	public Long countReservation (Integer id) throws DAOException;
	
	public List<Reservation> findReservationByClient (Integer id) throws DAOException;

}
