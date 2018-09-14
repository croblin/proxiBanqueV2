package fr.gtm.proxibanque.domaine;

/**
 * Client est la classe représentant un client associé à un conseiller de la banque ProxiBanque.
 * 
 * @author croblin / tyahyaoui / amialoundama
 * @version 1.0
 */

public class Client {
	
	/**
     * L'identifiant du client. Unique pour chaque client.
     * 
     */
	private int id;
	
	/**
     * Le nom du client.
     * 
     */
	private String nom;
	
	/**
     * Le prénom du client.
     * 
     */
	private String prenom;
	
	/**
     * L'adresse du client.
     * 
     */
	private String adresse;
	
	/**
     * Le prénom du client.
     * 
     */
	private String courriel;
	
	/**
     * Le constructeur par défaut du client.
     * 
     */
	public Client() {
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the courriel
	 */
	public String getCourriel() {
		return courriel;
	}

	/**
	 * @param courriel the courriel to set
	 */
	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((courriel == null) ? 0 : courriel.hashCode());
		result = prime * result + id;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (courriel == null) {
			if (other.courriel != null)
				return false;
		} else if (!courriel.equals(other.courriel))
			return false;
		if (id != other.id)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", courriel="
				+ courriel + "]";
	}
}
