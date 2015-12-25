$(function(){
    $('.signOut').click(function(){
        $.ajax({
            type: "POST",
            url: "",
            data: "method=signOut",
            success: function(answer){
                if(answer){
                    location = '/index.html';
                }
            }
        });
    });
});