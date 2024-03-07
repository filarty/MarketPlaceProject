document.addEventListener("DOMContentLoaded", () => {
    const addClickHandler = () => {
        const elements = document.querySelectorAll(".product_card");
        elements.forEach((element) => {
            element.addEventListener('click', () => {
                location.href="/get_product/" + element.getAttribute("data-product-id");
            });
        });
    };

    const updateRatings = () => {
        const ratings = document.querySelectorAll(".rateStar");
        ratings.forEach((element) => {
            let starsHTML = "";
            let rating = element.getAttribute("rate");
            let floatRating = parseFloat(rating);
            let fullStars = 5;
            if (floatRating === 0) {
                starsHTML += "<span class='star'>&#9734;</span>".repeat(fullStars);
            } else if (fullStars === floatRating) {
                starsHTML += "<span class='star'>&#9733;</span>".repeat(fullStars);
            } else if ((floatRating % 1) === 0.5) {
                starsHTML += "<span class='star'>&#9733;</span>".repeat(floatRating) +
                    "<span class='half-star-left'>&#9733;</span><span class='half-star-right'>&#9734;</span>" + "<span class='star'>&#9734;</span>"
                        .repeat(fullStars - floatRating);
            } else {
                starsHTML += "<span class='star'>&#9733;</span>".repeat(floatRating) + "<span class='star'>&#9734;</span>".repeat(fullStars - floatRating);
            }
            element.innerHTML = starsHTML;
        });
    };

    const searchInput = document.querySelector(".search");
    if (searchInput) {
        searchInput.addEventListener("input", () => {
            const string = searchInput.value;
            if (string.trim() === "") {
                location.href="/";
            } else
            fetch("http://127.0.0.1:8080/search/find_product/" + string)
                .then(response => response.json())
                .then(data => {
                    const productContainer = document.querySelector(".productContainer");
                    productContainer.innerHTML = "";
                    if (data.length === 0) {
                        productContainer.innerHTML = "<p>No products found</p>";
                    }
                    data.forEach(product => {
                        const productCard = document.createElement("div");
                        productCard.classList.add("col-sm-3", "product_card");
                        productCard.setAttribute("data-product-id", product.id);
                        productCard.innerHTML = `
                            <div class="card mb-3" style="max-width: 400px;">
                                <div class="row g-0">
                                    <div class="col-sm-4">
                                        <img src="image/get_image/${product.images[0].path}" class="img-fluid rounded-start" alt="...">
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="card-body">
                                            <h5 class="card-title">${product.name}</h5>
                                            <p class="card-text">${product.price}</p>
                                            <div class="rateStar" rate="${product.rate.toPrecision()}"></div>
                                            <p class="card-text"><small class="text-muted">${product.username}</small></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        `;
                        productContainer.appendChild(productCard);
                    });
                    addClickHandler();
                    updateRatings();
                })
                .catch(error => {
                    console.error('Ошибка при выполнении запроса:', error);
                });
        });
    }
    addClickHandler();
    updateRatings();
});
