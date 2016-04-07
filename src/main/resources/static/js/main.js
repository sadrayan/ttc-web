$( window ).ready(function() {
    getAgentList ();
});

$( "#agent" ).change(function() {
    getRouteList();
});

$("#route").change(function() {
    getDirection ();
});

$("#direction").change(function() {
    getDirectionStopList();
});

function getAgentList () {
  $.getJSON("/agentList/", function(result) {
      $.each(result, function(i, item) {
          //  console.log(item);
          $("#agent").append($("<option>").val(item.tag).text(item.title));
      });
  });
}

function getRouteList () {
    var data = {
        agentTag: $("#agent").val(),
    };

    $.getJSON("/routeList/", data, function(result) {
        $("#route").empty();
        $("#route").append($("<option>").val(0).text("Select a location..."));
        $.each(result, function(i, item) {
            //  console.log(item);
            $("#route").append($("<option>").val(item.tag).text(item.title));
        });
    });
}

function getDirection () {
    var data = {
        agentTag: $("#agent").val(),
        routeTag: $("#route").val(),
    };

    $.getJSON("/directionList/", data, function(result) {
        $("#direction").empty();
        $("#direction").append($("<option>").val(0).text("Your Path"));
        $.each(result, function(i, direction) {
            console.log(direction);
            $("#direction").append($("<option>").val(direction.tag).text(direction.title));
        });
    });

    deleteMarkers();
}

function getDirectionStopList () {
   var data = {
        agentTag: $("#agent").val(),
        routeTag: $("#route").val(),
        directionTag: $("#direction").val(),
    };

    $.getJSON("/directionStopList/", data, function(result) {
        updateMap(result);
    });
}








