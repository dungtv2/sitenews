$(function() {
    effect_login();
    checkUser_pass();
    rememberUserPass();
});
function effect_login() {
    tinh();
    window.onresize = function() {
        tinh();
    };
    function tinh() {
        var height_window = $(window).height();
        var height_main_log = $('.main-login').outerHeight();
        var margin_top = (height_window / 2) - (height_main_log / 2);
        $('.panel-login > div:first-child').height(height_window);
        $('.main-login').css({marginTop: margin_top + 'px'});
    }
}
function rememberUserPass() {
    $('#cb_remember').click(function() {
        if ($(this).is(":checked")) {
            var user = $('#txt_username').val();
            var pass = $('#txt_password').val();
            if (confirm('do you want remember user')) {
                if (user != null && user != "" && pass != null && pass != "") {
                    setCookie("username", user, 1);
                    setCookie("password", pass, 1);
                    alert('remember success');
                } else {
                    alert('username and password not null');
                    $('#cb_remember').removeAttr("checked");
                }
            } else {
                $('#cb_remember').removeAttr("checked");
            }
        }
    });
}
function checkUser_pass() {
    var username = getCookie("username");
    var password = getCookie("password");
    if (username !== null && password !== null) {
        $('#txt_username').val(username);
        $('#txt_password').val(password);
    }
}

