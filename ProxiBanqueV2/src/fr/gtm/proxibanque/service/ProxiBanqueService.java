package fr.gtm.proxibanque.service;

import java.sql.Connection;
import java.util.List;

import fr.gtm.proxibanque.dao.ClientDAO;
import fr.gtm.proxibanque.dao.CompteCourantDAO;
import fr.gtm.proxibanque.dao.CompteEpargneDAO;
import fr.gtm.proxibanque.dao.ConseillerDAO;
import fr.gtm.proxibanque.dao.ListeClientDAO;
import fr.gtm.proxibanque.domaine.Client;
import fr.gtm.proxibanque.domaine.CompteCourant;
import fr.gtm.proxibanque.domaine.CompteEpargne;
import fr.gtm.proxibanque.domaine.Conseiller;
import fr.gtm.proxibanque.domaine.ListeClient;

/**
 * ProxiBanqueDAO est la classe contenant les méthodes métier de l'application.
 * 
 * @author croblin / tyahyaoui / amialoundama
 * @version 1.0
 */

public class ProxiBanqueService implements IOperationCompte {
	
	/**
     * L'objet ConseillerDAO va permettre d'envoyer des requêtes vers la table Conseiller.
     * 
     */	
	private ConseillerDAO daoConseiller;
	
	/**
     * L'objet ClientDAO va permettre d'envoyer des requêtes vers la table Client.
     * 
     */	
	private ClientDAO daoClient;
	
	/**
     * L'objet ListeClientDAO va permettre d'envoyer des requêtes vers la table ListeClient.
     * 
     */	
	private ListeClientDAO daoListeClient;
	
	/**
     * L'objet CompteCourantDAO va permettre d'envoyer des requêtes vers la table CompteCourant.
     * 
     */	
	private CompteCourantDAO daoCompteCourant;
	
	/**
     * L'objet CompteEpargneDAO va permettre d'envoyer des requêtes vers la table CompteEpargne.
     * 
     */	
	private CompteEpargneDAO daoCompteEpargne;

	/**
     * Le constructeur du service ProxiBanqueService qui prend en paramètre une référence vers la connexion à la base de données.
     * 
     * @param cn
     *		L'objet Connection pour se connecter à une base de données.
     * 
     */
	public ProxiBanqueService(Connection cn) {
		this.daoConseiller = new ConseillerDAO(cn);
		this.daoClient = new ClientDAO(cn);
		this.daoListeClient = new ListeClientDAO(cn);
		this.daoCompteCourant = new CompteCourantDAO(cn);
		this.daoCompteEpargne = new CompteEpargneDAO(cn);
	}
	
	/**
     * Méthode permettant de vérifier si un conseiller existe dans la table Conseiller via son login et son mot de passe.
     * On retourne le conseiller si trouvé dans la table Conseiller. 
     * 
     * @return Le conseiller récupéré dans la table Conseiller.
     * 
     * @param login
     * 		Le login du conseiller.
     * 
     * @param motDePasse
     * 		Le mot de passe du conseiller.
     * 
     */	
	public Conseiller verifierAuthentificationConseiller(String login, String motDePasse) {
		return this.daoConseiller.getConseillerByLoginAndPassword(login, motDePasse);
	}
	
	/**
     * Méthode permettant de récupérer un conseiller.
     * 
     * @return Le conseiller récupéré dans la table Conseiller.
     * 
     * @param id
     * 		L'identifiant du conseiller.
     * 
     */	
	public Conseiller recupererConseiller(int id) {
		return this.daoConseiller.getConseillerById(id);
	}
	
	/**
     * Méthode permettant de récupérer tous les clients.
     * 
     * @return La liste de tous les clients.
     * 
     */	
	public List<Client> recupererTousClient() {
		return this.daoClient.getAllClient();
	}
	
	/**
     * Méthode permettant de récupérer tous les clients rattachés à un conseiller. 
     * 
     * @return La liste des clients rattachés à un conseiller.
     * 
     * @param id
     * 		L'identifiant du conseiller. 
     * 
     */	
	public List<ListeClient> recupererListeClient(int id) {
		return this.daoListeClient.getListeClient(id);
	}
	
