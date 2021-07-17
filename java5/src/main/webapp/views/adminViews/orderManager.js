var PAGE = 0;
var selectId = 0;
async function editUS(id) {
  selectId = id;

  // address
  // createDate
  // account
  // status

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

  // orderAccount
  // orderAddress
  // orderCreateDate
  // orderProduct
  // orderQuantity
  // orderTotalMoney
  // orderStatus

  $("#orderAccount").html(account)
  $("#orderAddress").html(address)
  $("#orderCreateDate").html(createDate)
  $("#orderProduct").html(product)
  $("#orderQuantity").html(allQuantity)
  $("#orderTotalMoney").html(totalMoney)
  $("#orderStatus").html(status)
  $("#orderDuyet").prop("checked",status=="da duyet");
  

}

function saveEdit() {
  let email = $("#userEmail").val()
  let password = $("#userPassword").val()
  let name = $("#userName").val()
  let admin = $("#flexSwitchCheckDefault").prop("checked")
  $.post("/code/admin/userManager", { id: selectId, email: email, password: password, name: name, admin: admin }, function (data) {
    console.log("thanh cong")
    let id = selectId;
    $("#" + id + " .email").html(email);
    $("#" + id + " .password").html(password);
    $("#" + id + " .name").html(name);
    $("#" + id + " .admin").html(admin ? 1 : 0);
  });
  $("#closeEdit").click();
  alert("Save succec !")

}
// delete function
function deleteUs(id) {

  let bool = confirm("are you sure ?");

  if (bool) {
    $.ajax({
      url: '/api/order/' + id,
      method: 'DELETE',
      contentType: 'application/json',
    }
    ).then((data) => {
      $("#" + id).remove()
      alert("delete succec !")
    }).catch(err => {
      alert("delete failed !")
    });
  }
}
function duyet() {

  let bool = confirm("are you sure ?");
  let sts = $("#orderDuyet").prop("checked");
  let strs = sts ? 'da duyet' : 'chua duyet';
  if (bool) {
    $.ajax({
      url: '/api/order?id=' + selectId + '&status=' + strs,
      method: 'PUT',
      contentType: 'application/json',
    }
    ).then((data) => {
      $("#orderStatus").html(strs)
      $("#" + selectId + " .status").html(strs);
      alert("update succec !")

    }).catch(err => {
      alert("update failed !")
    });
  }
}
function loadVideo() {
  PAGE++;
  $.get("/api/order/pagination", { page: PAGE, limit: 2 }).then(data => {

    if (data.length < 1) {
      alert("Da het du lieu");
    }
    console.log(data)
    data.map((v, k) => {
      console.log(k)
      let item = $(`
      <tr id="${v.id}">
      <th scope="row">${v.id}</th>
      <td class="address">${v.address}</td>
      <td class="createDate">${v.createDate}</td>
      <td class="account">${v.account.name}</td>
      <td class="status">${v.status}</td>
      <td><button type="button" class="btn btn-danger" onclick="deleteUs(${v.id})">Delete</button></td>
      <td><button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"
          onClick="editUS(${v.id})">View</button></td>
    </tr>
      `);
      $('tbody').append(item);
    })
  });

}
