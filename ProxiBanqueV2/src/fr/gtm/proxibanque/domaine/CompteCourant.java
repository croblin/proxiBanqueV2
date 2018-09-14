package fr.gtm.proxibanque.domaine;

/**
 * CompteCourant est la classe représentant un compte courant appartenant à un client. Elle hérite de la classe abstraite Compte.
 * 
 * @author croblin / tyahyaoui / amialoundama
 * @version 1.0
 */

public class CompteCourant extends Compte {
	
	/**
     * L'identifiant du compte.
     * 
     */
	private float decouvert;
	
	/**
     * Le constructeur par défaut du compte courant.
     * 
     */
	public CompteCourant() {
		
	}

	/**
	 * @return the decouvert
	 */
	public float getDecouvert() {
		return decouvert;
	}

	/**
	 * @param decouvert the decouvert to set
	 */
	public void setDecouvert(float decouvert) {
		this.decouvert = decouvert;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Float.floatToIntBits(decouvert);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompteCourant other = (CompteCourant) obj;
		if (Float.floatToIntBits(decouvert) != Float.floatToIntBits(other.decouvert))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompteCourant [decouvert=" + decouvert + "]";
	}
}
