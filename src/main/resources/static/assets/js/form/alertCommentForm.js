$(document).ready(function () {
    $(".submit-enter").on("keydown", function(e) {
        if(e.altKey && e.keyCode === 13) {
            e.preventDefault();
            e.stopPropagation();
            $(this).val($(this).val() + "\n");
        } else if(e.keyCode === 13) {
            e.preventDefault();
            e.stopPropagation();
            $(this).parents('form').submit();
        }
    });

    $('.newAlertComment').submit(function (event) {
        event.preventDefault();
        var data = $(this);
        submitNewAlertCommentForm(data);
    });
})

function submitNewAlertCommentForm(form) {
    var data = $(form).serialize();
    var alertId = $(form).data('alert-id');
    var token = $('#_csrf-'+alertId).attr('content');
    var header = $('#_csrf_header-'+alertId).attr('content');
    $.ajax({
        type: "POST",
        url: $(form).attr('action'),
        data: data,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function () {
            loadAlertComments($('#alert-'+alertId))

        },

    });
    console.log(data)
}
