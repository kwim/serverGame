$(function(){
    var showForm = function(isReg){
        $('.form').attr('action', isReg ? 'signup' : 'signin');
        $('.form-button').text(isReg ? 'Sign up' : 'Sign in');
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