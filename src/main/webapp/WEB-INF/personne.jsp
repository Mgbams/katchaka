<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Ajouter l'entete -->
<jsp:include page="entete.jsp" />

<div class="container mt-3">
	<a href="liste-personnes" class="toPageIndex"><i
		class="bi bi-arrow-left-circle"></i>
	</a>
	<div class="top-image card-img-top mt-3">
		<img src="images/dating2.jpeg" class="profile-image-img" />
	</div>

	<div class="d-flex flex-row justify-content-between mt-5">

		<div class="card">

			<div class="card-body">
				<div>
					<div class="profileTitleInfo">
						<h3>${personneInfo.pseudo}</h3>
						<p>
							<i class="bi bi-geo-alt mr-3"></i> ${personneInfo.ville.nom}
						</p>

						<p>
							<i class="bi bi-people"></i> ${personneInfo.statut.nom}
						</p>

						<p>Âge: ${age} years</p>
						<div class="profile-bio">
							<p>${personneInfo.bio}</p>
						</div>

						<p>Fumeur: ${personneInfo.estFumeur}</p>

						<p>
							<i class="bi bi-person"></i>Genre: &nbsp;<b class="genre-nom">${personneInfo.genre.nom}</b>
						</p>
					</div>

				</div>
			</div>
		</div>

		<div class="inviterDeProfile text-center">
			<form action="listes-invitations" method="POST">
				<input type="hidden" name="inviterId" value="${personneInfo.id}" />
				<button class="btn btn-success">Inviter</button>
			</form>

			<div class="mt-4 text-center">
				<h3>Intérêt</h3>

				<ul class="list-group">
					<c:forEach var="interet" items="${personneInterets.interets}">
						<li class="list-group-item">${interet.nom}</li>
					</c:forEach>
				</ul>

			</div>

			<div class="mt-4 text-center">
				<h3>Récherche Info</h3>

				<ul class="list-group">
					<li class="list-group-item"><i class="bi bi-person"></i>
						GenreRecherché: &nbsp;<b  class="genre-nom">${personneInfo.genreRecherche.nom}</b></li>
				</ul>

			</div>
		</div>

	</div>

</div>

<!-- Ajouter l'entete -->
<jsp:include page="footer.jsp" />