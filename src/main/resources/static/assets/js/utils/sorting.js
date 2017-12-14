$(document).ready(function () {
    var params = getSearchParams();
    $('.sortable').click(function (event) {


        if (params.sort === $(this).data('sorted-by')) {
            if (params.direction === 'DESC' || typeof params.direction === "undefined") {
                params.direction = 'ASC';
            }
            else {
                params.direction = 'DESC';
            }
        }
        else {
            params.sort = $(this).data('sorted-by');
        }

        url = window.location.protocol +"//"+ window.location.hostname + (window.location.port ? ':'+ window.location.port: "") + window.location.pathname  + "?";
        for (const key of Object.keys(params)) {
            url = url + key + "=" + params[key] + "&";
        }

        window.location.replace(url);
    })
})