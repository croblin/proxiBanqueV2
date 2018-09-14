<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="fr.gtm.proxibanque.domaine.Conseiller" %>
    <%@ page import="fr.gtm.proxibanque.dao.ConseillerDAO" %>
    
     <%@ page import="fr.gtm.proxibanque.domaine.Client" %>
    <%@ page import="fr.gtm.proxibanque.dao.ClientDAO" %>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Conseiller u = (Conseiller) session.getAttribute("conseiller");
	Client up = (Client) session.getAttribute("client");
	
%>
    
<!DOCTYPE html>
<html lang="en"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">

    <title>Proxy Banque</title>

    <!-- Bootstrap core CSS -->
    <link href="inc/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="inc/css/carousel.css" rel="stylesheet">
	
	<style></style>
  </head>
  <body>

    <header>
      <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <a class="nav-link" href="accueil.jsp">Accueil</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="connexion.jsp">Compte</a>
            </li>
          
          
          <c:if test = "${conseiller.nom != null }">
            <li class="nav-item">
              <a class="nav-link" href="deconnexion.jsp">Déconnexion</a>
            </li>
            <li>
            </c:if>
            
           <c:out value="${conseiller.nom }"></c:out>, <c:out value="${conseiller.prenom }"></c:out>
            </li>
            
            
          </ul>
          
        </div>
      </nav>
    </header>