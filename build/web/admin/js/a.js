jQuery.fn.extend({mam: function(options) {
        var defaults = {title: 'xin chào', value: 'hello'};
        var settings = jQuery.extend(defaults, options);
        circle("info", "top-right", settings.title, settings.value);
    }
});
jQuery.fn.extend({oai: function(options) {
        var defaults = {title: 'xin chào', value: 'hello'}, settings = jQuery.extend(defaults, options), t = this, pr = t.parent(), d = "undefined", o = "object";
        c = function(s) {
            return t.children(s).text();
        };
        var e;
        function r(d) {
            if (s) {
                d = d ||
                        window.event;
                var c = 0;
                d.wheelDelta && (c = -d.wheelDelta / 120);
                d.detail && (c = d.detail / 3);
                f(d.target || d.srcTarget || d.srcElement).closest("." + a.wrapperClass).is(b.parent()) && m(c, !0);
                d.preventDefault && !k && d.preventDefault();
                k || (d.returnValue = !1)
            }
        }
        window.addEventListener ? (this.addEventListener("DOMMouseScroll", r, !1), this.addEventListener("mousewheel", r, !1), this.addEventListener("MozMousePixelScroll", r, !1)) : document.attachEvent("onmousewheel", r);
        flip("success", "top-right", c('label'));
    }
});


