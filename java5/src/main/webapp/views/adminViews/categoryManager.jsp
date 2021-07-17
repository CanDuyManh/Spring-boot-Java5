<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<style>
.avatar {
  vertical-align: middle;
  width: 35px;
  height: 35px;
 
}</style>
<main>
 <div class="container-sm">
 	<table class="table">
  <thead>
    <tr>
      <th scope="col">#ID</th>
      <th scope="col">Name</th>
      <th scope="col"></th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${lst}" var="item" >
    <tr id="${item.id}">
      <th scope="row">${item.id}</th>
      <td class="name">${item.name}</td>
      <td><button type="button" class="btn btn-danger" onclick="deleteUs(${item.id})" >Delete</button></td>
      <td><button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onClick="editUS(${item.id})" >Edit</button></td>
    </tr>
    </c:forEach>
  </tbody>
</table>

<div class="row">
    <div class="col-7">
      <button type="button" class="btn btn-link" onclick="loadVideo()">xem them</button>
    </div>
    <div class="col-2 d-grid gap-2 mx-auto">     
        
        <button type="button" onclick="editUS('')" class="btn btn-success"  data-bs-toggle="modal" data-bs-target="#exampleModal" >Create</button>
    </div>
  </div>


<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Edit Account</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form>
          <div class="mb-3">
            <label for="categoryID" class="col-form-label">ID:</label>
            <label  id="categoryID" class="col-form-label">
          </div>
          <div class="mb-3">
            <label for="categoryName" class="col-form-label">Name:</label>
            <input type="text" class="form-control" id="categoryName">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button id="closeEdit" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button id="saveEdit" type="button" class="btn btn-primary" onclick="saveEdit()">Send message</button>
      </div>
    </div>
  </div>
</div>
 </div>
</main>
<script>
</script>
<script src="<c:url value = "/views/adminViews/categoryManager.js"/>"></script>
