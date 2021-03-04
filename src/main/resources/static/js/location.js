function showStationLocation(latitude, longitude) {
    	var mymap = L.map('mapid').setView([ latitude,longitude], 2);

    	L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
    		maxZoom: 10,
    		attribution: "",
    		id: 'mapbox/streets-v11',
    		tileSize: 600,
    		zoomOffset: -1
    	}).addTo(mymap);


    	L.circle([latitude,longitude], {
    		color: 'red',
    		fillColor: '#f03',
    		fillOpacity: 0.1,
    		radius: 1000000
    	}).addTo(mymap);

    	var icon = L.icon({
            iconUrl: '/resources/icons/iss_icon.png',

            iconSize:     [60, 60], // size of the icon
            iconAnchor:   [30, 30], // point of the icon which will correspond to marker's location
            popupAnchor:  [-3, -3] // point from which the popup should open relative to the iconAnchor
        });

        L.marker([latitude, longitude], {icon: icon}).addTo(mymap);

  }


