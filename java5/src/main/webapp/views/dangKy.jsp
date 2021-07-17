<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.82.0">
    <title>Signin Template · Bootstrap v5.0</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sign-in/">

    

    <!-- Bootstrap core CSS -->
<link href="/views/assets/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="/views/signin.css" rel="stylesheet">
  </head>
  <body class="text-center">
    
<main class="form-signin">
  <form method="POST" action="/login/create">
    <img class="mb-4" src="https://source.unsplash.com/random" alt="" width="72" height="57">
    <h1 class="h3 mb-3 fw-normal">Registration</h1>
    <c:if test="${error != null }"> 
      <div class="alert alert-danger" role="alert">
        ${error}
      </div> 
      </c:if> 
    <div class="form-floating mb-1">
      <input type="email" class="form-control" name="email" id="email" placeholder="name@example.com">
      <label for="email">Email</label>
    </div>
    <div class="form-floating mb-1"  >
      <input type="text" class="form-control" name="userName" id="userName" placeholder="name@example.com">
      <label for="userName">User Name</label>
    </div>
    <div class="form-floating mb-1">
      <input type="password" class="form-control" name="password" id="password" placeholder="Password">
      <label for="password">Password</label>
    </div>
    <div class="form-floating mb-1">
      <input type="password" class="form-control" name="Repassword" id="Repassword" placeholder="Repassword">
      <label for="Repassword">Repassword</label>
    </div>
    <div class="checkbox mb-3">
      <div class="row">
        <div class="col text-end"><a href="/login">Login</a></div>
      </div>
    </div>
    <button class="w-100 btn btn-lg btn-primary" type="submit">registration</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p>
  </form>
</main>
  </body>
</html>