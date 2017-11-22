$(function() {
    entityId = 5;
    $("#editResources").click(function () {
        $body = $("body");
        $.ajax({
            type: 'GET',
            url: '/plot/view/'+ entityId + '/edit-resources/',
            timeout: 3000,
            beforeSend: function() { $body.addClass("loading"); },
            success: function (data) {
                $('#plot-resource-graph').load('/plot/view/'+ entityId + '/edit-resources/');
            },
            complete: function() {
                    $body.removeClass("loading");
                    $mesSelect = $('select');
                    for(x=0;x<$mesSelect.length;x++){
                        console.log(x)
                    }

                },
            error: function () {
                alert("Cannot load data");
            }
        });

    }
);
});






