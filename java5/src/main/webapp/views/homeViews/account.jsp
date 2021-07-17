<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <div class="shadow-lg p-3 mb-5 bg-body rounded">
        <div class="col-md-11 row justify-content-md-center" >
            <div class="shadow-lg p-3 mb-5 bg-body rounded col-md-5 mx-2 ">
                <div class="mb-3">
                    <H4>Thông Tin</H4>
                </div>
                <div class="mb-3">
                    <label for="usEmail" class="form-label">Email address</label>
                    <div class="row" id="row_xacThuc">
                        <div class="col-9">
                        <input type="text" class="form-control" id="usEmail" name="email" value="${acc.email}">
                        </div>
                        <div class="col-3 itemx" >
                            <c:if test="${!acc.activated}">
                            <button type="button" onclick="onXacThuc('${acc.email}')" class="btn btn-primary">Xác Thực</button>
                        </c:if> 
                        </div>
                        
                    </div>
                   
                </div>
                <div class="mb-3">
                    <label for="userName" class="form-label">User Name</label>
                    <input type="text" class="form-control" id="userName" name="userName" value="${acc.userName}">
                </div>
                <div class="mb-3">
                    <label for="usName" class="form-label">Name</label>
                    <input type="text" class="form-control" id="usName" name="name" value="${acc.name}">
                </div>
                <div class="mb-3">
                    <button type="button" class="btn btn-primary" onclick="changeTT()">Thay Đổi</button>
                </div>
            </div>
            <div class="shadow-lg p-3 mb-5 bg-body rounded col-md-5 mx-2 " >
                <div class="mb-3">
                    <H4>Đổi Mật Khẩu</H4>
                </div>
                <div class="mb-3">
                    <label for="oldPass" class="form-label">Mật Khẩu Cũ</label>
                    <input type="password" class="form-control" id="oldPass" name="oldPass">
                </div>
                <div class="mb-3">
                    <label for="NewPass" class="form-label">Mật Khẩu Mới</label>
                    <input type="password" class="form-control" id="NewPass" name="NewPass">
                </div>
                <div class="mb-3">
                    <label for="reNewPass" class="form-label">Nhập Lại Mật Khẩu Mới</label>
                    <input type="password" class="form-control" id="reNewPass" name="reNewPass">
                </div>
                <div class="mb-3">
                    <button type="button" class="btn btn-primary" onclick="onChangePass()">Đổi Mật Khẩu</button>
                </div>
            </div>
        </div>
        
    </div>

    <script>

        var MXN3 ;
        function changeTT(){
            
            let email = $("#usEmail").val()
            let usName = $("#userName").val()
            let name = $("#usName").val()
            $.post("/api/account/change",{name:name , email : email , userName : usName},function(data){
                alert("thay đổi thành công !")
            })
        }
        function onChangePass(){
            let oldPass = $("#oldPass").val()
            let NewPass = $("#NewPass").val()
            let reNewPass = $("#reNewPass").val()

            if(NewPass === reNewPass){
                $.post("/api/account/change/password",{oldPass:oldPass ,newPass : NewPass},function(data){
                    if(data){
                        alert("thay đổi thành công !")
                    }else{
                        alert("Sai mật Khẩu cũ !")
                    }
                
            })
            }else{
                alert("Nhập lại mật khẩu mới sai !");
            }
        }
        function onXacThuc(gmail){
            const item = `
                    <label for="userName" class="form-label itemx mt-3">Mã Xác Nhận</label>
                    <div class="col-9 itemx mt-2">
                    <input type="text" class="form-control" id="mxn2">
                    </div>
                    <div class="col-3 itemx mt-2">
                        <button type="button" onclick="onXacThuc2(${acc.id})" class="btn btn-primary">Xác Thực</button>
                    </div>
                    `;
                    $('#row_xacThuc').append(item);
                    $.post("/api/account/xacThuc",{gmail : gmail}).then(
                        data => {
                            MXN3 = data;
                        }
                    )
        }
        function onXacThuc2(id){
            let mxn = $("#mxn2").val();
            if(((Number)(mxn)) == MXN3 ){
                $.post("/api/account/onAccount/"+id).then(
                        data => {
                            if(data){
                                alert("Xác Thực Thành Công")
                                $(".itemx").remove()
                            }else{
                                alert("Xác Thực Thất bại")
                            }
                       
                        }
                    )
            }else{
                alert("Sai Mã Xác Nhận")
            }
        }
         
    </script>