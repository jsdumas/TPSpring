package fr.treeptik.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.springframework.beans.factory.annotation.Autowired;

import fr.treeptik.model.Client;
import fr.treeptik.model.Message;
import fr.treeptik.model.Reservation;
import fr.treeptik.model.Voiture;
import fr.treeptik.service.MessageService;

public class DataBaseNotification {
	
	private Logger logger = Logger.getLogger(DataBaseNotification.class);
	
	@Autowired
	public MessageService messageService;

	public void afterReturningSave(StaticPart staticPart, Object result) {

		Message message = new Message();
		
		logger.info("Déclenchement de : " + staticPart.getSignature().getName()
				+ " type de retour " + result);

		message.setType(result.getClass().toString());

		if (result instanceof Client) {
			Client client = (Client) result;

			message.setMessage("Client " + client.getNom() + " "
					+ client.getPrenom() + " créée en base");
			
		} else if (result instanceof Voiture) {
			Voiture voiture = (Voiture) result;
			message.setMessage("Voiture : " + voiture + " sauvée en base");
						
		} else if (result instanceof Reservation) {
			Reservation reservation = (Reservation) result;
			message.setMessage("Reservation : " + reservation + " sauvée en base");			
		}
		
		messageService.save(message);

	}

	// public void beforeReturningSave(JoinPoint joinPoint) {
	//
	// }

}
