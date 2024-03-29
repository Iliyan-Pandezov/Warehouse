$(document).ready(function(){
    $("#buttonAdd2Cart").on("click", function(e){
        addToCart();
    });
});

function addToCart() {
    quantity = $("#quantity" + productId).val();

    url = contextPath + "users/cart/add/" + productId + "/" +quantity;

    $.ajax({
        type: "POST",
        url: url,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(crsfHeaderName, csrfValue);
        }
    }).done(function(response){
        $("#modalTitle").text("Shopping Cart");
        $("#modalBody").text(response);
        $("#myModal").modal('show');
    }).fail(function() {
        $("#modalTitle").text("Shopping Cart");
        $("#modalBody").text("Error while adding product to shopping cart.");
        $("#myModal").modal('show');
    });
}