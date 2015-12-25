$(function(){
    var showForm = function(isReg){
        $('.form-container').show();
        $('.form').attr('action', isReg ? 'signup' : 'signin');
        $('.form-button').text(isReg ? 'Sign in' : 'Sign up');
    };
    $('.reg-button').click(function(){
        showForm(true);
    });

    $('.auth-button').click(function(){
        showForm(false);
    });

    $('.admin-button').click(function(){
        location = '/admin';
    });
});