	/**
     * Méthode permettant de récupérer un client dans la table Client via son identifiant.
     * 
     * @return Le client récupéré dans la table Client.
     * 
     * @param id
     * 		L'identifiant du client. 
     * 
     */	
	public Client recupererClient(int id) {
		return this.daoClient.getClientById(id);
	}
	
	/**
     * Méthode permettant de mettre à jour un client dans la table Client.
     * 
     * @param client
     * 		Le client à modifier. 
     * 
     */	
	public void modifierClient(Client client) {
		this.daoClient.updateClient(client);
	}

	/**
     * Méthode permettant de créditer le compte d'un client.
     * 
     * @param idClient
     * 		L'identifiant du client à créditer.
     * 
     * @param montant
     * 		Le montant à ajouter au solde du compte du client.
     * 
     * @param typeCompte
     * 		Le type de compte à créditer.   
     * 
     */	
	@Override
	public void crediter(int idClient, float montant, String typeCompte) {
		if(typeCompte.equals("comptecourant")) {
			CompteCourant compteCourant = this.daoCompteCourant.getCompteCourantByIdClient(idClient);
			montant = montant + compteCourant.getSolde();
			this.daoCompteCourant.updateCompteCourant(idClient, montant);
		}
		else if(typeCompte.equals("compteepargne")) {
			CompteEpargne compteEpargne = this.daoCompteEpargne.getCompteEpargneByIdClient(idClient);
			montant = montant + compteEpargne.getSolde();
			this.daoCompteEpargne.updateCompteEpargne(idClient, montant);
		}		
	}

	/**
     * Méthode permettant de débiter le compte d'un client, en fonction d'un seuil à ne pas dépasser sur le compte courant ou d'éparge.
     * Si si seuil est dépassé, le débit n'est pas effectué, et la méthode retourne un false.
     * 
     * @param idClient
     * 		L'identifiant du client à débiter.
     * 
     * @param montant
     * 		Le montant à retirer au solde du compte du client.
     * 
     * @param typeCompte
     * 		Le type de compte à débiter.    
     * 
     */	
	@Override
	public boolean debiter(int idClient, float montant, String typeCompte) {
		if(typeCompte.equals("comptecourant")) {
			CompteCourant compteCourant = this.daoCompteCourant.getCompteCourantByIdClient(idClient);
			float soldeApresDebit = compteCourant.getSolde() - montant;
			float autorisation = - compteCourant.getDecouvert();
			if(soldeApresDebit < autorisation) {
				return false;
			}
			else {
				System.out.println(idClient + " " + soldeApresDebit);
				this.daoCompteCourant.updateCompteCourant(idClient, soldeApresDebit);
				return true;
			}
		}
		if(typeCompte.equals("compteepargne")) {
			CompteEpargne compteEpargne = this.daoCompteEpargne.getCompteEpargneByIdClient(idClient);
			float soldeApresDebit = compteEpargne.getSolde() - montant;
			if(soldeApresDebit < 0) {
				return false;
			}
			else {
				this.daoCompteEpargne.updateCompteEpargne(idClient, soldeApresDebit);
				return true;
			}
		}
		return false;
	}
	
	/**
     * Méthode permettant de récupérer le compte courant rattaché à un client.
     * 
     * @return Le compte courant du client.
     * 
     * @param id
     * 		L'identifiant du client. 
     * 
     */	
	public CompteCourant recupererCompteCourant(int id) {
		return this.daoCompteCourant.getCompteCourantByIdClient(id);
	}
	
	/**
     * Méthode permettant de récupérer le compte épargne rattaché à un client.
     * 
     * @return Le compte épargne du client.
     * 
     * @param id
     * 		L'identifiant du client. 
     * 
     */	
	public CompteEpargne recupererCompteEpargne(int id) {
		return this.daoCompteEpargne.getCompteEpargneByIdClient(id);
	}
}
