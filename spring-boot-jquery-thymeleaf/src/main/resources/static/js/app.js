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
                    "<td><button type='button' data-id='"+result[index].creditId+"' class='btn btn-primary btnEdit'>"+
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

//for edit select info
function getListForSelectedInfo(){
    $.ajax({
        url: '/api/v1/info',
        type: 'GET',
        dataType: 'json',
        async: true,
        success: function(response){
            console.log(response);
            var selectOption = "";
            $.each(response, function(key,value){
                selectOption += '<option value='+response[key].infoId+'>'+response[key].name+'</option>';
            });
            $(".select-dropdown").append(selectOption);
        },
        error(jqXHR, textStatus, err){
            alert("something happend with my code : "+err.error);
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
    //list for selected update
    getListForSelectedInfo();

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
                //error please check !
                console.log('error ! something happens : '+err);
                $(".alert-success").hide();
                $(".alert-danger").show();
            }
        });
    });
});

$(document).on('click', '.btnEdit', function(e){
    //alert('test button edit');
    console.log('get data credit berdasarkan kode / id');
    var linkCreditId = $(this).attr('data-id');

    $.ajax({
        type: 'GET',
        url: '/api/v1/credit/'+linkCreditId,
        success: function(result){
            //alert("kode id : "+result.creditId)
            console.log("data credit by id : "+result.creditId)
            $("#edit-creditId").val(result.creditId);
            $("#edit-name").val(result.name);
            $("#active option[value='"+result.info.infoId+"']").prop('selected', true);
            $("#modalCreditUpdate").modal('show');
        }
    });
});