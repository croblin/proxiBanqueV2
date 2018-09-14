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
import fr.gtm.proxibanque.domaine.ListeClient;
import fr.gtm.proxibanque.service.ProxiBanqueService;

/**
 * VirementServlet est la classe qui gère les requêtes et réponses liés aux virements de compte à compte.
 * 
 * @author croblin / tyahyaoui / amialoundama
 * @version 1.0
 */
@WebServlet("/virement")
public class VirementServlet extends HttpServlet {
	private static final long serialVersionUID = 4L;
       
	/**
     * Le constructeur par défaut de la servlet liée aux virements de compte à compte.
     * 
     */
    public VirementServlet() {
       
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
		List<Client> listeClient = service.recupererTousClient();
		session.setAttribute("idConseiller", idConseiller);
		session.setAttribute("client", client);
		session.setAttribute("listeTousClient", listeClient);
		bdd.deconnexionBDD();
		dispatcher = request.getRequestDispatcher("virement.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idConseiller = Integer.parseInt(request.getParameter("idConseiller")); 
		float montant = Float.parseFloat(request.getParameter("montant"));
		int idClientDebit = Integer.parseInt(request.getParameter("idClientDebit"));
		int idClientCredit = Integer.parseInt(request.getParameter("idClientCredit"));
		String typeCompteDebit = request.getParameter("typeCompteDebit");
		String typeCompteCredit = request.getParameter("typeCompteCredit");
		
		GestionConnexionBDD bdd = new GestionConnexionBDD();
		bdd.connexionBDD();
		
		RequestDispatcher dispatcher;
		
		HttpSession session = request.getSession();
		
		ProxiBanqueService service = new ProxiBanqueService(bdd.getConnexion());
		
		if(service.debiter(idClientDebit, montant, typeCompteDebit)) {
			service.crediter(idClientCredit, montant, typeCompteCredit);
			session.setAttribute("reussiteVirement", "Le virement a bien été effectué.");
			session.setAttribute("echecVirement", "");
		}
		else {
			session.setAttribute("reussiteVirement", "");
			session.setAttribute("echecVirement", "Le virement n'a pas été effectué car le montant proposé dépasse le seuil autorisé.");			
		}
		session.setAttribute("idConseiller", service.recupererConseiller(idConseiller));
		List<Client> listeClient = new ArrayList<>();
		List<ListeClient> listeClientRattache = service.recupererListeClient(idConseiller);
		for(ListeClient clientRattache: listeClientRattache) {
			listeClient.add(service.recupererClient(clientRattache.getIdClient()));
		}
		session.setAttribute("listeClient", listeClient);
		bdd.deconnexionBDD();
		dispatcher = request.getRequestDispatcher("accueil.jsp");
		dispatcher.forward(request, response);
	}
}
