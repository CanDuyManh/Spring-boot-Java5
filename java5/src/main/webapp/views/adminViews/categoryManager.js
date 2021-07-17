var selectId = 0;
var PAGE = 0;
  function clear(){
  var selectId = 0;
  $("#categoryID").html("")
  $("#categoryName").val("")
  }

  function editUS(id){
  selectId = id;
  if(id){
    var name = $("#"+id+" .name").html();

    $("#categoryName").val(name)
    $("#categoryID").html(id)
    
  }else{
    clear()
    
  }
  }

  function saveEdit(){

    let name =  $("#categoryName").val()

    $.post( "/api/category",{id : selectId 
      ,name : name 
    },function( data ) {
        console.log("thanh cong")
        
        let id = selectId;
        if(selectId != ''){
        $("#"+id+" .name").html(name);
      }

    }).then(data =>{
      if(data){
        if(selectId == ''){
       let item = $(`
       <tr id="${data.id}">
       <th scope="row">${data.id}</th>
       <td class="name">${data.name}</td>
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
          url: '/api/category/'+id,
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
  function loadVideo(){
      PAGE ++;
      $.get( "/api/category/pagination",{page:PAGE,limit:2}).then(data =>{

          if(data.length < 1){
            alert("Da het du lieu");
          }
          console.log(data)
          data.map((v,k)=>{
            let item = $(`
            <tr id="${v.id}">
            <th scope="row">${v.id}</th>
            <td class="name">${v.name}</td>
            <td><button type="button" class="btn btn-danger" onclick="deleteUs(${v.id})" >Delete</button></td>
            <td><button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onClick="editUS(${v.id})" >Edit</button></td>
            </tr>
            `);
             $('tbody').append(item);
          })
      });
      
  }
