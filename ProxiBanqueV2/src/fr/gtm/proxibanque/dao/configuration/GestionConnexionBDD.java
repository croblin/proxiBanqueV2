package fr.gtm.proxibanque.dao.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * GestionConnexionBDD est la classe permettant de se connecter � un SGBD avec l'API JDBC.
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
     * M�thode permettant de se connecter � la base de donn�es MySQL avec son pilote de JDBC MySQL.
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
     * M�thode permettant de r�cup�rer l'instance de la classe Connection utilis�e.
     * 
     * @return La connexion � la base de donn�es
     * 
     */	
	public Connection getConnexion() {
		return this.cn;
	}
	
	/**
     * M�thode permettant de se d�connecter de la base de donn�es.
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
