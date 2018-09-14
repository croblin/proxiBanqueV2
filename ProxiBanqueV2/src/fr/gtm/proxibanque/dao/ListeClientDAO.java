package fr.gtm.proxibanque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.gtm.proxibanque.domaine.ListeClient;

/**
 * ListeClientDAO est la classe permettant d'envoyer des requêtes vers la table ListeClient.
 * 
 * @author croblin / tyahyaoui / amialoundama
 * @version 1.0
 */

public class ListeClientDAO {
	
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
     * Le constructeur de la liste de client qui prend en paramètre une référence vers la connexion à la base de données.
     * 
     */
	public ListeClientDAO(Connection cn) {
			this.cn = cn;
		}
	
	/**
     * Méthode permettant de récupérer la liste de clients rattachés à un conseiller. Le paramètre est l'identifiant du conseiller.
     * 
     * @return La liste des clients rattachés à un conseiller. 
     * 
     */	
	public List<ListeClient> getListeClient(int id) {
		List<ListeClient> listeClientRattache = new ArrayList<>();
		ListeClient clientRattache;
		try {
			String requete = "SELECT * FROM listeclient WHERE idconseiller = ?";			
			this.pst = this.cn.prepareStatement(requete);
			this.pst.setInt(1, id);
			this.rs = pst.executeQuery();
			if (this.rs == null) {
				return null;
			} else {				
				while (rs.next()) {
					System.out.println(id);
					clientRattache = new ListeClient();
					clientRattache.setId(rs.getInt("id"));
					clientRattache.setIdConseiller(rs.getInt("idconseiller"));
					clientRattache.setNomConseiller(rs.getString("nomconseiller"));
					clientRattache.setIdClient(rs.getInt("idclient"));
					clientRattache.setNomClient(rs.getString("nomclient"));
					listeClientRattache.add(clientRattache);
				}
			}
			return listeClientRattache;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
