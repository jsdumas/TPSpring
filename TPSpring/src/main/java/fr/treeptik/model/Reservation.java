package fr.treeptik.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@NamedQueries({
	@NamedQuery(name="countReservation",
			query="SELECT COUNT(r)  FROM Reservation r "
						+ "WHERE r.client.id= :clientId"),
	
	@NamedQuery(name="findReservationByClient",
			query="SELECT r FROM Reservation r "
					+ "WHERE r.client.id= :clientId")
})
@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	private Voiture voiture;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yy-MM-dd")
	private Date date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yy-MM-dd")
	private Date dateDePrise;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yy-MM-dd")
	private Date dateDeRetour;
	@ManyToOne
	private Client client;
	private Integer total;

	public Reservation() {
		// TODO Auto-generated constructor stub
	}

	public Reservation(Voiture voiture, Date date, Date dateDePrise,
			Date dateDeRetour, Client client, Integer total) {
		super();
		this.voiture = voiture;
		this.date = date;
		this.dateDePrise = dateDePrise;
		this.dateDeRetour = dateDeRetour;
		this.client = client;
		this.total = total;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", voiture=" + voiture + ", date="
				+ date + ", dateDePrise=" + dateDePrise + ", dateDeRetour="
				+ dateDeRetour + ", client=" + client + ", total=" + total
				+ "]";
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
		Reservation other = (Reservation) obj;
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

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDateDePrise() {
		return dateDePrise;
	}

	public void setDateDePrise(Date dateDePrise) {
		this.dateDePrise = dateDePrise;
	}

	public Date getDateDeRetour() {
		return dateDeRetour;
	}

	public void setDateDeRetour(Date dateDeRetour) {
		this.dateDeRetour = dateDeRetour;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
