$(document).ready(function(){
    $('.nBtn, .table .eBtn').on('click', function(event){

        event.preventDefault()
        var href = $(this).attr('href');

        var text = $(this).text();

        if(text == 'Edit'){
        $.get(href, function(country, status){
            $('.myForm #id').val(country.id);
            $('.myForm #name').val(country.name);
            $('.myForm #capital').val(country.capital);
        });
        $('.myForm #modal-country').modal();
        }else if(text == 'New'){
            $('.myForm #id').val('');
            $('.myForm #name').val('');
            $('.myForm #capital').val('');
            $('.myForm #modal-country').modal();
        }
    });

    $('.table .delBtn').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#modal-country-validation-delete #delRef').attr('href', href);
        $('#modal-country-validation-delete').modal();
    });
});