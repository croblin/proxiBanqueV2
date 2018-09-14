package fr.gtm.proxibanque.presentation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.gtm.proxibanque.dao.configuration.GestionConnexionBDD;
import fr.gtm.proxibanque.domaine.Client;
import fr.gtm.proxibanque.service.ProxiBanqueService;

/**
 * ModificationClientServlet est la classe qui gère les requêtes et réponses liés à la modification d'un client.
 * 
 * @author croblin / tyahyaoui / amialoundama
 * @version 1.0
 */
@WebServlet("/modificationclient")
public class ModificationClientServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
       
	/**
     * Le constructeur par défaut de la servlet liée à la modification d'un client.
     * 
     */
    public ModificationClientServlet() {
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idConseiller = Integer.parseInt(request.getParameter("idConseiller")); 
		
		GestionConnexionBDD bdd = new GestionConnexionBDD();
		bdd.connexionBDD();
		
		RequestDispatcher dispatcher;
		
		HttpSession session = request.getSession();
		
		ProxiBanqueService service = new ProxiBanqueService(bdd.getConnexion());
		
		Client client = service.recupererClient(Integer.parseInt(request.getParameter("idClient")));
		
		session.setAttribute("idConseiller", idConseiller);
		session.setAttribute("client", client);
		
		System.out.println("AAA" + client);
		
		dispatcher = request.getRequestDispatcher("modifierclient.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idConseiller = Integer.parseInt(request.getParameter("idConseiller")); 
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");
		String courriel = request.getParameter("courriel");
		
		Client client = new Client();
		client.setId(id);
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setAdresse(adresse);
		client.setCourriel(courriel);
		
		GestionConnexionBDD bdd = new GestionConnexionBDD();
		bdd.connexionBDD();
		
		RequestDispatcher dispatcher;
		
		HttpSession session = request.getSession();
		
		ProxiBanqueService service = new ProxiBanqueService(bdd.getConnexion());
		service.modifierClient(client);
		
		session.setAttribute("idConseiller", idConseiller);
		session.setAttribute("modificationClient", "Le client a bien été modifié.");
		dispatcher = request.getRequestDispatcher("accueil.jsp");
		dispatcher.forward(request, response);
	}

}
