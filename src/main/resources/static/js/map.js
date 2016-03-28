var map;
var markers = [];


//43.67023	-79.38676
  function updateMap(stopList) {
    deleteMarkers();
    console.log('in update map: ');
    for(var i = 0; i < stopList.length; i++) {
        var stop = stopList[i];

        if (stop.lon != null && stop.lat != null) {
            var markerLoc = { lat: parseFloat(stop.lat), lng: parseFloat(stop.lon) };
            console.log (markerLoc);
            addMarker(markerLoc);
        }
    }

    //TODO: think twice
    map.panTo(markers[0].getPosition());
    map.setZoom(12);
  }

  function initMap() {
    var toronto = {lat: 43.67023, lng: -79.38676};
    map = new google.maps.Map(document.getElementById('map'), {
      zoom: 11,
      center: toronto,
      mapTypeId: google.maps.MapTypeId.TERRAIN
    });

//    if (navigator.geolocation) {
//         navigator.geolocation.getCurrentPosition(function (position) {
//             initialLocation = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
//             map.setCenter(initialLocation);
//         });
//    }

  }


  // Adds a marker to the map and push to the array.
  function addMarker(location) {
//    var marker = new google.maps.Marker({
//      position: location,
//      map: map
//    });

    var contentString = '<div id="content">'+
         '<div id="siteNotice">'+
         '</div>'+
         '<h1 id="firstHeading" class="firstHeading">TTC </h1>'+
         '<div id="bodyContent">'+
         'Predictions: ' +
         '</div>'+
         '</div>';

    var infowindow = new google.maps.InfoWindow({
       content: contentString
     });

    var marker = new google.maps.Marker({
       position: location,
       map: map,
       title: 'TTC'
     });

    marker.addListener('click', function() {
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