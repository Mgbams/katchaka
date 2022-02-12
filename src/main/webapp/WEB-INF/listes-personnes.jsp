<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Ajouter l'entete -->
<jsp:include page="entete.jsp" />

<div class="container">
	<div class="mt-2">
		<p>
			Connectée: ${sessionScope["personne"].pseudo} – solde:
			${sessionScope["personne"].nbCredits} crédits <a href="deconnexion"
				class="ml-3">Déconnexion</a>
		</p>
	</div>
	<h2 class="my-3">LISTE DES PERSONNES</h2>
	<div
		class="mt-3 d-flex flex-row justify-content-between global-filtre-container">
		<div class="filtres">Filtres</div>
		<div class="filtres-interets">Intérêt</div>
		<div class="ville-filtres">
			<form action="liste-personnes" method="GET"
				class="d-flex flex-row justify-content-between">
				<div>
					<select id="interet" name="interet" class="custom-select"
						title="Maintenez ctrl et sélectionnez avec la souris">
						<option disabled selected="${defaultSelect}">Veuillez
							Choisir</option>
						<c:forEach var="interet" items="${interets}">
							<option value="${interet.id}">${interet.nom}</option>
						</c:forEach>
					</select>
				</div>
				<div>Ville</div>
				<div>
					<select id="ville" class="form-control custom-select" name="ville">
						<option disabled selected="${defaultSelect}">Veuillez
							choisir</option>

						<c:forEach var="singleVille" items="${villes}">
							<option value="${singleVille.id}">${singleVille.nom}</option>
						</c:forEach>
					</select>
				</div>
				<div>
					<input type="hidden" name="filtrer" value="true" /> <input
						type="submit" value="Filtre" class="btn btn-primary" />
				</div>
			</form>
		</div>

	</div>

	<div class="mt-5">
		<table class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th scope="col">
						<form action="liste-personnes" method="GET"
							class="ml-2 d-flex flex-row">
							<input type="hidden" name="pseudoSort" value="pseudoSort" /> <span
								class="mr-2">Pseudo</span>
							<button type="submit" class="pseudo-button">
								<i class="bi bi-arrow-down"></i>
							</button>
						</form>
					</th>
					<th scope="col">
						<form action="liste-personnes" method="GET"
							class="ml-2 d-flex flex-row">
							<input type="hidden" name="statutSort" value="statutSort" /> <span
								class="mr-2">Statut</span>
							<button type="submit" class="statut-button">
								<i class="bi bi-arrow-down"></i>
							</button>
						</form>
					</th>
					<th scope="col">
						<form action="liste-personnes" method="GET"
							class="ml-2 d-flex flex-row">
							<input type="hidden" name="villeSort" value="villeSort" /> <span
								class="mr-2">Ville</span>
							<button type="submit" class="ville-button">
								<i class="bi bi-arrow-down"></i>
							</button>
						</form>
					</th>
					<th scope="col">Opérations</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="personne" items="${listesDesPersonnes }">
					<tr>
						<th scope="row">${personne.pseudo}</th>
						<td>${personne.statut.nom}</td>
						<td>${personne.ville.nom}</td>
						<td class="d-flex flex-row">
							<form action="personne" method="GET" class="mr-5 d-flex flex-row">
								<input type="hidden" name="personneId" value="${personne.id}" />
								<button type="submit" class="pseudo-button">
									<i class="bi bi-eye mr-2"></i>Voir
								</button>
							</form>
							<form action="listes-invitations" method="POST"
								class="d-flex flex-row">
								<input type="hidden" name="inviterId" value="${personne.id}" />
								<button type="submit" class="pseudo-button">
									<i class="bi bi-send mr-2"></i>Inviter
								</button>
							</form>
						</td>
					</tr>
				</c:forEach>

				<c:if test="${empty listesDesPersonnes }">
					<td colspan="4" class="text-center text-warning"><b>OUPS!
							Désolé, il n'y a pas de données pour cette recherche.</b></td>
				</c:if>
			</tbody>
		</table>
	</div>

	<div
		class="buttonDeNavigation d-flex flex-row justify-content-between mt-5 mb-3">
		<form action="liste-personnes" method="GET">
			<input type="hidden" name="premier" value="premier" /> <input
				type="hidden" name="page" value="1" />
			<button class="btn btn-success" type="submit">Premier</button>
		</form>

		<form action="liste-personnes" method="GET">
			<input type="hidden" name="precedent" value="precedent" />
			<c:if test="${page != 1}">
				<input type="hidden" name="page" value="${page - 1}" />
			</c:if>
			<button class="btn btn-outline-info">Précédent</button>
		</form>

		<form action="liste-personnes" method="GET">
			<input type="hidden" name="suivant" value="suivant" />
			<c:if test="${page lt noOfPages}">
				<input type="hidden" name="page" value="${page + 1}" />
			</c:if>
			<button class="btn btn-info">Suivant</button>
		</form>

		<form action="liste-personnes" method="GET">
			<input type="hidden" name="dernier" value="dernier" />
			<c:if test="${page lt noOfPages}">
				<input type="hidden" name="page" value="${noOfPages}" />
			</c:if>
			<button class="btn btn-outline-success">Dernier</button>
		</form>
	</div>

	<div>
		<p>Personnes de ${startNombreDeChaquePage} à ${endNombreDeChaquePage} sur
			${totalDePersonneDisponible}</p>
	</div>

	<a href="listes-invitations">Liste des invitations</a>

</div>

<!-- Ajouter l'entete -->
<jsp:include page="footer.jsp" />