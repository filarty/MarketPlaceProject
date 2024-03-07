<#import "macro/base.ftlh" as base/>
<#import "macro/menu_bar.ftlh" as bar/>
<@base.base>
    <@bar.bar user>
    <div class="card mb-3" style="max-width: 540px;">
        <div class="row g-0 product_card">
            <div class="col-md-4">
                <#assign image_path = product.images[0].path>
                <img src="../image/get_image/${image_path}" class="img-fluid rounded-start" alt="...">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title">${product.name}</h5>
                    <p class="text">${product.description}</p>
                    <p class="text">${product.price}</p>
                    <div class="rateStar" rate="${product.rate?c}"></div>
                    <p class="text">${product.username}</p>
                    <p class="text">${product.phoneNumber}</p>
                </div>
            </div>
        </div>
    </div>
    </@bar.bar>
</@base.base>