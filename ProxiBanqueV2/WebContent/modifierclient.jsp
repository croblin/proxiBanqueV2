<%@ 

include file="inc/haut.jsp"



 %>

<br />
<br />
<div align="center">

<h3>Client : <c:out value="${client.nom}"></c:out></h3>


<h4>Modifier : </h4>
<form method="POST" action="modificationclient">

Nom :
<input id="nom" type="TEXT" name="nom" value="<c:out value="${client.nom}"></c:out>" />

<br /><br />


Prenom :

<input id="prenom" type="TEXT" name="prenom" value="<c:out value="${client.prenom}"></c:out>" />

<br /><br />

Adresse :
<input id="adresse" type="TEXT" name="adresse" value="<c:out value="${client.adresse}"></c:out>" />
<br /><br />

Courriel :
<input id="courriel" type="TEXT" name="courriel" value="<c:out value="${client.courriel}"></c:out>" />

<input type="HIDDEN" name="idConseiller" value="<c:out value="${conseiller.id}"></c:out>" />


<input type="HIDDEN" name="id" value="<c:out value="${client.id}"></c:out>" />

<br /><br />

<input type="SUBMIT" value="Valider" />
</form>

</div>

<%@ include file="inc/bas.jsp" %>