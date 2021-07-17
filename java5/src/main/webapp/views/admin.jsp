<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.82.0">
<title>ADMIN</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.0/examples/album/">

<link href="<c:url value = "/views/assets/dist/css/bootstrap.min.css"/>"
	rel="stylesheet">


<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>


</head>
<body>

	<header>
		<div class="collapse bg-dark" id="navbarHeader">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-md-7 py-4">
						<h4 class="text-white">ADMIN</h4>
						<p class="text-muted"></p>
					</div>
					<div class="col-sm-4 offset-md-1 py-4">
						<h4 class="text-white">Manage</h4>
						<ul class="list-unstyled">
							<li><a href="/admin/accountManager" class="text-white">User
									manage</a></li>
							<li><a href="/admin/orderManager" class="text-white">Order
									manage</a></li>
							<li><a href="/admin/productManager" class="text-white">Product
									manage</a></li>
							<li><a href="/admin/categoryManager" class="text-white">Category
									manage</a></li>
							<li><a onclick="loguot()" href="/login"
								class="text-white">Logout</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="navbar navbar-dark bg-dark shadow-sm">
			<div class="container">
				<a href="/code/admin/userManager"
					class="navbar-brand d-flex align-items-center"> <svg
						xmlns="http://www.w3.org/2000/svg" width="20" height="20"
						fill="none" stroke="currentColor" stroke-linecap="round"
						stroke-linejoin="round" stroke-width="2" aria-hidden="true"
						class="me-2" viewBox="0 0 24 24">
						<path
							d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z" />
						<circle cx="12" cy="13" r="4" /></svg> <strong>Admin</strong>
				</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarHeader"
					aria-controls="navbarHeader" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
			</div>
		</div>
	</header>

	<jsp:include page="${view}"></jsp:include>

	<footer class="text-muted py-5">
		<div class="container">
			<p class="float-end mb-1">
				<a href="#">Back to top</a>
			</p>
			<p class="mb-1">Template &copy; Bootstrap, but setup by
				MANHCDPH11373!</p>
		</div>
	</footer>


	<script
		src="<c:url value ="/views/assets/dist/js/bootstrap.bundle.min.js"/>"></script>
	<script>
		function loguot() {
			$.ajax({
				url : '/login',
				type : 'DELETE',
			});
		}
	</script>

</body>
</html>