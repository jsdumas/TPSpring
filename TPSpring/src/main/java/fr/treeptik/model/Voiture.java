package fr.treeptik.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQueries({
		@NamedQuery(name = "findAllFreeCar", query = "SELECT v FROM Voiture v LEFT JOIN v.reservations r "
				+ "WHERE r IS NULL "
				+ "OR r NOT IN (SELECT r FROM Reservation r "
				+ "WHERE r.dateDePrise BETWEEN :dateDePrise AND :dateDeRetour "
				+ "OR r.dateDeRetour BETWEEN :dateDePrise AND :dateDeRetour "
				+ "OR r.dateDePrise<:dateDePrise AND r.dateDeRetour>:dateDeRetour)"),

		@NamedQuery(name = "findByColor", query = "SELECT v FROM Voiture v "
				+ "WHERE v.couleur LIKE :color"),

		@NamedQuery(name = "findByBrand", query = "SELECT v FROM Voiture v "
				+ "WHERE v.marque LIKE :brand"),

		@NamedQuery(name = "findByDate", query = "SELECT v FROM Voiture v "
				+ "WHERE v.dateDeMiseEnCirculation LIKE :date")

})
@Entity
public class Voiture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String marque;
	private String couleur;
	private String immatriculation;
	private Date dateDeMiseEnCirculation;
	private Integer prix;
	@OneToMany(mappedBy = "voiture")
	private List<Reservation> reservations;

	public Voiture() {
	}

	public Voiture(String marque, String couleur, String immatriculation,
			Date dateDeMiseEnCirculation, Integer prix) {
		super();
		this.marque = marque;
		this.couleur = couleur;
		this.immatriculation = immatriculation;
		this.dateDeMiseEnCirculation = dateDeMiseEnCirculation;
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Voiture [id=" + id + ", marque=" + marque + ", couleur="
				+ couleur + ", immatriculation=" + immatriculation
				+ ", dateDeMiseEnCirculation=" + dateDeMiseEnCirculation
				+ ", prix=" + prix + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voiture other = (Voiture) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public Date getDateDeMiseEnCirculation() {
		return dateDeMiseEnCirculation;
	}

	public void setDateDeMiseEnCirculation(Date dateDeMiseEnCirculation) {
		this.dateDeMiseEnCirculation = dateDeMiseEnCirculation;
	}

	public Integer getPrix() {
		return prix;
	}

	public void setPrix(Integer prix) {
		this.prix = prix;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> voitures) {
		this.reservations = voitures;
	}

}
