package fr.gtm.proxibanque.domaine;

/**
 * Compte est la classe abstraite représentant un compte appartenant à un client.
 * 
 * @author croblin / tyahyaoui / amialoundama
 * @version 1.0
 */

public abstract class Compte {
	
	/**
     * L'identifiant du compte.
     * 
     */
	private int id;
	
	/**
     * Le solde du compte.
     * 
     */
	private float solde;
	
	/**
     * L'identifiant du client possédant le compte.
     * 
     */
	private int idClient;
	
	/**
     * Le nom du client possédant le compte.
     * 
     */
	private String nomClient;

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
	 * @return the solde
	 */
	public float getSolde() {
		return solde;
	}

	/**
	 * @param solde the solde to set
	 */
	public void setSolde(float solde) {
		this.solde = solde;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + idClient;
		result = prime * result + ((nomClient == null) ? 0 : nomClient.hashCode());
		result = prime * result + Float.floatToIntBits(solde);
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
		Compte other = (Compte) obj;
		if (id != other.id)
			return false;
		if (idClient != other.idClient)
			return false;
		if (nomClient == null) {
			if (other.nomClient != null)
				return false;
		} else if (!nomClient.equals(other.nomClient))
			return false;
		if (Float.floatToIntBits(solde) != Float.floatToIntBits(other.solde))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Compte [id=" + id + ", solde=" + solde + ", idClient=" + idClient + ", nomClient=" + nomClient + "]";
	}	
}
