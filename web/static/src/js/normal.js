function datetoday() {
    setInterval(getTimePC, 1000);
    function getTimePC() {
        var date = new Date();
        $.datepicker.setDefaults(
                $.extend(
                        {'dateFormat': 'MM,dd-mm-yy'},
                $.datepicker.regional['vi']
                        )
                );
        $('.txt_timer').text($.datepicker.formatDate("DD, dd/mm/yy", date) + " | " + date.getHours() +
                ":" + date.getUTCMinutes() + ":" + date.getUTCSeconds() + " GMT +7");
    }
}

