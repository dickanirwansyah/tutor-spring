$(document).ready(function(){
    $("#getAllBooks").click(function(event){
        event.preventDefault();
        //ajax Get in here..
        ajaxGet();
    });

    /**EXAMPLE API CALL DENGAN AJAX JQUERY **/
    function ajaxGet(){
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/api/book/list',
            success: function(result){
                if(result.status == "success"){
                    $('#getResultDiv ul').empty();
                    var custList="";
                    $.each(result.data, function(i, book){
                        var books = "Book Name = "+book.name + " OK.";
                        $('#getResultDiv .list-group').append(books);
                    });
                    console.log("success : ", result);
                }else{
                    $('#getResultDiv').html("<strong>ERROR.</strong>");
                    console.log("FAIL : "+result);
                }
            },
            error: function(e){
                $("#getResultDiv").html("<strong>ERROR.</strong>");
                console.log("error : "+e);
            }
        });
    }

});