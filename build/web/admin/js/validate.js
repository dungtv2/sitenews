$(function() {
    validata();
});
function validata() {
    var str = '<div class="popover fade top in" role="tooltip">\n\
                        <div class="arrow"></div>\n\
                        <h3 class="popover-title"></h3>\n\
                        <div class="popover-content">Không được để trống!.</div>\n\
                   </div>',flag = false;
    $(str).insertAfter($('input[required],textarea[required]'));
    $('input[required],textarea[required]').focusout(function() {
        var th = $(this), type_validate = th.attr("type-validate"), list, val = th.val(), title, pr = th.parent();
        if (type_validate != null && type_validate != "") {
            list = type_validate.split(" ");
        }
        showpp = function() {
            th.next(".popover").css({display: "block"});
            top_this = th.position().top;
            left_this = th.position().left;
            this_width = th.outerWidth();
            this_height = th.outerHeight();
            tool_width = th.next().outerWidth();
            tool_height = th.next().outerHeight();
            pr.addClass("has-error");
            th.next().css({top: (top_this - tool_height - 10) + 'px', left: (left_this - tool_width / 2 + this_width / 2) + 'px'});
        }
        hidepp = function() {
            th.next(".popover").css({display: "none"});
            pr.removeClass("has-error");
        }
        if (title == "" || title == null) {
            th.next().find(".popover-title").css({display: "none"});
        }
        if (val == "") {
            showpp();
        } else {
            showpp();
            var tt;
            if (list != null && list != "") 
            {
                $.each(list, function(index) {
                    var item = list[index], name, vl;
                    if (item.indexOf(":") > 0) {
                        name = item.substr(0, item.indexOf(":"));
                        vl = item.substr(item.indexOf(":") + 1);
                    } else {
                        name = item;
                    }
                    switch (name) {
                        case "max":
                            val.length > vl ? tt = "Kí tự vượt quá số quy định" : tt = null;
                            break;
                        case "min":
                            if (tt == "" || tt == null) {
                                val.length < vl ? tt = "Kí tự quá ít với số quy định" : tt = null;
                            }
                            break;
                        case "number":
                            if (tt == "" || tt == null) {
                                val.match("^([0-9]+)$") ? tt = null : tt = "Phải là số";
                            }
                            break;
                        case "letter":
                            if (tt == "" || tt == null) {
                                val.match("^([a-zA-Z]?[^0-9]+)$") ? tt = null : tt = "Phải là chữ";
                            }
                            break;
                        case "age":
                            if (tt == "" || tt == null) {
                                val.match("^\\d{2}$") ? tt = null : (10 > val || val > 90 ? tt = "Tuổi trong khoảng từ 10-90" : tt = "Tuổi phải là số");
                            }
                            break;
                        case "date":
                            if (tt == "" || tt == null) {
                                if (!val.match("^\\d{2}/\\d{2}/\\d{4}$")) {
                                    tt = "sai ngày tháng năm";
                                } else {
                                    var date = val.substr(0, 2);
                                    var month = val.substr(3, 2);
                                    var year = val.substr(6, val.length)
                                    if (!(month > 0 && month < 13)) {
                                        tt = "0<month<13";
                                    } else if (!(date > 0 && date < 32)) {
                                        tt = "0<date<31";
                                    } else if (!(year > 1900 && year < 2050)) {
                                        tt = "1900<year<2050";
                                    } else {
                                        tt = null;
                                    }
                                }
                            }
                            break;
                        case "email":
                            if (tt == "" || tt == null) {
                                val.match("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$") ? tt = null : tt = "sai định dạng email";
                            }
                            break;
                        case "phone":
                            if (tt == "" || tt == null) {
                                val.match("^\\d{8,12}$") ? tt = null : tt = "sai định dạng số điện thoại";
                            }
                            break;
                    }
                });
                if (tt !== null && tt !== "") {
                    th.next().find(".popover-content").text(tt);
                } else {
                    hidepp();
                }
            } else {
                hidepp();
            }
        }
    });
     $('.btn-save').click(function(){
          return flag;
     });
}