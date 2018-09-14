package fr.gtm.proxibanque.domaine;

/**
 * Conseiller est la classe représentant un conseiller appartenant à la banque ProxiBanque.
 * 
 * @author croblin / tyahyaoui / amialoundama
 * @version 1.0
 */

public class ListeClient {
	
	/**
     * L'identifiant de la liste de clients rattachés à un conseiller.
     * 
     */
	private int id;
	
	/**
     * L'identifiant du client rattaché au conseiller.
     * 
     */
	private int idClient;
	
	/**
     * Le nom du client rattaché au conseiller.
     * 
     */
	private String nomClient;
	
	/**
     * L'identifiant du conseiller rattaché à un ou plusieurs clients.
     * 
     */
	private int idConseiller;
	
	/**
     * Le nom du conseiller rattaché à un ou plusieurs clients.
     * 
     */
	private String nomConseiller;
	
	/**
     * Le constructeur par défaut de la liste de clients.
     * 
     */
	public ListeClient() {
		
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
	 * @return the idClient
	 */
	public int getIdClient() {
		return idClient;
	}

	/**
	 * @param idClient the idClient to set
	 */
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	/**
	 * @return the nomClient
	 */
	public String getNomClient() {
		return nomClient;
	}

	/**
	 * @param nomClient the nomClient to set
	 */
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	/**
	 * @return the idConseiller
	 */
	public int getIdConseiller() {
		return idConseiller;
	}

	/**
	 * @param idConseiller the idConseiller to set
	 */
	public void setIdConseiller(int idConseiller) {
		this.idConseiller = idConseiller;
	}

	/**
	 * @return the nomConseiller
	 */
	public String getNomConseiller() {
		return nomConseiller;
	}

	/**
	 * @param nomConseiller the nomConseiller to set
	 */
	public void setNomConseiller(String nomConseiller) {
		this.nomConseiller = nomConseiller;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + idClient;
		result = prime * result + idConseiller;
		result = prime * result + ((nomClient == null) ? 0 : nomClient.hashCode());
		result = prime * result + ((nomConseiller == null) ? 0 : nomConseiller.hashCode());
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
		ListeClient other = (ListeClient) obj;
		if (id != other.id)
			return false;
		if (idClient != other.idClient)
			return false;
		if (idConseiller != other.idConseiller)
			return false;
		if (nomClient == null) {
			if (other.nomClient != null)
				return false;
		} else if (!nomClient.equals(other.nomClient))
			return false;
		if (nomConseiller == null) {
			if (other.nomConseiller != null)
				return false;
		} else if (!nomConseiller.equals(other.nomConseiller))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListeClient [id=" + id + ", idClient=" + idClient + ", nomClient=" + nomClient + ", idConseiller="
				+ idConseiller + ", nomConseiller=" + nomConseiller + "]";
	}	
}
