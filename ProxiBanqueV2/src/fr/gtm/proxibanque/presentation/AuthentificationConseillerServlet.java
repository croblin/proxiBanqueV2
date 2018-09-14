package fr.gtm.proxibanque.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.gtm.proxibanque.dao.configuration.GestionConnexionBDD;
import fr.gtm.proxibanque.domaine.Client;
import fr.gtm.proxibanque.domaine.Conseiller;
import fr.gtm.proxibanque.domaine.ListeClient;
import fr.gtm.proxibanque.service.ProxiBanqueService;

/**
 * AuthentificationServlet est la classe qui gère les requêtes et réponses liés à l'authenfication d'un conseiller. 
 * 
 * @author croblin / tyahyaoui / amialoundama
 * @version 1.0
 */
@WebServlet("/connexion")
public class AuthentificationConseillerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * Le constructeur par défaut de la servlet liée à l'authentification d'un conseiller.
     * 
     */
    public AuthentificationConseillerServlet() {
       
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String motDePasse = request.getParameter("motdepasse");
		
		GestionConnexionBDD bdd = new GestionConnexionBDD();
		bdd.connexionBDD();
		
		RequestDispatcher dispatcher;
		
		HttpSession session = request.getSession();
		
		ProxiBanqueService service = new ProxiBanqueService(bdd.getConnexion());
		Conseiller conseiller = service.verifierAuthentificationConseiller(login, motDePasse);
		
		
		if(conseiller.getId() == 0) {
			
			session.setAttribute("echecConnexion", "Echec authentification Conseiller. Essayer à nouveau.");
			dispatcher = request.getRequestDispatcher("connexion.jsp");
			
			
		}
		else {
			
			
			
			session.setAttribute("conseiller", conseiller);
			
			List<Client> listeClient = new ArrayList<>();
			List<ListeClient> listeClientRattache = service.recupererListeClient(conseiller.getId());
			
			for(ListeClient clientRattache: listeClientRattache) {
				listeClient.add(service.recupererClient(clientRattache.getIdClient()));
			}
			session.setAttribute("listeClient", listeClient);	
			dispatcher = request.getRequestDispatcher("accueil.jsp");
		}		
		bdd.deconnexionBDD();			
		dispatcher.forward(request, response);
	}
}
