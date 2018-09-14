package fr.gtm.proxibanque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.gtm.proxibanque.domaine.Client;

/**
 * ClientDAO est la classe permettant d'envoyer des requêtes vers la table Client.
 * 
 * @author croblin / tyahyaoui / amialoundama
 * @version 1.0
 */

public class ClientDAO {
	
	/**
     * L'objet PreparedStatement va permettre de préparer les requêtes pour les envois vers une table.
     * 
     */
	PreparedStatement pst;
	
	/**
     * L'objet ResultSet va permettre de récupérer les résultats en retour d'une requête envoyée vers une table.
     * 
     */
	ResultSet rs;
	
	/**
     * L'objet Connection va permettre de récupérer la connexion vers la base de données.
     * 
     */
	Connection cn;

	/**
     * Le constructeur du client qui prend en paramètre une référence vers la connexion à la base de données.
     * 
     */
	public ClientDAO(Connection cn) {
			this.cn = cn;
		}

	/**
     * Méthode permettant de récupérer un client dans la table Client. On va le chercher via son identifiant.
     * 
     * @return Le client récupéré dans la table Client.
     * 
     */	
	public Client getClientById(int id) {
		Client client = new Client();
		try {
			String requete = "SELECT * FROM client WHERE id = ?";
			this.pst = this.cn.prepareStatement(requete);
			this.pst.setInt(1, id);
			this.rs = pst.executeQuery();
			if (this.rs == null) {
				return null;
			} else {				
				while (rs.next()) {
					client.setId(rs.getInt("id"));
					client.setNom(rs.getString("nom"));
					client.setPrenom(rs.getString("prenom"));
					client.setAdresse(rs.getString("adresse"));
					client.setCourriel(rs.getString("courriel"));					
				}
			}
			return client;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Méthode permettant de mettre à jour un client dans la table Client.
	 * 
	 */
	public void updateClient(Client client) {
		try {
			String requete = "UPDATE client SET nom = ?, prenom = ?, adresse = ?, courriel = ? WHERE id = ?";
			this.pst = this.cn.prepareStatement(requete);
			this.pst.setString(1, client.getNom());
			this.pst.setString(2, client.getPrenom());
			this.pst.setString(3, client.getAdresse());
			this.pst.setString(4, client.getCourriel());
			this.pst.setInt(5, client.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Méthode permettant de récupérer tous les clients de la table Client.
     * 
     * @return Tous les clients de la table Client contenus dans une liste.
     * 
     */	
	public List<Client> getAllClient() {
		List<Client> listeClient = new ArrayList<>();
		Client client;
		try {
			String requete = "SELECT * FROM client";
			this.pst = this.cn.prepareStatement(requete);
			this.rs = pst.executeQuery();
			if (this.rs == null) {
				return null;
			} else {				
				while (rs.next()) {
					client = new Client();
					client.setId(rs.getInt("id"));
					client.setNom(rs.getString("nom"));
					client.setPrenom(rs.getString("prenom"));
					client.setAdresse(rs.getString("adresse"));
					client.setCourriel(rs.getString("courriel"));	
					listeClient.add(client);
				}
			}
			return listeClient;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
