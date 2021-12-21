/**
 * 
 */

$(".sidebar_categoty").children().hide();
$(".sidebar_categoty").on("mouseover", function() {
    $(".sidebar_categoty").children().hide();
    $(this).children().show();
});

setTimeout(function() {
    $(".sidebar_categoty").on("mouseout", function() {

        $(this).children().hide();
    });

});