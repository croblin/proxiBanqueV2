package fr.gtm.proxibanque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.gtm.proxibanque.domaine.Conseiller;

/**
 * ConseillerDAO est la classe permettant d'envoyer des requ�tes vers la table Conseiller.
 * 
 * @author croblin / tyahyaoui / amialoundama
 * @version 1.0
 */

public class ConseillerDAO {

	/**
     * L'objet PreparedStatement va permettre de pr�parer les requ�tes pour les envois vers une table.
     * 
     */
	PreparedStatement pst;
	
	/**
     * L'objet ResultSet va permettre de r�cup�rer les r�sultats en retour d'une requ�te envoy�e vers une table.
     * 
     */
	ResultSet rs;
	
	/**
     * L'objet Connection va permettre de r�cup�rer la connexion vers la base de donn�es.
     * 
     */
	Connection cn;

	/**
     * Le constructeur du conseiller qui prend en param�tre une r�f�rence vers la connexion � la base de donn�es.
     * 
     */
	public ConseillerDAO(Connection cn) {
			this.cn = cn;
		}

	/**
     * M�thode permettant de r�cup�rer un conseiller dans la table Conseiller. On va le chercher via son login et son mot de passe.
     * 
     * @return Le conseiller r�cup�r� dans la table Conseiller.
     * 
     */	
	public Conseiller getConseillerByLoginAndPassword(String login, String motDePasse) {
		Conseiller conseiller = new Conseiller();
		try {
			String requete = "SELECT * FROM conseiller WHERE login = ? AND motdepasse = ?";
			this.pst = this.cn.prepareStatement(requete);
			this.pst.setString(1, login);
			this.pst.setString(2, motDePasse);
			this.rs = pst.executeQuery();
			if (this.rs == null) {
				return null;
			} else {				
				while (rs.next()) {
					conseiller.setId(rs.getInt("id"));
					conseiller.setNom(rs.getString("nom"));
					conseiller.setPrenom(rs.getString("prenom"));
					conseiller.setLogin(rs.getString("login"));
					conseiller.setMotDePasse(rs.getString("motdepasse"));					
				}
			}
			return conseiller;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * M�thode permettant de r�cup�rer un conseiller dans la table Conseiller. On va le chercher via son identifiant.
     * 
     * @return Le conseiller r�cup�r� dans la table Conseiller.
     * 
     */	
	public Conseiller getConseillerById(int id) {
		Conseiller conseiller = new Conseiller();
		try {
			String requete = "SELECT * FROM conseiller WHERE id = ?";
			this.pst = this.cn.prepareStatement(requete);
			this.pst.setInt(1, id);
			this.rs = pst.executeQuery();
			if (this.rs == null) {
				return null;
			} else {				
				while (rs.next()) {
					conseiller.setId(rs.getInt("id"));
					conseiller.setNom(rs.getString("nom"));
					conseiller.setPrenom(rs.getString("prenom"));
					conseiller.setLogin(rs.getString("login"));
					conseiller.setMotDePasse(rs.getString("motdepasse"));					
				}
			}
			return conseiller;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
