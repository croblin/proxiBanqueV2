package fr.gtm.proxibanque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.gtm.proxibanque.domaine.CompteCourant;

/**
 * CompteCourantDAO est la classe permettant d'envoyer des requêtes vers la table CompteCourant.
 * 
 * @author croblin / tyahyaoui / amialoundama
 * @version 1.0
 */

public class CompteCourantDAO {
	
	/**
     * L'objet PreparedStatement va permettre de préparer les requêtes pour les envois vers une table.
     * 
     */
	private PreparedStatement pst;
	
	/**
     * L'objet ResultSet va permettre de récupérer les résultats en retour d'une requête envoyée vers une table.
     * 
     */
	private ResultSet rs;
	
	/**
     * L'objet Connection va permettre de récupérer la connexion vers la base de données.
     * 
     */
	private Connection cn;

	/**
     * Le constructeur du compte courant qui prend en paramètre une référence vers la connexion à la base de données.
     * 
     * @param cn 
     * 		L'objet Connection pour se connecter à la base de données.
     * 
     */
	public CompteCourantDAO(Connection cn) {
			this.cn = cn;
		}
	
	/**
	 * Méthode permettant de mettre à jour le solde d'un compte courant.
	 * 
	 * @param id 
	 * 		L'identifiant du compte.
	 * 
	 * @param solde 
	 * 		Le nouveau solde du compte.  
	 * 
	 */
	public void updateCompteCourant(int id, float solde) {
		try {
			
			System.out.println(" solde "+ solde);
			String requete = "UPDATE comptecourant SET solde = ? WHERE idclient = ?";
			this.pst = this.cn.prepareStatement(requete);
			this.pst.setFloat(1, solde);
			this.pst.setFloat(2, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Méthode permettant de récupérer un compte courant lié à un client. On filtre la recherche avec l'identifiant du client.
     * 
     * @return Le compte courant.
     * 
     * @param id 
     * 		L'identifiant du compte. 
     * 
     */	
	public CompteCourant getCompteCourantByIdClient(int id) {
		CompteCourant compte = new CompteCourant();
		try {
			String requete = "SELECT * FROM comptecourant WHERE id = ?";
			this.pst = this.cn.prepareStatement(requete);
			this.pst.setInt(1, id);
			this.rs = pst.executeQuery();
			if (this.rs == null) {
				return null;
			} else {				
				while (rs.next()) {
					compte.setId(this.rs.getInt("id"));
					compte.setIdClient(this.rs.getInt("idclient"));
					compte.setNomClient(this.rs.getString("nomClient"));
					compte.setSolde(this.rs.getFloat("solde"));	
					compte.setDecouvert(this.rs.getFloat("decouvert"));	
				}
			}
			return compte;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
