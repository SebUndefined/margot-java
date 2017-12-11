$(document).ready(function () {
    $('.load-comment').click(function (event) {
        const alertSelected = $(this).parent();
        loadAlertComments(alertSelected);
    });
})

/**
 * Load
 * @param alertSelected
 */
function loadAlertComments(alertSelected) {
    var alertId = alertSelected.data('alert-id');

    $.ajax({
        type: 'GET',
        url: '/alert/view/'+alertId+'/load-comments/',
        timeout: 5000,
        beforeSend: function() {
            $(alertSelected).find('.loader').addClass('loading-part');
        },
        success: function (data) {
            $('.alert-comment').empty();
            $(alertSelected).parent().find('#alertComments-' +alertId).load(this.url);
        },
        complete: function() {
            $(alertSelected).find('.loader').removeClass('loading-part');

        },
        error: function () {
            alert("Cannot load data on " + this.url);
        }
    });

}
