/** example table **/
$(document).ready(function(){
    var table = $('#exampleTableItem').DataTable({
        "sAjaxSource": "http://localhost:8080/api/item/list",
        "sAjaxDataProp": "",
        "order" : [[0, "asc"]],
        "aoColumns" : [
            {"mData": "id"},
            {"mData": "name"},
            {"mData": "price"}
        ]
    })
});