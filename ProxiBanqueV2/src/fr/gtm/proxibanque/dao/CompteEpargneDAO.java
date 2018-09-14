package fr.gtm.proxibanque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.gtm.proxibanque.domaine.CompteEpargne;

/**
 * CompteEpargneDAO est la classe permettant d'envoyer des requ�tes vers la table CompteEpargne.
 * 
 * @author croblin / tyahyaoui / amialoundama
 * @version 1.0
 */

public class CompteEpargneDAO {
	
	/**
     * L'objet PreparedStatement va permettre de pr�parer les requ�tes pour les envois vers une table.
     * 
     */
	private PreparedStatement pst;
	
	/**
     * L'objet ResultSet va permettre de r�cup�rer les r�sultats en retour d'une requ�te envoy�e vers une table.
     * 
     */
	private ResultSet rs;
	
	/**
     * L'objet Connection va permettre de r�cup�rer la connexion vers la base de donn�es.
     * 
     */
	private Connection cn;

	/**
     * Le constructeur du compte �pargne qui prend en param�tre une r�f�rence vers la connexion � la base de donn�es.
     * 
     * @param cn 
     * 		L'objet Connection pour se connecter � une base de donn�es.
     * 
     */
	public CompteEpargneDAO(Connection cn) {
			this.cn = cn;
		}
	
	/**
	 * M�thode permettant de mettre � jour le solde d'un compte �pargne.
	 * 
	 * @param id L'identifiant du compte
	 * 
	 * @param solde 
	 * 		Le nouveau montant du compte.  
	 * 
	 */
	public void updateCompteEpargne(int id, float solde) {
		try {
			String requete = "UPDATE compteepargne SET solde = ? WHERE idclient = ?";
			this.pst = this.cn.prepareStatement(requete);
			this.pst.setFloat(1, solde);
			this.pst.setFloat(2, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * M�thode permettant de r�cup�rer un compte �pargne li� � un client. On filtre la recherche avec l'identifiant du client.
     * 
     * @return Le compte �pargne.
     * 
     * @param id 
     * 		L'identifiant du compte.
     * 
     */	
	public CompteEpargne getCompteEpargneByIdClient(int id) {
		CompteEpargne compte = new CompteEpargne();
		try {
			String requete = "SELECT * FROM compteepargne WHERE id = ?";
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
				}
			}
			return compte;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
