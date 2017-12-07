$(document).ready(function () {
    $('.load-comment').click(function (event) {
        var alertSelected = $(this).parent();
        loadAlertComments(alertSelected);
    });
})

/**
 * Load
 * @param alertId
 */
function loadAlertComments(alertSelected) {
    console.log(alertSelected.data('alert-id'));
    const alertId = alertSelected.data('alert-id');

    $.ajax({
        type: 'GET',
        url: '/alert/view/'+alertId+'/load-comments/',
        timeout: 5000,
        beforeSend: function() {
            $(alertSelected).find('.loader').addClass('loading-part');
        },
        success: function (data) {
            $(alertSelected).parent().find('.alert-comment').load(this.url);
        },
        complete: function() {
            $(alertSelected).find('.loader').removeClass('loading-part');

        },
        error: function () {
            alert("Cannot load data on " + this.url);
        }
    });

}
