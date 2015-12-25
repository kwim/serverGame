$(function(){
    $('tr[key] .delete','#userTable').click(function(){
        $.ajax({
            type: "POST",
            url: "",
            data: "method=deleteUser&login=" + $(this).parents('tr[key]').attr('key'),
            success: function(answer){
                if(answer){
                    //удалили успешно, обновляем страницу
                    location = 'admin';
                }
            }
        });
    });
});