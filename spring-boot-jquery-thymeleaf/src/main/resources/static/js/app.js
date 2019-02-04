function modalCredit(){
    $("#modalDialog").modal();
}

function getListCredit(){
    $.ajax({
    url: '/api/v1/credit',
    success: function(result){
            $(".table tbody").html("");
            if(result.length > 0){

               $.each(result, function(index, value){
                    var htmlIsFound = "<tr><td>"+result[index].creditId+"</td>"+
                    "<td>"+result[index].name+"</td>"+
                    "<td>"+result[index].createdAt+"</td>"+
                    "<td>"+result[index].updatedAt+"</td>"+
                    "<td>"+result[index].info.name+"</td>"+
                    "<td><button type='button' class='btn btn-primary'>"+
                    "<span class='fa fa-fw fa-pencil'></span>Update</button></td>"+
                    "</tr>";
                    $(".table tbody").append(htmlIsFound);
               });

            }else{
                var htmlNotFound = "<tr><td colspan='4' style='color:red;'>Data Not Found !</td></tr>";
                $(".table tbody").append(htmlNotFound);
            }
        }
   });
}

function getListInfo(){
    $.ajax({
        url: '/api/v1/info',
        type: 'GET',
        dataType: 'json',
        async: true,
        success: function(result_data){
            console.log(result_data);
            var option = '';
            $.each(result_data, function(key, value){
                console.log("data : "+result_data);
                option += '<option value='+result_data[key].infoId+'>'+result_data[key].name+'</option>';
            });
            $("#loadInfo").append(option);
        },
        error(jqXHR, textStatus, errorThrown){
            alert('error because : '+errorThrown);
        }
    });
}

$(document).ready(function(){
    $(".alert-danger").hide();
    $(".alert-success").hide();
    //list credit
    getListCredit();
    //list data info
    getListInfo();

    $("#btnSave").on('click', function(){
        var infoId = $("#loadInfo").val();
        var jsonData = {
            name: $("#name").val()
        };

        //action
        $.ajax({
            type: 'POST',
            url: '/api/v1/credit/create/'+infoId,
            data: JSON.stringify(jsonData),
            contentType: 'application/json',
            success: function(result){
                console.log('success : '+result);
                 $("#modalDialog").modal().hide();
                //$(".alert-success").show();
                //$(".alert-danger").hide();
                getListCredit();
            },
            error: function(err){
                console.log('error ! something happens : '+err);
                $(".alert-success").hide();
                $(".alert-danger").show();
            }
        });
    });

});