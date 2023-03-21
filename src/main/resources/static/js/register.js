$(document).ready(function(){
    $("#registerButton").on("click", function(e){
        register();
    });
});

function register() {

    url = contextPath + "users/register";

    $.ajax({
        type: "POST",
        url: url,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(crsfHeaderName, csrfValue);
        }
    }).done(function(response){
        $("#modalTitle").text("Registration Successful");
        $("#modalBody").text(response);
        $("#myModal").modal();
    }).fail(function(response) {
        $("#modalTitle").text("Registration Failed");
        $("#modalBody").text(response);
        $("#myModal").modal();
    });
}