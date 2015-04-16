$(function() {
    function effectNews() {
        var D = "undefined", j = document;
        $('#btn-insert').click(function() {
            $('#txtfull').val($('#cke_1_contents textarea').val());
            var flag = false;
            var count = 0;
            if (flag) {
                return false;
            }
        });
//effect hide columns
        $('#dunga').change(function() {
            var e = getCookie("hide"), c, flag = false, this_val = $(this).val();
            if(typeof(e)=='undefined'){
                setCookie("hide", this_val, 1);
            }
            if (e !== null && typeof(e)!='undefined') {
                var b = e.trim().split(",");
                $.each(b, function(index) {
                    var d = b[index];
                    if (d !== D) {
                        if (this_val !== d) {
                            c = c + "," + d;
                        } else {
                            $('.cell[hide-col="' + d + '"]').show();
                            change("#dunga", d, "white");
                            flag = true;
                        }
                    }
                });
            }
            flag ? setCookie("hide", c, 1) : setCookie("hide", getCookie("hide") + "," + $(this).val(), 1);
            check();
        });
        check();
        function check() {
            var r = getCookie("hide");
            if (r != null) {
                var t = r.trim().split(",");
                $.each(t, function(index) {
                    var d = t[index];
                    if (d != D) {
                        $('.cell[hide-col="' + d + '"]').hide();
                        change('#dunga', d, "rgba(0,122,196,0.2)");
                    }
                });
            }
        }
        function change(id, d, color) {
            $(id).find("option").each(function(index) {
                if ($(this).val() == d) {
                    $(this).css({backgroundColor: color});
                }
            });
        }
        CKEDITOR.replace('txtfull');
        $('.btn-test').click(function() {
            var val = $('#test_html').val();
            $('#result_test').text("").append(val);
        });
    }
    effectNews();
});


