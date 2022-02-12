<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Ajouter l'entete -->
<jsp:include page="entete.jsp" />

<div class="container mt-3">
	<a href="index" class="toPageIndex"><i
		class="bi bi-arrow-left-circle"></i></a>
	<div class="card card-inscription">
		<div class="card-header text-center">Inscription</div>
		<div class="card-body">
			<form action="inscription" method="POST">

				<div>
					<label for="pseudo">Pseudo</label> <input type="text"
						class="form-control" id="pseudo" placeholder="Pseudo"
						name="pseudo" value="${sessionScope.pseudo}"> <small
						class="mb-3 errorMessages"> <c:if
							test="${sessionScope.inscrireButtonClicked eq true }">
					 	${sessionScope.errors["pseudoError"]}
					</c:if>
					</small>
				</div>

				<div class="form-row mt-3">
					<div class="form-group col-md-6">
						<label for="email">Email</label> <input type="email"
							class="form-control" id="email" name="email"
							value="${sessionScope.email}" placeholder="Saisir votre Email">
						<small class="mb-3 errorMessages"> <c:if
								test="${sessionScope.inscrireButtonClicked eq true }">
					 		${sessionScope.errors["emailError"]}
						</c:if>
						</small>
					</div>

					<div class="form-group col-md-6">
						<label for="motDePasse">Mot de Passe</label> <input
							type="password" class="form-control" id="motDePasse"
							name="motDePasse" value="${sessionScope.motDePasse}"
							placeholder="Saisir votre Mot de Passe"> <small
							class="errorMessages"> <c:if
								test="${sessionScope.inscrireButtonClicked eq true }">
					 		${(sessionScope.errors["motDePasseError"])} 
						</c:if>
						</small>
					</div>

				</div>

				<div class="d-flex flex-column genre-container jumbotron">
					<div class="form-group text-center">
						<label for="ville">Ville</label> <select id="ville"
							class="form-control custom-select" name="ville">
							<option disabled>Veuillez sélectionner</option>
							<c:forEach var="ville" items="${villes}">
								<option value="${ville.id}" selected="${sessionScope.ville}">${ville.nom}</option>
							</c:forEach>
						</select> <small class="errorMessages"> <c:if
								test="${sessionScope.inscrireButtonClicked eq true }">
					 		${(sessionScope.errors["villeError"])}
						</c:if>
						</small>
					</div>

					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="genre">Genre</label> <select id="genre" name="genre"
								class="form-control custom-select">
								<option disabled>Veuillez sélectionner</option>
								<c:forEach var="genre" items="${genres}">
									<option value="${genre.id}" selected="${sessionScope.genre}">${genre.nom}</option>
								</c:forEach>
							</select> <small class="errorMessages"> <c:if
									test="${sessionScope.inscrireButtonClicked eq true }">
					 			${(sessionScope.errors["genreError"])} 
							</c:if>
							</small>
						</div>

						<div class="form-group col-md-6">
							<label for="genreRecherche">Genre recherché</label> <select
								name="genreRecherche" id="genreRecherche"
								class="form-control custom-select">
								<option selected disabled>Veuillez sélectionner</option>
								<c:forEach var="genresRecherche" items="${genresRecherche}">
									<option value="${genresRecherche.id}"
										selected="${sessionScope.genresRecherche}">${genresRecherche.nom}</option>
								</c:forEach>
							</select> <small class="errorMessages"> <c:if
									test="${sessionScope.inscrireButtonClicked eq true }">
					 			${(sessionScope.errors["genresRechercheError"])} 
							</c:if>
							</small>
						</div>

					</div>

					<div class="form-row">

						<div class="form-group col-md-6">
							<label for="statut">Statut</label> <select id="statut"
								name="statut" class="form-control custom-select">
								<option disabled>Veuillez sélectionner</option>
								<c:forEach var="statut" items="${statuts}">
									<option value="${statut.id}" selected="${sessionScope.statut}">${statut.nom}</option>
								</c:forEach>
							</select> <small class="errorMessages"> <c:if
									test="${sessionScope.inscrireButtonClicked eq true }">
					 			${(sessionScope.errors["statutError"])}
							</c:if>
							</small>
						</div>

						<div class="form-group col-md-6">
							<label for="interet">Intérêt(s)</label> <select id="interet"
								name="interet" multiple class="custom-select"
								title="Maintenez ctrl et sélectionnez avec la souris">
								<option disabled>Veuillez sélectionner</option>
								<c:forEach var="interet" items="${interets}">
									<option value="${interet.id}"
										selected="${sessionScope.interets}">${interet.nom}</option>
								</c:forEach>
							</select> <small class="errorMessages"> <c:if
									test="${sessionScope.inscrireButtonClicked eq true }">
					 			${(sessionScope.errors["interetError"])} 
							</c:if>
							</small>
						</div>

					</div>

					<div class="form-group text-center bio-container mt-2">
						<textarea id="bio" name="bio" class="text-center"
							placeholder="Votre saisir doit comporter au moins 20 caractères"
							rows="8" cols="50">${sessionScope.bio}</textarea>
						<small class="errorMessages"> <c:if
								test="${sessionScope.inscrireButtonClicked eq true }">
					 		${sessionScope.errors["bioError"]} 
						</c:if>
						</small>
					</div>

				</div>


				<div class="form-group mt-3 dateNaissance-wrapper">
					<label for="dateDeNaissance">Date de naissance</label> <input
						type="date" class="form-control" name="dateDeNaissance"
						value="${sessionScope.dateDeNaissance}" id="dateDeNaissance"
						placeholder="JJ/mm/aaaa"> <small class="errorMessages">
						<c:choose>

							<c:when
								test="${sessionScope.inscrireButtonClicked eq true && sessionScope.errors['horsDeRangeDateDeNaissanceError'] != null }">
								${(sessionScope.errors["horsDeRangeDateDeNaissanceError"])}
							</c:when>

							<c:when
								test="${sessionScope.inscrireButtonClicked eq true && sessionScope.errors['dateDeNaissanceError'] != null}"> 
								${(sessionScope.errors["dateDeNaissanceError"])}
							</c:when>
							
						</c:choose>
					</small>
				</div>

				<div class="form-group">
					<div class="form-check">
						<label class="form-check-label mr-5" for="fumeur"> Fumeur
						</label> &nbsp; <input class="form-check-input" type="checkbox"
							id="fumeur" name="fumeur"
							value="${sessionScope.inscrireButtonClicked eq true && sessionScope.fumeur }">
					</div>
				</div>

				<div class="text-center">
					<input type="hidden" name="inscrireButtonClicked"
						value="inscrireButtonClicked" /> <input type="submit"
						class="btn btn-primary" value="S'inscrire" />
				</div>

			</form>
		</div>
	</div>

</div>

<!-- Ajouter l'entete -->
<jsp:include page="footer.jsp" />