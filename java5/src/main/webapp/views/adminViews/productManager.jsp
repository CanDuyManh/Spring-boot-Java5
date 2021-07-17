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
      <th scope="col">Photo</th>
      <th scope="col">Name</th>
      <th scope="col">Create Date</th>
      <th scope="col">Price</th>
      <th scope="col">Available</th>
      <th scope="col">Category</th>
      <th scope="col"></th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${lst}" var="item" >
    <tr id="${item.id}">
      <th scope="row">${item.id}</th>
      <td class="photo"><img src="/images/${item.photo}" alt="Avatar" class="avatar"></td>
      <td class="name">${item.name}</td>
      <td class="createDate">${item.createDate}</td>
      <td class="price">${item.price}</td>
      <td class="available" >${item.available}</td>
      <td class="category">${item.category.name}</td>
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
        <h5 class="modal-title" id="exampleModalLabel">Edit Product</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form>
          <div class="mb-3">
            <label for="productName" class="col-form-label">Name:</label>
            <input type="text" class="form-control" id="productName">
          </div>
          <div class="mb-3">
            <label for="productPhoto" class="col-form-label mr-3">Photo:</label> 
            <span id="productPhoto" style="margin: 10px;" ></span> 
            <button type="button" class="btn btn-primary btn-sm" onclick="onChageFile()">Change</button>
            <form id="formUpLoadFile" action="/uploadFile" method="post" style="display: none;" enctype="multipart/form-data">
              <input id="fileUpload" type="file" name="photo" accept="image"  style="display: none;"/>
            </form>
            
          </div>
          <div class="mb-3">
            <label for="productPrice" class="col-form-label">Price:</label>
            <textarea  class="form-control" id="productPrice"></textarea >
          </div>
          <div class="mb-3">
            <label for="productCategory" class="col-form-label">Category:</label>
            <select id="productCategory" class="form-select form-select-sm" aria-label=".form-select-sm example">
            </select>
          </div>
          <div class="mb-3">
            <p>Available:</p>
            <div class="form-check form-switch">
            <input class="form-check-input" type="checkbox" id="productAvailable">
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
<script src="<c:url value = "/views/adminViews/productManager.js"/>"></script>
