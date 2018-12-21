$(document).ready(function(){
    //submit form
    $("#myForm").submit(function(event){
        event.preventDefault();
        //ajaxPost function in here !!
        ajaxPost();
    });

    function ajaxPost(){
        //prepare form data
        var formData = {
            bookId: $("#bookId").val(),
            name: $("#name").val(),
            stock: $("#stock").val(),
            author: $("#author").val()
        }

        //lakukan aksi post, please..initilize data dulu !!
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: 'http://localhost:8080/api/book/create',
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function(result){
                if(result.status == "success"){
                    $("#postResultDiv").html(
                        "<p> "+result.data.name + " Post successfully! <br/>"
                        +"---> Congrats !! "+ "</p>"
                    );
                }else{
                    $("postResultDiv").html("<strong>Error Inserted</strong>");
                }
                console.log(result)
            },
            error: function(e){
                alert("Error")
                console.log("ERROR "+e)
            }
        });
    }

});