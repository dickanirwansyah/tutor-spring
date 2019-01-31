$(document).ready(function(){
    $("#submitButton").click(function(event){
        event.preventDefault();
        ajaxSubmitButton();
    });
});

function ajaxSubmitButton(){
    var form = $('#fileUploadForm')[0];
    var data = new FormData(form);

    $("#submitButton").prop("disabled", true);
    $.ajax({
        type: 'POST',
        enctype: 'multipart/form-data',
        url: '/api/upload/multiple',
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 1000000,
        success: function(data, textStatus, jqXHR){
            $("#result").html(data);
            console.log("success : ",data);
            $("#submitButton").prop("disabled", false);
            $("#fileUploadForm")[0].reset();
        },
        error: function(jqXHR, textStatus, errorThrow){
            $("#result").html(jqXHR.responseText);
            console.log("error : ",jqXHR.responseText);
            $("submitButton").prop("disabled", false);
        }
    });
}