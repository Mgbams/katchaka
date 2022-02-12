<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	<h2 class="mt-5">LISTE DES INVITATIONS REÇUES</h2>

	<div>
		<table class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th scope="col">
						<form action="" method="GET" class="ml-2 d-flex flex-row">
							<input type="hidden" name="pseudoSort" value="pseudoSort" /> <span
								class="mr-2">Pseudo</span>
						</form>
					</th>
					<th scope="col">
						<form action="" method="GET" class="ml-2 d-flex flex-row">
							<input type="hidden" name="villeSort" value="villeSort" /> <span
								class="mr-2">Ville</span>
						</form>
					</th>
					<th scope="col">
						<form action="" method="GET" class="ml-2 d-flex flex-row">
							<input type="hidden" name="dateEnvoieSort" value="true" /> <span
								class="mr-2">Envoyée le</span>
						</form>
					</th>
					<th scope="col">Opérations</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="invitationsRecu" items="${invitationsRecu}">
					<tr>
						<td scope="row">${invitationsRecu["expediteur"].pseudo }</td>
						<td>${invitationsRecu["expediteur"]["ville"].nom }</td>
						<td>${invitationsRecu.dateEnvoi }</td>
						<td class="d-flex flex-row">
							<form action="listes-invitations" method="GET"
								class="mr-5 d-flex flex-row">
								<input type="hidden" name="accepterId"
									value="${invitationsRecu.expediteur.id}" />

								<c:if
									test="${not empty invitationsRecu.dateLecture && invitationsRecu.estAccepte == true}">
									 Accepté le &nbsp; ${ invitationsRecu.dateLecture}
								</c:if>

								<c:if test="${empty invitationsRecu.dateLecture}">
									<button type="submit" class="pseudo-button">
										<i class="bi bi-eye mr-2"></i>Accepter
									</button>
								</c:if>

							</form>
							
							<form action="listes-invitations" method="GET"
								class="d-flex flex-row">
								<input type="hidden" name="declinerId"
									value="${invitationsRecu.expediteur.id}" />

								<c:if
									test="${not empty invitationsRecu.dateLecture && invitationsRecu.estAccepte == false}">
									Décliné le  &nbsp; ${invitationsRecu.dateLecture}
								</c:if>

								<c:if test="${empty invitationsRecu.dateLecture}">
									<button type="submit" class="pseudo-button">
										<i class="bi bi-send mr-2"></i>Décliner
									</button>
								</c:if>

							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<h2 class="mt-5">LISTE DES INVITATIONS ENVOYÉES</h2>

	<div>
		<table class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th scope="col">
						<form action="" method="GET" class="ml-2 d-flex flex-row">
							<input type="hidden" name="pseudoSort" value="pseudoSort" /> <span
								class="mr-2">Pseudo</span>
						</form>
					</th>
					<th scope="col">
						<form action="" method="GET" class="ml-2 d-flex flex-row">
							<input type="hidden" name="statutSort" value="statutSort" /> <span
								class="mr-2">Statut</span>
						</form>
					</th>
					<th scope="col">
						<form action="" method="GET" class="ml-2 d-flex flex-row">
							<input type="hidden" name="villeSort" value="dateEnvoieSort" />
							<span class="mr-2">Envoyée le</span>
						</form>
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="invitation" items="${invitationsEnvoye}">
					<tr>
						<td scope="row">${ invitation["destinataire"].pseudo }</td>
						<td>${ invitation["destinataire"]["statut"].nom }</td>
						<td>${ invitation.dateEnvoi }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<a href="liste-personnes">Liste des personnes</a>


</div>

<!-- Ajouter l'entete -->
<jsp:include page="footer.jsp" />