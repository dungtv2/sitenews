$(function() {
    effecttbl();
    tooltip();
    textfocus();
    dropdown();
    datecontrol();
    notification();
    tablist();
});
function tablist() {
    tab = function() {
        var h = 'div[role="tablist"]', st = $('div[role="tablist"] > .tab-head > [data-tab]'),
                r = function(t) {
                    t.parent().parent(h).find('.tab-head > [data-tab]').removeAttr("tab-active");
                },
                ad = function(t) {
                    t.attr({"tab-active": "active"});
                },
                tabhide = function(t) {
                    t.parent().parent(h).find('.tab-content > div[for]').css({display: "none"});
                },
                tabshow = function(v, t) {
                    t.parent().parent(h).find('.tab-content > div[for]').filter('div[for="' + v + '"]').css({display: "block"});
                };
        st.click(function() {
            var e = $(this);
            r(e);
            tabhide(e);
            ad(e);
            tabshow(e.attr("data-tab"), e);
        });
    };
    tab();
}
function notification() {
    var l = $('.pgn-wrapper'), w = l.html(), d = "undefined", be = "<div>", Be = function(c) {
        return "<div class=" + c + ">";
    }, en = "</div>", f = false, D = function(m, M) {
        return '<div class="pgn-wrapper" data-position="' + m + '">' + M + '</div>';
    }, pn = function(m, t, M) {
        return '<div class="pgn ' + m + '"><div class="alert alert-' + t + '">' + M + '</div></div>';
    }, b = '<button type="button" class="close"></button>', s = function(v) {
        return '<span>' + v + '!</span>';
    };
    close = function() {
        $('.alert .close').click(function() {
            var pr = $(this).parent();
            typeof pr.parent('.pgn').html() !== d ? pr.parent('.pgn').remove() : pr.remove();
        });
    };
    close();
    exwrap = function() {
        typeof w === d ? f = true : f = false;
    };
    ch = function(n, ps, t, p, v) {
        exwrap();
        t === null || t === "" ? t = "info" : t = t;
        p === null || p === "" ? p = ps : p = p;
        var code = b + s(v);
        code = pn(n, t, code);
        f ? (code = D(p, code), $('form').append(code)) : l.attr({"data-position": p}).append(code);
        close();
    };
    flip = function(t, p, v) {
        ch("pgn-flip", "top-right", t, p, v);
    };
    simple = function(t, p, v) {
        ch("pgn-simple", "top-right", t, p, v);
    };
    bar = function(t, p, v) {
        ch("pgn-bar", "top", t, p, v);
    };
    circle = function(t, p, tl, v) {
        exwrap();
        t === null || t === "" ? t = "info" : t = t;
        p === null || p === "" ? p = "top-right" : p = p;
        var code = be + Be("pgn-thumbnail") + be + '<img width="40" height="40" src="http://localhost:8080/haihai/images/thethao/img_thethao1.jpg" alt="" />' + en + en + Be("pgn-message") + be + '<p class="bold">' + tl + '</p><p>' + v + '!</p>' + en + en + en + b;
        code = pn("pgn-circle", t, code) + Be("clearfix") + en;
        f ? (code = D(p, code), $('form').append(code)) : l.attr({"data-position": p}).append(code);
        close();
    };
}
function effecttbl() {
    if ($('.tbl-wrapper .table').height() > 800) {
        $('.tbl-wrapper').css({overflowY: 'scroll'});
    }
    if ($('.tbl-wrapper').width() > 1000) {
        $('.tbl-wrapper').css({overflowX: 'scroll'});
    }
}
function datecontrol() {
    $('input.date').focusin(function() {
        $(this).datepicker({dateFormat: 'dd/mm/yy'});
    });
    $('span.date').click(function() {
        $(this).prev().datepicker("show");
    });
}
function dropdown() {
    $('input[data-select="select"]').focusin(function() {
        var th = $(this);
        var sl = th.next('.select');
        $('<div class="drop" style="position:fixed;width:100%;height:100%;z-index:44;left:0px;top:0px"></div>').insertAfter(th);
        th.parent().css({overflow: "visible"});
        sl.css({width: th.parent().outerWidth() + 'px'});
        sl.show('clip', 500);
        sl.children('li').click(function() {
            //th.val($(this).attr("value"));
            th.val($(this).find('a').text().trim());
            th.prev("input[type='hidden']").val($(this).attr('value'));
            sl.hide("blind", 500);
            th.next('.drop').remove();
        });
        th.next('.drop').click(function() {
            th.next('.drop').remove();
            sl.hide("blind", 500);
        });
    });
}
function textfocus() {
    $('.form-control').focusin(function() {
        $(this).parent().addClass("focused");
    });
    $('.form-control').focusout(function() {
        if ($(this).val() != "") {
            $(this).prev().addClass("opct-5");
        } else {
            $(this).prev().removeClass("opct-5");
        }
        $(this).parent().removeClass("focused");
    });
}
function tooltip() {
    $('[data-tooltip="tooltip"]').hover(function() {
        var th = $(this), pos = th.attr("pos-tooltip"), val = th.attr("title-tooltip"), top_this = th.position().top, left_this = th.position().left;
        var str = '<div class="tooltip fade ' + pos + ' in" role="tooltip"  style="display:block">\n\
                                                            <div class="tooltip-arrow"></div>\n\
                                                            <div class="tooltip-inner">' + val + '!</div>\n\
                                                       </div>';
        $(str).insertAfter(th);
        var this_width = th.outerWidth(), this_height = th.outerHeight(), tool_width = th.next().outerWidth(), tool_height = th.next().outerHeight();
        if (pos == "top") {
            th.next().css({top: (top_this - tool_height) + 'px', left: (left_this - tool_width / 2 + this_width / 2) + 'px'});
        } else if (pos == "bottom") {
            th.next().css({top: (top_this + this_height + 5) + 'px', left: (left_this - tool_width / 2 + this_width / 2) + 'px'});
        } else if (pos == "right") {
            th.next().css({top: (top_this + this_height / 2 - tool_height / 2) + 'px', left: (left_this + this_width) + 'px'});
        } else if (pos == "left") {
            th.next().css({top: (top_this + this_height / 2 - tool_height / 2) + 'px', left: (left_this - tool_width - 5) + 'px'});
        }
    }, function() {
        $(this).next().remove();
    });
}


