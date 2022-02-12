<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Ajouter l'entete -->
<jsp:include page="entete.jsp" />

<!-- corps -->
<div class="container mt-4">
	<div class="d-flex flex-row justify-content-between body-contents-wrapper">
		<div class="your-class carousel-conrainer mt-5">
			<div class="round-circle circle-1"></div>
			<div class="round-circle circle-2"></div>
			<div class="round-circle circle-3"></div>
		</div>

		<div>
		
			<!-- Ajouter formulaire de connexion -->
			<jsp:include page="connexion.jsp" />
			

		</div>
	</div>
</div>

<!-- Ajouter le footer -->
<jsp:include page="footer.jsp" />