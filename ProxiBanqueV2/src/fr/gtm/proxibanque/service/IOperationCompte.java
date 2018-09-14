package fr.gtm.proxibanque.service;

public interface IOperationCompte {
	
	public void crediter(int idClient, float montant, String typeCompte);
	
	public boolean debiter(int idClient, float montant, String typeCompte);

}
