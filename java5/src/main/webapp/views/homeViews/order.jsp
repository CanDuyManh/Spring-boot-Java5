
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <div class="container-fluid bg-light py-5">
    <div class="col-md-6 m-auto text-center">
        <h1 class="h1">My Order</h1>
        <p">
            Hãy xem lại những thứ mày đã mua đi , mày đã dùng đến nó lần nào chưa ?
        </p>
    </div>
</div>
    <main>
      <div class="container-sm">
        <table class="table">
          <thead>
            <tr>
              <th scope="col">#ID</th>
              <th scope="col">Address</th>
              <th scope="col">Create Date</th>
              <th scope="col">Account</th>
              <th scope="col">Status</th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${lst}" var="item">
              <tr id="${item.id}">
                <th scope="row">${item.id}</th>
                <td class="address">${item.address}</td>
                <td class="createDate">${item.createDate}</td>
                <td class="account">${item.account.name}</td>
                <td class="status">${item.status}</td>
                <td><button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"
                    onClick="editUS(${item.id})">View</button></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>


        <div class="row">
          <div class="col-7">
            <!-- <button type="button" class="btn btn-link" onclick="loadVideo()">xem them</button> -->
          </div>
        </div>

        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">View Order</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <form>
                  <div class="mb-3">
                    <label for="orderAccount" class="col-form-label">Account: &nbsp</label>
                    <label id="orderAccount">
                  </div>
                  <div class="mb-3">
                    <label for="orderAddress" class="col-form-label">Address: &nbsp</label>
                    <label id="orderAddress">
                  </div>
                  <div class="mb-3">
                    <label for="orderCreateDate" class="col-form-label">Create Date: &nbsp</label>
                    <label id="orderCreateDate">
                  </div>
                  <div class="mb-3">
                    <label for="orderProduct" class="col-form-label">Product: &nbsp</label>
                    <label id="orderProduct">
                  </div>
                  <div class="mb-3">
                    <label for="orderQuantity" class="col-form-label">Quantity: &nbsp</label>
                    <label id="orderQuantity">
                  </div>
                  <div class="mb-3">
                    <label for="orderTotalMoney" class="col-form-label">total money: &nbsp</label>
                    <label id="orderTotalMoney">
                  </div>
                  <div class="mb-3">
                    <label for="orderStatus" class="col-form-label"> status: &nbsp</label>
                    <label id="orderStatus">

                  </div>
                  
                </form>
              </div>
              <div class="modal-footer">
                <button id="closeEdit" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
    <script>
async function editUS(id) {

var address = $("#" + id + " .address").html();
var createDate = $("#" + id + " .createDate").html();
var account = $("#" + id + " .account").html();
var status = $("#" + id + " .status").html();
var product = '';
var totalMoney = 0;
let allQuantity = 0;

var order;
await $.get("/api/order/" + id).then(data => {

    order = data;

})

order.product.map((v,k) => {
    let productOld = product;
    let quantity =  order.orderDetails[k].quantity;
    allQuantity = allQuantity + quantity;
    product = productOld + ',' + v.name + ' x' + quantity;
    totalMoney = totalMoney + order.orderDetails[k].price;
})

$("#orderAccount").html(account)
$("#orderAddress").html(address)
$("#orderCreateDate").html(createDate)
$("#orderProduct").html(product)
$("#orderQuantity").html(allQuantity)
$("#orderTotalMoney").html(totalMoney)
$("#orderStatus").html(status)
}


        
    </script>