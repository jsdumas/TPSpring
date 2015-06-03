package fr.treeptik.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;

import fr.treeptik.model.Client;

public class MonitorAspect {

	private Logger logger = Logger.getLogger(MonitorAspect.class);

	public void afterReturningMethode(StaticPart staticPart, Object result) {

		logger.info("Déclenchement de : " + staticPart.getSignature().getName()
				+ " type de retour " + result);
	}

	public void beforeMethode(JoinPoint joinPoint) {

		if (joinPoint.getArgs().length > 0) {
			Object obj = joinPoint.getArgs()[0];
			logger.info("paramètre d'entrée de la méthode : " + obj);
		}

	}

	//
	// public void onErrorSavePersonne(JoinPoint joinPoint, Exception e){
	// logger.error("Erreur de type : "
	// + e.getLocalizedMessage()
	// + " sur la méthode "
	// + joinPoint.getSignature().getName());
	// }

	public void afterReturningRemoveByIdClient(StaticPart staticPart,
			Object result) {

		Client client = (Client) result;
		logger.info("Déclenchement de : " + staticPart.getSignature().getName()
				+ " type de retour " + client);
	}

	public void beforeRemoveByIdClient(JoinPoint joinPoint) {

		Integer integer = (Integer) joinPoint.getArgs()[0];

		logger.info("paramètre d'entrée de la méthode : " + integer);
	}

}
