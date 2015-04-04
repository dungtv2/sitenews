$(function() {
    effectad();
    phantrang();
    deletelist();
    changeWidth();
    changeColor();
});
function changeWidth() {
    /* change boxed */
    boxed = function() {
        var boxed = getCookie("boxed");
        if (boxed !== null) {
            $('#container').css({width: boxed, margin: '0 auto', float: 'none'});
        }
    };
    boxed();
    $('#changeColor select').change(function() {
        var $ok = $(this).val();
        if ($ok == "full") {
            setCookie("boxed", "100%", 1);
            boxed();
        } else if ($ok == "90%") {
            setCookie("boxed", '90%', 1);
            boxed();
        } else if ($ok == "80%") {
            setCookie("boxed", '80%', 1);
            boxed();
        } else if ($ok == "70%") {
            setCookie("boxed", '70%', 1);
            boxed();
        }
    });

    /* reset */
    reset = function() {
        $('[btn-data="reset"]').click(function() {
            setCookie("boxed", null, 0);
            setCookie("wrapp_color", null, 0);
            setCookie("conlai_wrapp_color", null, 0);
            location.reload();
        });
    };
    reset();
}
function changeColor() {
    appendColor = function(nhan) {
        var color = getCookie("conlai_wrapp_color");
        if (color != null) {
            $(nhan).css({backgroundColor: '#' + color});
        } else {
            $(nhan).addClass("sub1_header_bgc");
        }
    };
    color = function(cname, nhan) {
        var color = getCookie(cname);
        if (color != null) {
            $(nhan).css({backgroundColor: '#' + color});
            $('.list_header li a').hover(function() {
                $(this).css({opacity: 0.7, backgroundColor: 'rgba(255,255,255,0)'});
            }, function() {
                $(this).css({opacity: 1});
            });
        }
    };
    colorPicker = function(b, cname, nhan) {
        $(b).colpick({
            colorScheme: 'dark',
            layout: 'rgbhex',
            color: 'ff8800',
            submitText: 'Chọn Đi',
            livePreview: 'false',
            onSubmit: function(hsb, hex, rgb, el) {
                $(el).css({backgroundColor: '#' + hex});
                setCookie(cname, hex, 1);
                color(cname, nhan);
                $(el).colpickHide();
            }
        });
    };
    appendColor("#footer, .sub1_header, .btn-search,.list_header li a");
    $('#changeColor').attr("class", "show");
    color('wrapp_color', '#container-wrapper');
    color('conlai_wrapp_color', '#footer, .sub1_header, .btn-search');
    $('#hehe').click(function() {
        var $change = $('#changeColor');
        var $class = $change.attr("class");
        if ($class == "show") {
            $change.animate({right: '-134px'}, 300);
            $change.attr("class", "hide");
        }
        if ($class == "hide") {
            $change.animate({right: '0px'}, 300);
            $change.attr("class", "show");
        }
    });
    $('#changeColor div:nth-of-type(2) a').click(function() {
        $('#changeColor div:nth-of-type(2)>div>div').css({backgroundColor: '#' + $(this).attr("id")});
        setCookie("wrapp_color", $(this).attr("id"), 1);
        color('wrapp_color', '#container-wrapper');
    });
    $('#changeColor div:nth-of-type(1) a').click(function() {
        $('#changeColor div:nth-of-type(1)>div>div').css({backgroundColor: '#' + $(this).attr("id")});
        setCookie("conlai_wrapp_color", $(this).attr("id"), 1);
        color('conlai_wrapp_color', '#footer, .sub1_header, .btn-search,.list_header li a');

    });
    colorPicker('#changeColor div:nth-of-type(2)>div>div', 'wrapp_color', '#container-wrapper');
    colorPicker('#changeColor div:nth-of-type(1)>div>div', 'conlai_wrapp_color', '#footer, .sub1_header, .btn-search,.list_header li a');
}
function effectad() {
    changerow = function(data) {
        if (data.status === "success") {
            location = location.href;
        }
    };
    /* link active */
    var href = location.pathname;
    var name = href.substr(href.indexOf("admin/") + 6);
    $('a[href="' + name + '"]').addClass("active");

    /* cắt chữ ở table */
    $('.cell-data').each(function() {
        var text_lbl = $(this).find('label').text();
        if (text_lbl.length > 20) {
            $(this).find('label').text(text_lbl.substr(0, 20)).append("...");
        }
    });

    /* ẩn hiện insert and show */
    hide = function(td, btd) {
        var flag = false;
        $(td).click(function() {
            if (flag) {
                $(btd).show();
                flag = false;
            } else {
                $(btd).hide();
                flag = true;
            }
        });
    };
    hide('.a_insert', 'div[data-hide="insert"]');
    hide('.a_show', 'div[data-hide="show"]');
}
function setCookie(cname, cvalue, cexdays) {
    var today = new Date();
    today.setTime(today.getTime() + (cexdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + today.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}
function getCookie(cname) {
    var obj = document.cookie.split(';');
    var ok;
    $.each(obj, function(index) {
        var name = obj[index].substr(0, obj[index].indexOf("=")).trim();
        var value = obj[index].substr(obj[index].indexOf("=") + 1).trim();
        if (name == cname) {
            ok = value;
        }
    });
    return ok;
}
//check cb for delete list
function deletelist() {
    $('.cb').removeAttr("checked");
    $('.cb').click(function() {
        var val = $('#test').val();
        var val_next = $(this).parent().parent().next().children("label").text();
        if ($(this).is(":checked")) {
            $(this).parent().parent().parent().addClass("selected");
            if (val == null || val == "") {
                val = val + val_next;
            } else {
                val = val + "," + val_next;
            }
            $('#test').val(val);
        } else {
            var mang = val.trim().split(",");
            var conlai = "";
            $(this).parent().parent().parent().removeClass("selected");
            $.each(mang, function(index) {
                if (val_next != mang[index]) {
                    if (conlai != "") {
                        conlai = conlai + "," + mang[index];
                    } else {
                        conlai = mang[index];
                    }
                }
            });
            $('#test').val(conlai);
        }
    });
}
function playSelectedFileInit() {
    var URL = window.URL || window.webkitURL;
    var displayMessage = (function displayMessageInit() {
        var node = $('#message');

        return function displayMessage(message, isError) {
            node.append('<button type="button" class="close"></button>');
            isError ? node.addClass("alert alert-danger") : node.addClass("alert alert-success");
            node.append(message);
        };
    }());
    var files = document.getElementById("upload").files;
    $('.sub_infor,#sub1_infor, #message').html("");
    $('.message').remove();
    $('#message').removeClass();
    $('.ui-progressbar-label').text("");
    $('.ui-progressbar-value').css({width: '0px', display: 'none'});

    $.each(files, function(index) {
        var file = files[index];
        var type = file.type;
        var filesize;
        if (file.size > (1024 * 1024)) {
            filesize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
        } else {
            filesize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
        }
        var infor = '<div class="sub_infor">\n\
                                    <div id="test' + index + '"></div>\n\
                                    <div>\n\
                                        <p><span>Name</span> :' + file.name + '</p>\n\
                                        <p><span>Type</span> :' + type + '</p>\n\
                                        <p><span>Size</span> :' + filesize + '</p>\n\
                                    </div>\n\
                                </div>';
        $('#infor').append(infor);

        var nodeBK;
        if (file.type.match("^(video/[a-zA-Z0-9]+)")) {
            $('#test' + index + '').append('<video id="video' + index + '" class="video_test" controls="controls" autoplay="true"></video>');
            nodeBK = document.getElementById('video' + index + '');
            var canPlay = nodeBK.canPlayType(type);
            canPlay = (canPlay === '' ? 'no' : canPlay);
            var stm;
            var isError = canPlay === 'no';
            isError ? stm = "can't" : stm = "can";
            var message = 'Video ' + (index + 1) + stm + ' play (type "' + type + '")';
            displayMessage(message, isError);
            if (isError) {
                return;
            }
        } else if (type.match("^(image/[a-zA-Z0-9]+)")) {
            $('#test' + index + '').append('<img class="img_test" id="img' + index + '" />');
            nodeBK = document.getElementById('img' + index + '');
        }
        var fileURL = URL.createObjectURL(file);
        nodeBK.src = fileURL;
    });
}
function phantrang() {
    var number_page = $('#pag_row').val();
    var search = location.search;
    var sea = search.split("&");
    if (search == "") {
        $('a[data-page]:contains(' + $('#pag_page').val() + ')').addClass("page-active");
    }
    $.each(sea, function(index) {
        var name = sea[index].substr(0, sea[index].indexOf("="));
        var va = sea[index].substr(sea[index].indexOf("=") + 1);
        if (name == "?search") {
            $('a[data-page]:contains(' + va + ')').addClass("page-active");
        }
    });
    $('.topage [topage="topage"]').click(function() {
        var val = $('.topage .form-control').val();
        if (val != "") {
            location.search = "search=" + val;
        }
    });
    $('a[data-page]').click(function() {
        var val = $(this).attr('data-page');
        if (search == "") {
            if (val == "pre") {
                val = 1;
            } else if (val == "next") {
                val = 2;
            }
        } else {
            if (val == "pre") {
                val = Math.round($('a[data-page].page-active').text().toString());
                val > 1 ? val-- : val = 1;
            } else if (val == "next") {
                val = Math.round($('a[data-page].page-active').text().toString());
                if (!(val == number_page)) {
                    val++;
                }
            }
        }
        location.search = "search=" + val;
    });
}

