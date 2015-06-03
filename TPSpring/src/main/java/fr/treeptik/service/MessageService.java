package fr.treeptik.service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.MessageDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Message;

@Service
public class MessageService {
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Transactional
	public Message save(Message message) throws ServiceException {
		try {
			return messageDAO.save(message);
		} catch (DAOException e) {
			throw new ServiceException("Erreur dans save de MessageService ", e);
		}
	}
}
