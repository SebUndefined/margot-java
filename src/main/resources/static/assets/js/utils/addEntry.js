$(document).ready(function() {

    var deleter         = $(".input_fields_delete"); //Fields wrapper
    var add_button      = $("#add_field_button"); //Add button ID

    var x = 1; //initlal text box count

    $(add_button).click(function (e) {
        var idNewItem = parseInt($('#editPlotResourceForm').children('div').last().attr('id')) + 1;
        var insertion = "<div class='row' id='"+ idNewItem +"'>\n" +
            "                            <div class='input-field col l5 m5 s5'>\n" +
            "                                <input type='hidden' id='" + "plotResourceList"+ idNewItem +".plot" +"' name='" + "plotResourceList["+ idNewItem +"].plot" +"' />\n" +
            "                                <select th:field=\"*{plotResourceList[__${rowStat.index}__].resource}\">\n" +
            "                                    <option id=\"resourceForm\" th:each=\"resource: ${resources}\" th:value=\"${resource.id}\" th:text=\"${resource.name}\"></option>\n" +
            "                                </select>\n" +
            "                                <label for='resourceForm'>Resource</label>\n" +
            "                            </div>\n" +
            "                            <div class=\"input-field col l5 m5 s5\">\n" +
            "                                <input id='proportionForm' type=\"text\" th:field=\"*{plotResourceList[__${rowStat.index}__].proportion}\" th:value=\"*{plotResourceList[__${rowStat.index}__].proportion}\" />\n" +
            "                                <label for=\"proportionForm\">Proportion</label>\n" +
            "                            </div>\n" +
            "                            <div class=\"input-field col l2 m2 s2\">\n" +
            "                                <a class=\"input_fields_delete\"><i class=\"material-icons left\">delete</i></a>\n" +
            "                            </div>\n" +
            "                        </div>"








        $( insertion ).insertBefore( "#add_field_button" );
        $('select').material_select();
    });
    $(deleter).click(function (e) {

        e.preventDefault();
        $(this).parent('div').parent().remove();
    });
});
