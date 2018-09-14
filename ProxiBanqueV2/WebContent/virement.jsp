<%@ include file="inc/haut.jsp" %>


<div align="center">
<H1>Virement</H1>


<form method="POST" action="virement">



<label for="solde">
Montant à virer :

<input id="solde" type="TEXT" name="montant" />
</label>
<br />

<label for="listeClient">
Type de compte à débiter :
	
		<input type="HIDDEN" name="idConseiller" value="<c:out value="${conseiller.id}"></c:out>" />
		<input type="HIDDEN" name="idClientDebit" value="<c:out value="${client.id}"></c:out>" />
		
		<input type="radio" id="courantDebit" name="typeCompteDebit" value="comptecourant" checked />
	<label for="courantDebit">Compte courant </label>
	
		<input type="radio" id="epargneDebit" name="typeCompteDebit" value="compteepargne"  />
	<label for="epargneDebit">Compte epargne </label>
</label>

<br />

<label for="listeClient">
Client à virer :
<select id="listeClient" name="idClientCredit">
	<c:forEach items="${listeTousClient}" var="clients">
	
		<option value="${clients.id}">
			<c:out value="${clients.nom }"></c:out>
			<c:out value="${clients.prenom }"></c:out>
			</option>
	
	</c:forEach>

</select>
</label>
<br />
<label for="listeClient">
Type de compte à créditer :
	
		<input type="radio" id="courantCredit" name="typeCompteCredit" value="comptecourant" checked />
	<label for="courantCredit">Compte courant </label>
	
		<input type="radio" id="epargneCredit" name="typeCompteCredit" value="compteepargne"  />
	<label for="epargneCredit">Compte epargne </label>
</label>
<br />



<br />
<input type="SUBMIT" value="Valider" />
</form>

</div>

<%@ include file="inc/bas.jsp" %>