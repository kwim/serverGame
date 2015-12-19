$(function(){
    var showForm = function(isReg){
        $('.form-container').show().attr('/auth/' + (isReg ? 'signup' : 'signin'));
        $('.form-button').text(isReg ? 'Зарегистрироваться' : 'Вход');
    };
    $('.reg-button').click(function(){
        showForm(true);
    });

    $('.auth-button').click(function(){
        showForm(false);
    });
});