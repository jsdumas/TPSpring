package fr.treeptik.dao.impl;

import org.springframework.stereotype.Repository;

import fr.treeptik.dao.MessageDAO;
import fr.treeptik.model.Message;

@Repository
public class MessageJPADAO extends GenericJPADAO<Message, Integer> implements MessageDAO {

	public MessageJPADAO() {
		super(Message.class);
	}
	
	

}
