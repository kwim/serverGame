$(function(){
$('tr','#userTable').click(function(){
    $.ajax({
       type: "POST",
       url: "",
       data: "name=John&location=Boston",
       success: function(msg){
         alert( "Data Saved: " + msg );
       }
     });
});

});