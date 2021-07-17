var selectId = 0;
var PAGE = 0;
  function clear(){
    
  var selectId = '';
  $("#productPhoto").html("")
  $("#productName").val("")
  $("#productCreateDate").val("")
  $("#productPrice").val("")
  $("#productAvailable").prop("checked",true)
  $("#productCategory").empty()
  }

  async function editUS(id){
  selectId = id;
  $("#productCategory").empty()
  if(id){
    let allCategory ;
  await $.get('/api/category').then( data => {
    allCategory = data;
  })
  allCategory.map((v,k) => {
    let option = `<option value='${v.id}'>${v.name}</option>`
  $("#productCategory").append(option)
  })

    let photo = $("#"+id+" .photo img").attr('src').replace("/images/","");
    let name = $("#"+id+" .name").html();
    let price = $("#"+id+" .price").html();
    let category = $("#"+id+" .category").html();
    let available = $("#"+id+" .available").html()=="true";

    $("#productName").val(name)
    $("#productPhoto").html(photo)
    $("#productPrice").val(price)
    
    $("#productCategory option").each(function (){
      if($(this).text() == category){
        $(this).prop("selected",true)
      }
    })
    $("#productAvailable").prop("checked",available)
  }else{
    clear()
    let allCategory ;
    await $.get('/api/category').then( data => {
      allCategory = data;
    })
    allCategory.map((v,k) => {
      let option = `<option value='${v.id}'>${v.name}</option>`
    $("#productCategory").append(option)
    })
    
  }
  }

  function saveEdit(){
    
  let name = $("#productName").val()
  let photo =  $("#productPhoto").html()
  let price =  $("#productPrice").val()
  let available =  $("#productAvailable").prop("checked")
  let category =   $("#productCategory").val()
  let categoryName = $("#productCategory option:selected").text()

  if($("#fileUpload").val().split('\\').pop()){
    photo = $("#fileUpload").val().split('\\').pop();
    $("#formUpLoadFile").submit();
  }
  
  $.post("/api/product",{id : selectId 
      ,photo : photo
      ,name : name 
      ,price : price
      ,available : available
      ,category : category
    },function( data ) {
        console.log("thanh cong")
        let id = selectId;
       if(id!=''){
        $("#"+id+" .createDate").html(data.createDate);
        $("#"+id+" .name").html(name);
        $("#"+id+" .price").html(price);
        $("#"+id+" .category").html(categoryName);
        $("#"+id+" .available").html(available);
        $("#"+id+" .photo img").attr('src',"/images/"+photo);
      } 

    }).then(data =>{
      
      if(data){
        if(selectId==''){
          console.log("da vao")
       let item = $(`
       <tr id="${data.id}">
       <th scope="row">${data.id}</th>
       <td class="photo"><img src="/images/${data.photo}" alt="Avatar" class="avatar"></td>
       <td class="name">${data.name}</td>
       <td class="createDate">${data.createDate}</td>
       <td class="price">${data.price}</td>
       <td class="available" >${data.available}</td>
       <td class="category">${categoryName}</td>
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
          url: '/api/product/'+id,
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
      $.get( "/api/product/pagination",{page:PAGE,limit:2}).then(data =>{

          if(data.length < 1){
            alert("Da het du lieu");
          }
          console.log(data)
          data.map((v,k)=>{
            let item = $(`
            <tr id="${v.id}">
            <th scope="row">${v.id}</th>
            <td class="photo"><img src="/images/${v.photo}" alt="Avatar" class="avatar"></td>
            <td class="name">${v.name}</td>
            <td class="createDate">${v.createDate}</td>
            <td class="price">${v.price}</td>
            <td class="available" >${v.available}</td>
            <td class="category">${v.category.name}</td>
            <td><button type="button" class="btn btn-danger" onclick="deleteUs(${v.id})" >Delete</button></td>
            <td><button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onClick="editUS(${v.id})" >Edit</button></td>
          </tr>
            `);
             $('tbody').append(item);
          })
      });
      
  }
