$( document ).ready(function(){
    $("input:submit").attr("disabled", true);
    
    $("input:text").on("keyup", function(){
        if($("#human-name").val() !== "" && $("#ability").val() !== "" && $("#origin").val() !== "" && $("#abilities").val() !== "") {
            $("input:submit").attr("disabled", false)
        } else {
            $("input:submit").attr("disabled", true)
        }
    });
})