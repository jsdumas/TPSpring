package fr.treeptik.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQueries({
		@NamedQuery(name = "findByTotalBookDesc", query = "SELECT c FROM Client c "
				+ "JOIN c.reservations r "
				+ "GROUP BY c "
				+ "ORDER BY COUNT(r) DESC"),

		@NamedQuery(name = "findByBookOfCarNumber", query = "SELECT DISTINCT c FROM Reservation r "
				+ "JOIN r.client c "
				+ "JOIN r.voiture v "
				+ "GROUP BY c, r "
				+ "ORDER BY COUNT(v) DESC"),
				
		@NamedQuery(name = "findByLocationDuration", query = "SELECT c FROM Client c "
				+ "JOIN c.reservations r "
				+ "GROUP BY c "
				+ "ORDER BY SUM(r.dateDeRetour - r.dateDePrise) ")
				
})
@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nom;
	private String prenom;
	@OneToMany(mappedBy = "client")
	private List<Reservation> reservations;

	public Client() {
	}

	public Client(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
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
		Client other = (Client) obj;
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

}
