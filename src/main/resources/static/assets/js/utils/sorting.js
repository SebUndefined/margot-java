$(document).ready(function () {
    var params = getSearchParams();
    console.log(params)
    $('.sortable').click(function (event) {
        console.log($(this).data('sorted-by'));

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
        console.log(params)
        url = window.location.protocol +"//"+ window.location.hostname + (window.location.port ? ':'+ window.location.port: "") + window.location.pathname  + "?";
        for (const key of Object.keys(params)) {
            url = url + key + "=" + params[key] + "&";
        }
        console.log("Redirect to " + url);
        window.location.replace(url);
    })
})