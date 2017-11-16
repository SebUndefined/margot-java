$(function(){
    // Activate the side menu
    $(".button-collapse").sideNav({
        menuWidth: 250, // Default is 300
        edge: 'left', // Choose the horizontal origin
        //closeOnClick: true, // Closes side-nav on <a> clicks, useful for Angular/Meteor
        draggable: true // Choose whether you can drag to open on touch screens
    });
    $("#openpersonnalmenu").sideNav();
    $('.collapsible').collapsible();
    Materialize.updateTextFields();
    $('select').material_select();
    $('.modal').modal();
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 15, // Creates a dropdown of 15 years to control year,
        format: 'dd-mm-yyyy',
        today: 'Today',
        clear: 'Clear',
        close: 'Ok',
        closeOnSelect: false // Close upon selecting a date,
    });
    $('ul.tabs').tabs();
    //$('ul.tabs').tabs('select_tab', 'tab_id');



});