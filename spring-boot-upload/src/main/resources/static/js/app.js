function getEmployeeDetails(){
    $.ajax({url: "/employees", success: function(result){
        $(".table tbody").html('');
        if(result.length > 0){
            $.each(result, function(index, value){
                var htmlStr = '<tr><td>'+result[index].id+'</td><td>'+result[index].name+'</tr>';
                $('.table tbody').append(htmlStr);
            })
        }else{
            $('.table tbody').append('<tr><td colspan="4" style="color:red;">No record Found !</td></tr>');
        }
    }});
}

$(document).ready(function(){
    getEmployeeDetails();
    $(".alert-success").hide();
    $(".alert-danger").hide();

    $('#btnSubmit').click(function(){
        var form = $('#fileUploadForm')[0];
        var data = new FormData(form);
        var jsonDataObj = {
            "name": $("#name").val(),
            "designation": $("#designtaion").val()
        };
        data.append("empJson", JSON.stringify(jsonDataObj));
        $("#btnSubmit").prop("disabled", true);
        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            url: '/employees',
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function(data){
                console.log("SUCCESS : ",data);
                $("#btnSubmit").prop("disabled", false);
                $(".alert-success").show();
                $(".alert-danger").hide();
                getEmployeeDetails();
            },
            error: function(e){
                $(".alert-danger").show();
                $(".alert-success").hide();
                console.log("ERROR : ",e);
                $("#btnSubmit").prop("disabled", false);
            }
        });
    });
});