var selectId = 0;
var PAGE = 0;
  function clear(){
    // activated
    // admin
    // email
    // name
    // password
    // photo
    // userName
  var selectId = 0;
  $("#accPhoto").html("")
  $("#accEmail").val("")
  $("#accName").val("")
  $("#accPassword").val("")
  $("#accUserName").val("")
  $("#accActivated").prop("checked",true)
  $("#accAdmin").prop("checked",true)
  }

  function editUS(id){
  selectId = id;
  if(id){
    var email = $("#"+id+" .email").html();
    var photo = $("#"+id+" .photo img").attr('src').replace("/images/","");
    var name = $("#"+id+" .name").html();
    var password = $("#"+id+" .password").html();
    var userName = $("#"+id+" .userName").html();
    var activated = $("#"+id+" .activated").html()=="true";
    var admin = $("#"+id+" .admin").html()=="true";

    $("#accEmail").val(email)
    $("#accPhoto").html(photo)
    $("#accName").val(name)
    $("#accPassword").val(password)
    $("#accUserName").val(userName)
    $("#accActivated").prop("checked",activated)
    $("#accAdmin").prop("checked",admin)

  }else{
    clear()
    
  }
  }

  function saveEdit(){
    
    let email = $("#accEmail").val()
    let photo =  $("#accPhoto").html()
    let name =  $("#accName").val()
    let password =  $("#accPassword").val()
    let userName =  $("#accUserName").val()
    let activated =  $("#accActivated").prop("checked")
    let admin =  $("#accAdmin").prop("checked")
  
  if($("#fileUpload").val().split('\\').pop()){
    photo = $("#fileUpload").val().split('\\').pop();
    $("#formUpLoadFile").submit();
  }
  
    $.post( "/api/account",{id : selectId 
      ,email : email
      ,photo : photo
      ,name : name 
      ,password : password
      ,userName : userName
      ,activated : activated
      ,admin : admin
    },function( data ) {
        console.log("thanh cong")
        
        let id = selectId;
       if(id!=''){
        $("#"+id+" .email").html(email);
        $("#"+id+" .name").html(name);
        $("#"+id+" .password").html(password);
        $("#"+id+" .userName").html(userName);
        $("#"+id+" .activated").html(activated?"true":"fale");
        $("#"+id+" .admin").html(admin?"true":"fale");
        $("#"+id+" .photo img").attr('src',"/images/"+photo);
      } 

    }).then(data =>{
      
      if(data){
        if(selectId == ''){

       let item = $(`
     </tr>
     <tr id="${data.id}">
     <th scope="row">${data.id}</th>
     <td class="userName">${data.userName}</td>
     <td class="password">${data.password}</td>
     <td class="name">${data.name}</td>
     <td class="email">${data.email}</td>
     <td class="photo"><img src="/images/${data.photo}" alt="Avatar" class="avatar"></td>
     <td class="admin">${data.admin}</td>
     <td class="activated" >${data.activated}</td>
     <td><button type="button" class="btn btn-danger" onclick="deleteUs(${data.id})" >Delete</button></td>
     <td><button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onClick="editUS(${data.id})" >Edit</button></td>
   </tr>
       `);
        $('tbody').append(item);
      }
    }

    });
    $("#closeEdit").click();
    alert("Save succec !")
   
  }
  // delete function
  function deleteUs(id){

    let bool = confirm("are you sure ?");

    if(bool){
      $.ajax({
          url: '/api/account/'+id,
          method: 'DELETE',
          contentType: 'application/json',
        }
      ).then((data)=>{
        $("#"+id).remove()
            alert("delete succec !")
      }).catch(err=>{
        alert("delete failed !")
      });
    }
  }
  
  function onChageFile(){
    $('#fileUpload').show();
  }

  function loadVideo(){
      PAGE ++;
      $.get( "/api/account/pagination",{page:PAGE,limit:2}).then(data =>{

          if(data.length < 1){
            alert("Da het du lieu");
          }
          console.log(data)
          data.map((v,k)=>{
            let item = $(`
            </tr>
            <tr id="${v.id}">
            <th scope="row">${v.id}</th>
            <td class="userName">${v.userName}</td>
            <td class="password">${v.password}</td>
            <td class="name">${v.name}</td>
            <td class="email">${v.email}</td>
            <td class="photo"><img src="/images/${v.photo}" alt="Avatar" class="avatar"></td>
            <td class="admin">${v.admin}</td>
            <td class="activated" >${v.activated}</td>
            <td><button type="button" class="btn btn-danger" onclick="deleteUs(${v.id})" >Delete</button></td>
            <td><button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onClick="editUS(${v.id})" >Edit</button></td>
          </tr>
            `);
             $('tbody').append(item);
          })
      });
      
  }
