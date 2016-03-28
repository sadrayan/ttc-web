$( window ).load(function() {

  $.getJSON("/agentList/", function(result) {
      $.each(result, function(i, item) {
          //  console.log(item);
          $("#agent").append($("<option>").val(item.tag).text(item.title));
      });
  });

});


$( "#agent" ).change(function() {
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
});


$("#route").change(function() {
    var data = {
        agentTag: $("#agent").val(),
        routeTag: $( this ).val(),
    };

    $.getJSON("/directionStopList/", data, function(result) {
        $("#direction").empty();
        $("#direction").append($("<option>").val(0).text("Your Path"));
        $.each(result, function(i, direction) {
            console.log(direction);
            $("#direction").append($("<option>").val(direction.tag).text(direction.title));
        });
    });

    deleteMarkers();
});

$("#direction").change(function() {
   var data = {
        agentTag: $("#agent").val(),
        routeTag: $("#route").val(),
    };

    $.getJSON("/directionStopList/", data, function(result) {

        $.each(result, function(i, direction) {
            console.log(direction);
            if (direction.tag == $("#direction").val()) {
                updateMap(direction.stopList)
            }
        });

    });
});








