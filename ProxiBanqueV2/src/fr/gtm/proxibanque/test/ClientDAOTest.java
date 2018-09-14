package fr.gtm.proxibanque.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.gtm.proxibanque.dao.ClientDAO;
import fr.gtm.proxibanque.dao.configuration.GestionConnexionBDD;
import fr.gtm.proxibanque.domaine.Client;

public class ClientDAOTest {

	private GestionConnexionBDD bdd;
	private ClientDAO daoClient;
	private Client clientTest1;
	private Client clientTest2;
	private List<Client> listeClientTest1;
	private List<Client> listeClientTest2;

	@Before
	public void init() {
		this.bdd = new GestionConnexionBDD();
		this.bdd.connexionBDD();
		this.daoClient = new ClientDAO(bdd.getConnexion());
		this.clientTest1 = new Client();
		this.clientTest1.setId(2);
		this.clientTest1.setNom("ROBLIN");
		this.clientTest1.setPrenom("Christophe");
		this.clientTest1.setAdresse("LOS ANGELES, 7 rue mario bros");
		this.clientTest1.setCourriel("christophe@gtm.fr");
		this.clientTest2 = new Client();
		this.listeClientTest1 = new ArrayList<>();
		this.listeClientTest2 = new ArrayList<>();
	}

	// Méthode de test permettant de vérifier qu'un enregistrement existe dans la table Client.
	@Test
	public void testVerificationExistenceClient() {
		this.clientTest2 = this.daoClient.getClientById(2);
		assertEquals("Echec - Le profil n'existe pas dans la base de données", this.clientTest1, this.clientTest2);
	}

	// Méthode de test permettant de vérifier qu'un enregistrement existe dans la table Client.
	@Test
	public void testVerificationUpdateClient() {
		this.clientTest2.setId(2);
		this.clientTest2.setNom("ROBLIN");
		this.clientTest2.setPrenom("Christophe");
		this.clientTest2.setAdresse("LOS ANGELES, 7 rue mario bros");
		this.clientTest2.setCourriel("christophe@gtm.fr");
		this.daoClient.updateClient(this.clientTest2);
		assertNotEquals("Echec - Le profil n'a pas été mis à jour dans la base de données", this.clientTest1,
				this.clientTest2);
	}

	// Méthode de test permettant de vérifier que plusieurs enregistrements existent dans la table Client.
	@Test
	public void testVerificationExistenceTousClient() {
		this.listeClientTest1.add(this.daoClient.getClientById(1));
		this.listeClientTest1.add(this.daoClient.getClientById(2));
		this.listeClientTest1.add(this.daoClient.getClientById(3));
		this.listeClientTest1.add(this.daoClient.getClientById(4));
		this.listeClientTest1.add(this.daoClient.getClientById(5));

		this.listeClientTest2 = this.daoClient.getAllClient();
		assertThat("Echec - La liste de tous les utilisateurs n'existe pas dans la base de données",
				this.listeClientTest2, is(this.listeClientTest1));
	}

	// Méthode de test permettant de vérifier le nombre d'enregistremet dans la table Client.
	@Test
	public void testVerificationTailleListeClient() {
		this.listeClientTest1 = this.daoClient.getAllClient();
		assertThat("Echec - La liste de tous les utilisateurs n'existe pas dans la base de données",
				this.listeClientTest1.size(), is(5));
	}

	@After
	public void destroy() {
		this.bdd.deconnexionBDD();
	}

}
