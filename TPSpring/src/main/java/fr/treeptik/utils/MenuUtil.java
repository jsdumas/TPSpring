package fr.treeptik.utils;

public class MenuUtil {
	
	public static void afficherMenu() {
		System.out.println("MENU");
		System.out.println("Choisir une option : ");
		System.out.println("1 - Menu client");
		System.out.println("2 - Menu voiture");
		System.out.println("3 - Menu réservation");
		System.out.println("4 - Quit");
	}
	
	public static void afficherMenuClient() {
		System.out.println("MENU CLIENT");
		System.out.println("Choisir une option : ");
		System.out.println("1 - Créer un client");
		System.out.println("2 - Supprimer un client");
		System.out.println("3 - Update client");
		System.out.println("4 - Trouver un client par son ID");
		System.out.println("5 - Lister les clients");
		System.out.println("6 - q pour quitter");
	}
	
	public static void afficherMenuVoiture() {
		System.out.println("MENU VOITURE");
		System.out.println("Choisir une option : ");
		System.out.println("1 - Créer une voiture");
		System.out.println("2 - Supprimer une voiture");
		System.out.println("3 - Update voiture");
		System.out.println("4 - Trouver une voiture par son ID");
		System.out.println("5 - Lister les voiture");
		System.out.println("6 - Recherche de voiture par couleur");
		System.out.println("7 - Recherche de voiture par marque");
		System.out.println("8 - Recherche de voiture par date");
		System.out.println("9 - q pour quitter");
	}
	
	public static void afficherMenuReservation() {
		System.out.println("MENU RESERVATION");
		System.out.println("Choisir une option : ");
		System.out.println("1 - Créer une réservation");
		System.out.println("2 - Historique des réservations");
		System.out.println("3 - q pour quitter");
	}
	
	public static void telephoneMenu() {
		System.out.println("Appuyer sur c pour rentrer un nouveau numéro");
		System.out.println("Appuyer sur q pour quitter");
	}
	
	

}
