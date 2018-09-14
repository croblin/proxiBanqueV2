<%@ include file="inc/haut.jsp" %>
<c:if test = "${conseiller.nom != null }">
 <jsp:forward page="accueil.jsp"/>
 
 </c:if>

<div align="center">
<H1>Connexion au compte</H1>

<p><c:out value="${echecConnexion }"></c:out></p>


<form method="POST" action="connexion">



<label for="login">
Login : 
</label>
<input id="login" type="TEXT" name="login" />

<br />
<label for="motdepasse">
Mot de passe :
</label>
<input id="motdepasse" type="PASSWORD" name="motdepasse"/>

<br />
<input type="SUBMIT" value="Valider" />
</form>

</div>

<%@ include file="inc/bas.jsp" %>

