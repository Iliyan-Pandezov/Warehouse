$(document).ready(function(){
    $("#removeFromCart").on("click", function(e){
        removeFromCart();
    });
});

function removeFromCart() {

    url = contextPath + "users/cart/delete/" + productId;

    $.ajax({
        type: "POST",
        url: url,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(crsfHeaderName, csrfValue);
        }
    }).done(function(response){
        $("#modalTitle").text("Shopping Cart");
        $("#modalBody").text(response);
        $("#myModal").modal();
    }).fail(function() {
        $("#modalTitle").text("Shopping Cart");
        $("#modalBody").text("Error while removing a product from shopping cart.");
        $("#myModal").modal();
    });
}