<%@ include file="inc/haut.jsp" %>

<div align="center">

<h1>Client : <c:out value="${compteCourant.nomClient }"></c:out></h1>
<h3>Compte Courant</h3>
<ul>
	<li>

		<c:out value="${compteCourant.nomClient }"></c:out>
		<br />
		<c:out value="${compteCourant.solde }"></c:out>
		<br />
		<c:out value="${compteCourant.decouvert }"></c:out>
	</li>
</ul>


<h3>Compte Epargne</h3>
<ul>
	<li>

		<c:out value="${compteEpagne.nomClient }"></c:out>
		<br />
		<c:out value="${compteEpargne.solde }"></c:out>
	</li>
</ul>

</div>


<%@ include file="inc/bas.jsp" %>