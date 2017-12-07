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

    $('#newAlertComment').submit(function (event) {
        event.preventDefault();
        var data = $(this);
        submitNewAlertCommentForm(data)
    });
})

function submitNewAlertCommentForm(form) {
    var data = $(form).serialize();
    var alertId = $(form).data('alert-id');
    var token = $('#_csrf').attr('content');
    var header = $('#_csrf_header').attr('content');
    $.ajax({
        type: "POST",
        url: "/alert-comment/view/"+alertId+"/save-alert-comment/",
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        data: data,
    });
}
