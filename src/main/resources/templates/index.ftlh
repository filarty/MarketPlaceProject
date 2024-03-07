<#import "macro/base.ftlh" as base/>
<#import "macro/menu_bar.ftlh" as bar/>
<@base.base>
    <@bar.bar user>
    <div class="searchDiv">
    <input class="form-control me-2 search" type="search" placeholder="Search" aria-label="Search">
    </div>
    <br>
    <div class="row productContainer">
<#list products as product >
    <#assign image_path = product.images[0].path>
    <div class="col-sm-3  product_card" data-product-id="${product.id}">
    <div class="card mb-3" style="max-width: 400px;">
        <div class="row g-0">
            <div class="col-sm-4">
                <img src="image/get_image/${image_path}" class="img-fluid rounded-start" alt="...">
            </div>
                <div class="col-sm-6">
                        <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text">${product.price}</p>
                            <div class="rateStar" rate="${product.rate?c}"></div>
                        <p class="card-text"><small class="text-muted">${product.username}</small></p>
                        </div>
                </div>
        </div>
    </div>
    </div>
</#list>
    </div>
    <br>
    <#if user.username??>
<form action="/add_product" method="post" enctype="multipart/form-data">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <ul>
        <li>Название: <input name="name" type="text"></li><br>
        <li>Описание: <input name="description" type="text"></li><br>
        <li>Цена: <input name="price" type="number"></li><br>
        <li>Номер телефона: <input name="phoneNumber" type="text"></li><br>
        <li class="rateSelect">
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="rate" id="exampleRadios1" value="0">
                    <label class="form-check-label rateStar" for="exampleRadios1" rate="0">
                    </label>
                </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="rate" id="exampleRadios2" value="0.5">
                <label class="form-check-label rateStar" for="exampleRadios2" rate="0.5">
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="rate" id="exampleRadios3" value="1">
                <label class="form-check-label rateStar" for="exampleRadios3" rate="1">
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="rate" id="exampleRadios4" value="1.5">
                <label class="form-check-label rateStar" for="exampleRadios4" rate="1.5">
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="rate" id="exampleRadios5" value="2">
                <label class="form-check-label rateStar" for="exampleRadios5" rate="2">
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="rate" id="exampleRadios6" value="2.5">
                <label class="form-check-label rateStar" for="exampleRadios6" rate="2.5">
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="rate" id="exampleRadios7" value="3">
                <label class="form-check-label rateStar" for="exampleRadios7" rate="3">
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="rate" id="exampleRadios8" value="3.5">
                <label class="form-check-label rateStar" for="exampleRadios8" rate="3.5">
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="rate" id="exampleRadios9" value="4">
                <label class="form-check-label rateStar" for="exampleRadios9" rate="4">
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="rate" id="exampleRadios10" value="4.5">
                <label class="form-check-label rateStar" for="exampleRadios10" rate="4.5">
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="rate" id="exampleRadios11" value="5">
                <label class="form-check-label rateStar" for="exampleRadios11" rate="5">
                </label>
            </div>
        </li><br>
        <li>Продавец: <input name="username" type="text"></li><br>
        <li>Фото: <input name="file" type="file"></li><br>
        <li><input type="submit" value="Отправить"></li>
    </ul>
</form>
</#if>
    </@bar.bar>
</@base.base>