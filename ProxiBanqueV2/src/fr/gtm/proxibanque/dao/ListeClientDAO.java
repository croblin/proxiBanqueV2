package fr.gtm.proxibanque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.gtm.proxibanque.domaine.ListeClient;

/**
 * ListeClientDAO est la classe permettant d'envoyer des requ�tes vers la table ListeClient.
 * 
 * @author croblin / tyahyaoui / amialoundama
 * @version 1.0
 */

public class ListeClientDAO {
	
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
     * Le constructeur de la liste de client qui prend en param�tre une r�f�rence vers la connexion � la base de donn�es.
     * 
     */
	public ListeClientDAO(Connection cn) {
			this.cn = cn;
		}
	
	/**
     * M�thode permettant de r�cup�rer la liste de clients rattach�s � un conseiller. Le param�tre est l'identifiant du conseiller.
     * 
     * @return La liste des clients rattach�s � un conseiller. 
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
