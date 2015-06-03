package fr.treeptik.runtime;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Client;
import fr.treeptik.model.Reservation;
import fr.treeptik.model.Voiture;
import fr.treeptik.service.ClientService;
import fr.treeptik.service.ReservationService;
import fr.treeptik.service.VoitureService;
import fr.treeptik.utils.DateUtils;
import fr.treeptik.utils.MenuUtil;

public class Runtime {

	public static void main(String[] args) throws ParseException,
			ServiceException, DAOException {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");

		ClientService clientService = (ClientService) context
				.getBean("clientService");
		VoitureService voitureService = (VoitureService) context
				.getBean("voitureService");
		ReservationService reservationService = (ReservationService) context
				.getBean("reservationService");

		/************************************************************************/

		// Client client1 = new Client("Aubert", "Julien");
		// Client client2 = new Client("Dujardin", "Jean");
		// Client client3 = new Client("Thomas", "Eric");
		// clientService.save(client1);
		// clientService.save(client2);
		// clientService.save(client3);
		//
		// Voiture voiture1 = new Voiture ("Renault", "rouge", "124HJ13",
		// DateUtils.stringToDate("12/02/2012"), 30);
		// Voiture voiture2 = new Voiture ("Peugeot", "blanc", "456ML13",
		// DateUtils.stringToDate("01/01/2014"), 34);
		// Voiture voiture3 = new Voiture ("Audi", "noire", "789ML13",
		// DateUtils.stringToDate("12/03/2015"), 45);
		// voitureService.save(voiture1);
		// voitureService.save(voiture2);
		// voitureService.save(voiture3);

		/************************************************************************/

		Scanner scanner = new Scanner(System.in);
		String choix = "";
		List<String> choixDisponible = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

		do {
			// on affiche le menu
			MenuUtil.afficherMenu();
			choix = scanner.nextLine();
			// si le choix n'est pas disponible on réaffiche le menu
			if (!choixDisponible.contains(choix)) {
				continue;
			}
			// si le choix est égal à 5 on sort
			if (choix.equalsIgnoreCase("4"))
				break;

			switch (choix) {
			case "1":
				do {
					// MENU CLIENT ////////////////////////////////
					MenuUtil.afficherMenuClient();
					choix = scanner.nextLine();
					Integer id = null;
					// si le choix n'est pas disponible on réaffiche le menu
					if (!choixDisponible.contains(choix)) {
						continue;
					}
					// si le choix est égal à 5 on sort
					if (choix.equalsIgnoreCase("q"))
						break;

					switch (choix) {
					case "1":
						System.out.println("Création de client");
						Client c1 = new Client();
						System.out.println("Entrez un nom : ");
						c1.setNom(scanner.nextLine());
						System.out.println("Entrez un prenom : ");
						c1.setPrenom(scanner.nextLine());
						clientService.save(c1);
						System.out.println(c1 + " correctement crée");
						break;

					case "2":

						System.out.println("Supprimer un client par son id");
						System.out.println("Entrez un id : ");
						id = Integer.parseInt(scanner.nextLine());
						Client c2 = clientService.removeById(id);
						System.out.println(c2 + " correctement supprimer");
						break;

					case "3":
						String nom = null;
						String prenom = null;
						System.out.println("Update client");
						System.out.println("Entrez un id : ");
						id = Integer.parseInt(scanner.nextLine());
						System.out.println("Entrez un prenom : ");
						nom = scanner.nextLine();
						System.out.println("Entrez un nom : ");
						prenom = scanner.nextLine();
						clientService.update(id, nom, prenom);
						// System.out.println(client +
						// " correctement supprimer");
						break;

					case "4":
						System.out.println("findById client");
						System.out.println("Entrez un id : ");
						id = Integer.parseInt(scanner.nextLine());
						Client c3 = clientService.findById(id);
						System.out.println("client " + c3);
						break;

					case "5":
						System.out.println("findAll client");
						List<Client> clients = clientService.findAll();
						for (Client client : clients) {
							System.out.println("client :" + client);
						}
						break;
					}

				} while (!choix.equalsIgnoreCase("q"));
				break;

			case "2":
				do {
					// MENU VOITURE ////////////////////////////////////////////
					MenuUtil.afficherMenuVoiture();
					choix = scanner.nextLine();
					Integer id = null;
					// si le choix n'est pas disponible on réaffiche le menu
					if (!choixDisponible.contains(choix)) {
						continue;
					}
					// si le choix est égal à 5 on sort
					if (choix.equalsIgnoreCase("q"))
						break;

					switch (choix) {
					case "1":
						System.out.println("Création d'un voiture");
						Voiture v1 = new Voiture();
						System.out.println("Entrez une marque : ");
						v1.setMarque(scanner.nextLine());
						System.out.println("Entrez une couleur : ");
						v1.setCouleur(scanner.nextLine());
						System.out.println("Entrez une immatriculation : ");
						v1.setImmatriculation(scanner.nextLine());
						System.out
								.println("Entrez une date de mise en circulation : ");
						Date date = DateUtils.stringToDate(scanner.nextLine());
						v1.setDateDeMiseEnCirculation(date);
						System.out.println("Entrez un prix journalier : ");
						v1.setPrix(Integer.parseInt(scanner.nextLine()));

						voitureService.save(v1);
						System.out.println(v1 + " correctement crée");
						break;

					case "2":

						System.out.println("Supprimer une voiture par son id");
						System.out.println("Entrez un id : ");
						id = Integer.parseInt(scanner.nextLine());
						Voiture v2 = voitureService.removeById(id);
						System.out.println(v2 + " correctement supprimer");
						break;

					case "3":
						String marque = null;
						String couleur = null;
						String immatriculation = null;
						Date dateDeMiseEnCirculation = null;
						Integer prix = null;

						System.out.println("Update voiture");

						System.out.println("Entrez un id : ");
						id = Integer.parseInt(scanner.nextLine());

						System.out.println("Update d'une voiture");

						System.out.println("Entrez une marque : ");
						marque = scanner.nextLine();
						System.out.println("Entrez une couleur : ");
						couleur = scanner.nextLine();
						System.out.println("Entrez une immatriculation : ");
						immatriculation = scanner.nextLine();
						System.out
								.println("Entrez une date de mise en circulation : ");
						dateDeMiseEnCirculation = DateUtils
								.stringToDate(scanner.nextLine());
						System.out.println("Entrez un prix journalier : ");
						prix = Integer.parseInt(scanner.nextLine());

						voitureService.update(id, marque, couleur,
								immatriculation, dateDeMiseEnCirculation, prix);

						break;

					case "4":
						System.out.println("findById voiture");
						System.out.println("Entrez un id : ");
						id = Integer.parseInt(scanner.nextLine());
						Voiture v4 = voitureService.findById(id);
						System.out.println("voiture " + v4);
						break;

					case "5":
						System.out.println("findAll voiture");
						List<Voiture> voitures = voitureService.findAll();
						for (Voiture voiture : voitures) {
							System.out.println("Voiture :" + voiture);
						}
						break;
						
					case "6":
						System.out.println("***********************");
						System.out.println("findByColor");
						System.out.println("Entrez une couleur : ");
						String color = scanner.nextLine();
						List<Voiture> voituresByColor = voitureService
								.findByColor(color);
						if (!voituresByColor.isEmpty()) {
							for (Voiture voiture : voituresByColor) {
								System.out.println("Voiture de couleur "
										+ color + " " + voiture);
							}
						} else {
							System.out
									.println("Il n'y a pas de voiture de cette couleur");
						}
						break;
						
					case "7" :
						System.out.println("***********************");
						System.out.println("findByBrand");
						System.out.println("Entrez une marque de voiture : ");
						String brand = scanner.nextLine();
						List<Voiture> voituresByBrand = voitureService
								.findByBrand(brand);
						if (!voituresByBrand.isEmpty()) {
							for (Voiture voiture : voituresByBrand) {
								System.out.println("Voiture de marque "
										+ brand + " " + voiture);
							}
						} else {
							System.out
									.println("Il n'y a pas de voiture de cette marque");
						}
						
						break;
						
					case "8" :
						
						System.out.println("***********************");
						System.out.println("findByDate");
						System.out.println("Entrez la date de mise en circulation : ");
						String str = scanner.nextLine();
						Date dateFindByDate = DateUtils.stringToDate(str);
						
						List<Voiture> voituresBydate = voitureService
								.findByDate(dateFindByDate);
						if (!voituresBydate.isEmpty()) {
							for (Voiture voiture : voituresBydate) {
								System.out.println("Voiture mise en circulation en "
										+ dateFindByDate + " " + voiture);
							}
						} else {
							System.out
									.println("Il n'y a pas de voiture de cette marque");
						}
						
						break;

					}

				} while (!choix.equalsIgnoreCase("q"));
				break;

			case "3":
				do {
					// MENU RESERVATION
					// //////////////////////////////////////////////////////
					MenuUtil.afficherMenuReservation();
					choix = scanner.nextLine();
					Integer id = null;
					// si le choix n'est pas disponible on réaffiche le menu
					if (!choixDisponible.contains(choix)) {
						continue;
					}
					// si le choix est égal à 5 on sort
					if (choix.equalsIgnoreCase("q"))
						break;

					switch (choix) {

					case "1":

						try {

							/* Sélectionner un client */

							List<Client> clients = clientService.findAll();

							for (Client c : clients) {
								System.out.println(c);
							}
							System.out
									.println("Sélectionnez un client par son ID : ");

							Integer idClient = Integer.parseInt(scanner
									.nextLine());

							// if(reservationService.isReservervationUnderLimit(idClient))
							// System.out.println("Nombre de réservation maximale atteinte");

							/* Sélectionner une date */

							Date dateEnregistrement = new Date();

							System.out.println("Date de départ : ");
							Date dateDePrise = DateUtils.stringToDate(scanner
									.nextLine());

							System.out.println("Date de retour : ");
							Date dateDeRetour = DateUtils.stringToDate(scanner
									.nextLine());

							/* Sélectionner une voiture disponible */

							List<Voiture> voitures = voitureService
									.findAllFreeCar(dateDePrise, dateDeRetour);
							for (Voiture voiture : voitures) {
								System.out.println(voiture);
							}

							System.out
									.println("Sélectionnez une voiture par son ID : ");
							Integer idVoiture = Integer.parseInt(scanner
									.nextLine());

							/* Calcul */

							Integer total = voitureService
									.calculDuPrixLocation(dateDePrise,
											dateDeRetour, idVoiture);

							reservationService.save(dateEnregistrement,
									dateDePrise, dateDeRetour, total, idClient,
									idVoiture);

						} catch (ParseException e) {
							System.out.println("Vous devez rentrer un chiffre");
						}
						break;

					case "2":
						List<Reservation> reservations = reservationService
								.historiqueReservation();
						for (Reservation reservation : reservations) {
							System.out.println(reservation);
						}
						break;

					}

				} while (!choix.equalsIgnoreCase("q"));
				break;

			}
		} while (!choix.equalsIgnoreCase("4"));

	}
}
