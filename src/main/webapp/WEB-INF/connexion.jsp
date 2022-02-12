<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="card connexion-body-wrapper">
	<h5 class="card-header text-center header-page-connexion">Se Connecter</h5>
	<div class="card-body">
		<h5 class="card-title text-center title-page-connexion">Bienvenue à Katchaka</h5>

		<form action="connexion" method="POST" class="px-4">
			<div class="form-group">
				<label for="email">Adresse Mail</label> <input type="email"
					class="form-control" id="email" name="email"
					placeholder="Saisir votre email" value="">
			</div>
			<div class="form-group">
				<label for="motDePasse">Password</label> <input type="password"
					class="form-control" id="motDePasse" placeholder="Password"
					name="motDePasse" value="">
			</div>
			<div class="d-flex flex-row justify-content-between">
				<button type="submit" class="btn btn-primary">Se Connecter</button>
				<input type="hidden" name="connexionButton" value="connexionButton" />
				<a href="inscription" class="btn btn-outline-success">S'inscrire</a>
			</div>
			<small class="loginError">
				<c:if test="${not empty sessionScope.loginError && not empty connexionButton }" >
					 ${sessionScope.loginError}
				</c:if>
			</small>
		</form>

	</div>
</div>