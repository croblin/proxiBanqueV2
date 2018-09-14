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
import fr.gtm.proxibanque.domaine.CompteCourant;
import fr.gtm.proxibanque.domaine.CompteEpargne;
import fr.gtm.proxibanque.service.ProxiBanqueService;

/**
 * CompteClientServlet est la classe qui gère les requêtes et réponses liés à l'affichage des comptes d'un client.
 * 
 * @author croblin / tyahyaoui / amialoundama
 * @version 1.0
 */
@WebServlet("/compteclient")
public class CompteClientServlet extends HttpServlet {
	private static final long serialVersionUID = 3L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompteClientServlet() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idConseiller = Integer.parseInt(request.getParameter("idConseiller"));
		int idClient = Integer.parseInt(request.getParameter("idClient"));
		
		GestionConnexionBDD bdd = new GestionConnexionBDD();
		bdd.connexionBDD();
		
		RequestDispatcher dispatcher;
		
		HttpSession session = request.getSession();
		
		ProxiBanqueService service = new ProxiBanqueService(bdd.getConnexion());
		
		CompteCourant compteCourant = service.recupererCompteCourant(idClient);
		CompteEpargne compteEpargne = service.recupererCompteEpargne(idClient);
		
		if(compteCourant.getId() != 0) {
			session.setAttribute("compteCourant", compteCourant);
		}
		if(compteEpargne.getId() != 0) {
			session.setAttribute("compteEpargne", compteEpargne);
		}
		session.setAttribute("idConseiller", idConseiller);
		dispatcher = request.getRequestDispatcher("compteclient.jsp");
		dispatcher.forward(request, response);
	}

}
