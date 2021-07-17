
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section class="bg-success py-5">
  <div class="container">
      <div class="row align-items-center py-5">
          <div class="col-md-8 text-white">
            <h1>Yor Order</h1>
            <h2>Đừng Bao Giờ trối bỏ những gì bạn đã mua !</h2>
              <p>
                  Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                  tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                  quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
              </p>
          </div>
          <div class="col-md-4">
              <img src="/views/homeViews/assets/img/about-hero.svg" alt="About Hero">
          </div>
      </div>
  </div>
</section>
<div class="container px-2">
    <div class="row">
        <div class="col-sm-12 col-md-offset-1">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th class="text-center">Price</th>
                        <th class="text-center">Total</th>
                        <th> </th>
                    </tr>
                </thead>
                <c:set var = "allTotal" value = "${0}"/>
                <c:set var = "allQuantity" value = "${0}"/>
                <tbody>
                    <c:forEach items="${cart.products}" var="item" >
                        <tr id="${item.id}">
                            <td class="col-sm-8 col-md-6">
                            <div class="media">
                                <a class="thumbnail pull-left" href="#"> <img class="media-object" src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png" style="width: 72px; height: 72px;"> </a>
                                <div class="media-body">
                                    <h4 class="media-heading"><a href="#">${item.name}</a></h4>
                                    <span>Status: </span><span class="text-success"><strong>Con Hang</strong></span>
                                </div>
                            </div></td>
                            <td class="col-sm-1 col-md-1" style="text-align: center">
                              <input type="email" class="form-control inputQuantity" value="${item.quantity}" onchange="changeQuantity(${item.id})">
                            </td>
                            <td class="col-sm-1 col-md-1 text-center"><strong>$${item.price}</strong></td>
                            <td class="col-sm-1 col-md-1 text-center"><strong >$${item.total}</strong></td>
                            <c:set var = "allTotal" value = "${allTotal + item.total}"/>
                            <c:set var = "allQuantity" value = "${allQuantity + item.quantity}"/>
                            <td class="col-sm-1 col-md-1">
                            <button type="button" class="btn btn-danger" onclick="onRemove(${item.id})">
                                <span class="glyphicon glyphicon-remove"></span> Remove
                            </button></td>
                        </tr>
    
                    </c:forEach>
                    <tr>
                      <td>   </td>
                      <td>   </td>
                      <td>   </td>
                      <td><h5>Quantity</h5></td>
                      <td class="text-right"><h3><span id="getQuantity"> ${allQuantity}</span></h3></td>
                  </tr>
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td><h3>Total</h3></td>
                        <td class="text-right"><h3><span id="getTotal"> ${allTotal}</span></h3></td>
                    </tr>
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td>
                        <button type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                        </button></td>
                        <td>
                            <button type="button" class="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#exampleModal">Mua</button>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Điền Thông Tin</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form>
          <div class="mb-3">
            <div class="alert alert-danger" id="messError" role="alert">
            </div>
          </div>
          
          <div class="mb-3">
            <label for="productName" class="col-form-label">Họ Tên:</label>
            <input type="text" class="form-control" id="hoTen">
          </div>
          <div class="mb-3">
            <label for="productName" class="col-form-label">Địa Chỉ:</label>
            <input type="text" class="form-control" id="diaChi">
          </div>
          <div class="mb-3">
            <label for="productName" class="col-form-label">SDT:</label>
            <input type="text" class="form-control" id="SDT">
          </div>
          
        </form>
      </div>
      <div class="modal-footer">
        <button id="closeEdit" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button id="saveEdit" type="button" class="btn btn-primary" onclick="saveEdit(${acc.id})">Đặt</button>
      </div>
    </div>
  </div>
</div>


<script>

    function changeQuantity(id){
      let quantity = $("#"+id +" .inputQuantity").val()
      if(quantity > 0 && quantity < 101){
        quantity = parseInt( quantity )
        $.post("/cart/setQuantity",{id:id , quantity:quantity} , function(data){
        location.reload();
      })
      }
      else{
        alert("Hãy Nhập đúng số lượng (1 - 100)")
        }
      
    }

    function inintCart(){
        try {

            let allTotal = $(".getTotal")
            let to = allTotal[0].html()
            console.log(to)
        } catch (error) {
            
        }
        
    }

    function saveEdit(accID){
        let hoTen = $("#hoTen").val();
        let diaChi = $("#diaChi").val();
        let sdt = $("#SDT").val();
        let allQuantity = $("#getQuantity").html();
        let allTotal = $("#getTotal").html()
       
        if(hoTen.length < 1 || hoTen.length >255){
        $('#messError').html("Họ tên không hợp lệ !")
        return;
        }
               
        if(diaChi.length < 1 || diaChi.length >255){
        $('#messError').html("Địa Chỉ không hợp lệ !")
        return;
        }

        if(sdt.length < 10 || sdt.length >12 || isNaN(sdt)){
        $('#messError').html("SDT không hợp lệ !")
        return;
        }

        
          $.post("/api/order",{account:accID , address:diaChi , status:"cho duyet"} , function(data){
            $.get("/cart/getProduct",function(data2){
                data2.map((v,k)=>{
                  $.post("/api/orderDetail",{
                    price : v.total,
                    quantity : v.quantity,
                    order : data.id,
                    product : v.id,
                  })
                })
            })
          })

      
      $("#closeEdit").click();
      alert("Save succec !")
     
    }

    function onRemove(id){
      $.ajax({
    url: '/cart/remove/'+id,
    type: 'DELETE',
    success: function(result) {
        // Do something with the result
        window.location.href = "/cart";
    }
});
    }

    inintCart();
 
</script>