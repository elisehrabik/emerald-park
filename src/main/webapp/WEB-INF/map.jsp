
    <style>
        #map {
            width: 100%;
            height: 600px;
            border: 2px solid #444;
            z-index: 1;
            background-color: #8FA496;
        }
    </style>

<h2 style="text-align:center;">Interactive Trail Map</h2>
    <div class="map-container">
        <div id="map"></div>
        <div id="trail-info" class="trail-sidebar d-none">
            <h4 id="trail-name"></h4>
            <p id="trail-description"><strong>ID:</strong> <span id="trail-id"></span></p>
            <button onclick="hideSidebar()" class="btn btn-sm btn-secondary">Close</button>
        </div>
    </div>

    <script>
        // Initialize the map using Simple CRS (for fictional/custom maps)
        const map = L.map('map', {
            crs: L.CRS.Simple,
            minZoom: -5,
            maxZoom: 2,
            zoomSnap: 0.25
        });

        // Image dimensions (height x width)
        const bounds = [[0, 0], [769, 1366]];
        L.imageOverlay('images/park-map.png', bounds).addTo(map);

        // Set the initial view more zoomed in
        map.setView([384, 683], -.5);


        // // Optional: enable drawing for creating new trails
        // const drawnItems = new L.FeatureGroup();
        // map.addLayer(drawnItems);

        // const drawControl = new L.Control.Draw({
        //     draw: {
        //         polyline: true,
        //         polygon: false,
        //         rectangle: false,
        //         circle: false,
        //         marker: false,
        //         circlemarker: false
        //     },
        //     edit: {
        //         featureGroup: drawnItems
        //     }
        // });
        // map.addControl(drawControl);

        // Show coordinates when a trail is drawn (for dev use)
        // map.on(L.Draw.Event.CREATED, function (event) {
        //     const layer = event.layer;
        //     drawnItems.addLayer(layer);
        //
        //     const latlngs = layer.getLatLngs();
        //     console.log("Copied coordinates:", JSON.stringify(latlngs));
        //     alert("Trail coordinates copied to console. Paste into your JSON file!");
        // });

        // Load and render trail polylines from JSON
        fetch('data/trails.json')
            .then(res => res.json())
            .then(trails => {
                trails.forEach(trail => {
                    const polyline = L.polyline(trail.coordinates, {
                        color: '#394d40',
                        weight: 4
                    })
                        .bindTooltip(trail.name)
                        .on('mouseover', function () {
                            this.setStyle({ weight: 8 });
                        })
                        .on('mouseout', function () {
                            this.setStyle({ weight: 4 });
                        })
                        .on('click', function () {
                            document.getElementById("trail-name").textContent = trail.name;
                            document.getElementById("trail-id").textContent = trail.trail_id;
                            document.getElementById("trail-info").classList.remove("d-none");
                        })


                            .addTo(map);
                });
            })
            .catch(err => {
                console.error("Failed to load trails.json:", err);
            });

        function hideSidebar() {
            document.getElementById("trail-info").classList.add("d-none");
        }

    </script>

