package fr.gtm.proxibanque.dao.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * GestionConnexionBDD est la classe permettant de se connecter à un SGBD avec l'API JDBC.
 * 
 * @author croblin / tyahyaoui / amialoundama
 * @version 1.0
 */

public class GestionConnexionBDD {
	
	/**
     * L'identifiant du compte.
     * 
     */
	Connection cn;
		
	/**
     * Méthode permettant de se connecter à la base de données MySQL avec son pilote de JDBC MySQL.
     * 
     */	
	public void connexionBDD() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/proxibanque?useSSL=false", "root", "");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Méthode permettant de récupérer l'instance de la classe Connection utilisée.
     * 
     * @return La connexion à la base de données
     * 
     */	
	public Connection getConnexion() {
		return this.cn;
	}
	
	/**
     * Méthode permettant de se déconnecter de la base de données.
     * 
     */	
	public void deconnexionBDD() {
		try {
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
