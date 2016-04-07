
// Test case:
//  Complete user interaction;

$( document ).ready(function() {

    console.log("in ready");
    $("#agent").append($("<option>").val("ttc").text("Toronto Transit Commission"));
    $("#agent").val("ttc");
    $("#route").append($("<option>").val("51").text("51-Leslie"));
    $("#route").val("51");
    $("#direction").append($("<option>").val("51_1_51").text("North - 51 Leslie towards Steels"));
    $("#direction").val("51_1_51");

    getRouteList ();
    getDirection ();
    getDirectionStopList ();
});
