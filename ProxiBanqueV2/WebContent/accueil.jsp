<%@ include file="inc/haut.jsp" %>


<div align="center">
<H1>Accueil</H1>

 <c:if test = "${conseiller.nom == null }">
 <jsp:forward page="connexion.jsp"/>
 
 </c:if>

Vous êtes connecté !

<p><c:out value="${reussiteVirement }"></c:out></p>
<p><c:out value="${echecVirement }"></c:out></p>


<h3>Conseiller :</h3>
<ul>
	<li>
		
		<c:out value="${conseiller.nom }"></c:out>
		<c:out value="${conseiller.prenom }"></c:out>
	</li>
</ul>


    

<h3>Votre liste de clients :</h3>



<ul>
	<li>
	

		<table>
			<tr>
			<th>Client</th><th>Consulter</th><th>Modifier</th><th>Virement</th>
			</tr>
			<c:forEach items="${listeClient}" var="client">
			<tr>
			<td>	
				
				<c:out value="${client.nom }"></c:out>			
				
				<c:out value="${client.prenom }"></c:out>
			</td>
			
			<td><a href="compteclient?idClient=<c:out value="${client.id }"></c:out>&idConseiller=<c:out value="${conseiller.id }"></c:out>">Consulter</a></td>
			<td><a href="modificationclient?idClient=<c:out value="${client.id }"></c:out>&idConseiller=<c:out value="${conseiller.id }"></c:out>">Modifier</a></td>
			<td><a href="virement?idClient=<c:out value="${client.id }"></c:out>&idConseiller=<c:out value="${conseiller.id }"></c:out>">Virement</a></td>
			</tr>
			</c:forEach>
			
		</table>
		
	</li>
</ul>





</div>

<%@ include file="inc/bas.jsp" %>