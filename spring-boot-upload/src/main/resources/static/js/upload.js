// list employee
function getEmployeeList(){
    $.ajax({
        url: "/api/v1/employee",
        success: function(result){
            $('.table tbody').html("");
            if(result.length > 0){
                //load data
                $.each(result, function(index, value){
                    var htmlStr = "<tr><td>"+result[index].id+"</td>"+
                    "<td>"+result[index].name+"</td>"+
                    "<td>"+result[index].designation+"</td>"+
                    "<td>"+"<img height='80' width='80' src="+result[index].profilePicPath+"/>"+"</td>"+
                    "<td><button type='button' class='btn btn-primary'>Update</button>"+
                    "<button type='button' class='btn btn-danger'>Delete</button></td>"+
                    "</tr>";
                    $('.table tbody').append(htmlStr);
                })

            }else{
                //execption
                $('.table tbody').append('<tr><td colspan="4" style="color:red;">No record to display</td></tr>');
            }
        }
    })
}

$(document).ready(function(){
    getEmployeeList();

    $(".alert-danger").hide();
    $(".alert-success").hide();

    //action submitButton
    $("#submitButton").click(function(){
        console.log('this is submitButton is ok.');
        var formUpload = $("#fileUploadForm")[0];
        var data = new FormData(formUpload);
        var JSON_OBJECT = {
            "name": $("#name").val(),
            "designation": $("#designation").val()
        };
        //request param file
        data.append("empJson", JSON.stringify(JSON_OBJECT));
        $("#submitButton").prop("disabled", true);
        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            url: '/api/v1/employee/create',
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function(data){
                console.log("success : ",data);
                $("#submitButton").prop("disabled", false);
                $(".alert-success").show();
                $(".alert-danger").hide();
                getEmployeeList();
            },
            error: function(err){
                console.log("error : ",err);
                $("#submitButton").prop("disabled", false);
                $(".alert-success").hide();
                $(".alert-danger").show();
            }
        });
    });
});