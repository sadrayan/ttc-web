var map;
var markers = [];
var infowindow = null;


//43.67023	-79.38676
  function updateMap(stopList) {
    deleteMarkers();
    console.log('in update map: ');
    for(var i = 0; i < stopList.length; i++) {
        var stop = stopList[i];

        if (stop.lon != null && stop.lat != null) {
            var markerLoc = { lat: parseFloat(stop.lat), lng: parseFloat(stop.lon) };
            if ( stop.prediction != null){
                markerLoc.prediction = stop.prediction;
                console.log (markerLoc);
                addMarker(markerLoc);
            }

        }
    }

    //TODO: think twice
    map.panTo(markers[0].getPosition());
    map.setZoom(14);
  }

  function initMap() {
    var toronto = {lat: 43.67023, lng: -79.38676};
    map = new google.maps.Map(document.getElementById('map'), {
      zoom: 11,
      center: toronto,
      mapTypeId: google.maps.MapTypeId.TERRAIN
    });

    infowindow = new google.maps.InfoWindow();

//    if (navigator.geolocation) {
//         navigator.geolocation.getCurrentPosition(function (position) {
//             initialLocation = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
//             map.setCenter(initialLocation);
//         });
//    }

  }


  // Adds a marker to the map and push to the array.
  function addMarker(location) {

    var date = new Date(parseFloat(location.prediction.epochTime));

     var predictionTime = moment(date).format('hh:mm:ss a');

    var contentString = '<div id="content">'+
         '<div id="siteNotice">'+
         '</div>'+
         '<h4 id="firstHeading" class="firstHeading">'+ location.prediction.stopTitle +' </h4>'+
         '<div id="bodyContent">'+
         'Arrival: ' +  predictionTime +
         '</div>'+
         '</div>';




    var marker = new google.maps.Marker({
       position: location,
       map: map,
       title: location.prediction.routeTitle
     });

    marker.addListener('click', function() {
       infowindow.setContent(contentString);
       infowindow.open(map, marker);
    });

    markers.push(marker);
  }

  // Sets the map on all markers in the array.
  function setMapOnAll(map) {
    for (var i = 0; i < markers.length; i++) {
      markers[i].setMap(map);
    }
  }

  // Removes the markers from the map, but keeps them in the array.
  function clearMarkers() {
    setMapOnAll(null);
  }

  // Shows any markers currently in the array.
  function showMarkers() {
    setMapOnAll(map);
  }

  // Deletes all markers in the array by removing references to them.
  function deleteMarkers() {
    clearMarkers();
    markers = [];
  }