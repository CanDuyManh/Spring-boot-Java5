
        async function inintShop(){
            let product ;
            await $.get( "/api/product").then(data =>{
                product = data;
            });
            product.map((v,k)=>{
                let item = `<div class="col-md-4">
                        <div class="card mb-4 product-wap rounded-0">
                            <div class="card rounded-0">
                                <img class="card-img rounded-0 img-fluid" src="/images/${v.photo}">
                                <div class="card-img-overlay rounded-0 product-overlay d-flex align-items-center justify-content-center">
                                    <ul class="list-unstyled">
                                        <li><a class="btn btn-success text-white mt-2" href="/home/product/view/${v.id}"><i class="far fa-eye"></i></a></li>
                                        <li><a class="btn btn-success text-white mt-2" onclick="addToCart(${v.id})" ><i class="fas fa-cart-plus"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-body">
                                <a href="/home/product/view/${v.id}" class="h3 text-decoration-none">${v.name}</a>
                                <ul class="w-100 list-unstyled d-flex justify-content-between mb-0">
                                    <li>M/L/X/XL</li>
                                    <li class="pt-2">
                                        <span class="product-color-dot color-dot-red float-left rounded-circle ml-1"></span>
                                        <span class="product-color-dot color-dot-blue float-left rounded-circle ml-1"></span>
                                        <span class="product-color-dot color-dot-black float-left rounded-circle ml-1"></span>
                                        <span class="product-color-dot color-dot-light float-left rounded-circle ml-1"></span>
                                        <span class="product-color-dot color-dot-green float-left rounded-circle ml-1"></span>
                                    </li>
                                </ul>
                                <ul class="list-unstyled d-flex justify-content-center mb-1">
                                    <li>
                                        <i class="text-warning fa fa-star"></i>
                                        <i class="text-warning fa fa-star"></i>
                                        <i class="text-warning fa fa-star"></i>
                                        <i class="text-muted fa fa-star"></i>
                                        <i class="text-muted fa fa-star"></i>
                                    </li>
                                </ul>
                                <p class="text-center mb-0">${v.price}</p>
                            </div>
                        </div>
                    </div>`;
                    $("#container-product").append(item)
            })
        
        }
        inintShop();

        async function sortBy(category){
            $("#container-product").empty();

            let product ;
            await $.get( "/api/product").then(data =>{
                product = data;
            });

            product.map((v,k)=>{
                if(v.category.name == category){

                    let item = `<div class="col-md-4">
                    <div class="card mb-4 product-wap rounded-0">
                        <div class="card rounded-0">
                            <img class="card-img rounded-0 img-fluid" src="/images/${v.photo}">
                            <div class="card-img-overlay rounded-0 product-overlay d-flex align-items-center justify-content-center">
                                <ul class="list-unstyled">
                                    <li><a class="btn btn-success text-white mt-2" href="/home/product/view/${v.id}"><i class="far fa-eye"></i></a></li>
                                    <li><a class="btn btn-success text-white mt-2" onclick="addToCart(${v.id})" ><i class="fas fa-cart-plus"></i></a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="card-body">
                            <a href="/home/product/view/${v.id}" class="h3 text-decoration-none">${v.name}</a>
                            <ul class="w-100 list-unstyled d-flex justify-content-between mb-0">
                                <li>M/L/X/XL</li>
                                <li class="pt-2">
                                    <span class="product-color-dot color-dot-red float-left rounded-circle ml-1"></span>
                                    <span class="product-color-dot color-dot-blue float-left rounded-circle ml-1"></span>
                                    <span class="product-color-dot color-dot-black float-left rounded-circle ml-1"></span>
                                    <span class="product-color-dot color-dot-light float-left rounded-circle ml-1"></span>
                                    <span class="product-color-dot color-dot-green float-left rounded-circle ml-1"></span>
                                </li>
                            </ul>
                            <ul class="list-unstyled d-flex justify-content-center mb-1">
                                <li>
                                    <i class="text-warning fa fa-star"></i>
                                    <i class="text-warning fa fa-star"></i>
                                    <i class="text-warning fa fa-star"></i>
                                    <i class="text-muted fa fa-star"></i>
                                    <i class="text-muted fa fa-star"></i>
                                </li>
                            </ul>
                            <p class="text-center mb-0">${v.price}</p>
                        </div>
                    </div>
                </div>`;
                $("#container-product").append(item)
                }
                
            })

        }

        function addToCart(id){
            let prd ;
            $.get("/api/product/"+id,function (data){
                
                $.post("/cart/add" , {id:data.id , name : data.name , price : data.price},function(data){
                    if(data){
                        alert("Thêm Thành Công !")
                        $.get('/cart/getSize',function(data){
                            $("#getTotolCard").html(data)
                        })
                    }else{
                        alert("Thêm Thất bại !")
                    }
                })
            })
        }