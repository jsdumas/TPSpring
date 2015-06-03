delete from Reservation;
delete from Voiture;
delete from Client;
delete from Message;

INSERT INTO Client (id, nom, prenom) VALUES(1, "Aubert", "Julien");
INSERT INTO Client (id, nom, prenom) VALUES(2, "Dujardin", "Jean");
INSERT INTO Client (id, nom, prenom) VALUES(3, "Thomas", "Eric");

INSERT INTO Voiture (id, marque, couleur, immatriculation, dateDeMiseEnCirculation, prix) VALUES(1, "Renault", "rouge", "124HJ13", "2012-02-12", 30);
INSERT INTO Voiture (id, marque, couleur, immatriculation, dateDeMiseEnCirculation, prix) VALUES(2, "Peugeot", "blanc", "456ML13", "2014-01-01", 34);
INSERT INTO Voiture (id, marque, couleur, immatriculation, dateDeMiseEnCirculation, prix) VALUES(3, "Audi", "noire", "789ML13", "2015-03-12", 45);
