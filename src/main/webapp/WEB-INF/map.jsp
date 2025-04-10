
    <style>
        #map {
            width: 100%;
            height: 600px;
            border: 2px solid #444;
        }
    </style>

<h2 style="text-align:center;">Interactive Trail Map</h2>
<div id="map"></div>

    <script>
        // Initialize the map using Simple CRS (for fictional/custom maps)
        const map = L.map('map', {
            crs: L.CRS.Simple,
            minZoom: -1
        });

        // Set dimensions of your custom background map image
        const bounds = [[0, 0], [800, 1000]]; // height, width
        L.imageOverlay('images/park-map.png', bounds).addTo(map);
        map.fitBounds(bounds);

        // Optional: enable drawing for creating new trails
        const drawnItems = new L.FeatureGroup();
        map.addLayer(drawnItems);

        const drawControl = new L.Control.Draw({
            draw: {
                polyline: true,
                polygon: false,
                rectangle: false,
                circle: false,
                marker: false,
                circlemarker: false
            },
            edit: {
                featureGroup: drawnItems
            }
        });
        map.addControl(drawControl);

        // Show coordinates when a trail is drawn (for dev use)
        map.on(L.Draw.Event.CREATED, function (event) {
            const layer = event.layer;
            drawnItems.addLayer(layer);

            const latlngs = layer.getLatLngs();
            console.log("Copied coordinates:", JSON.stringify(latlngs));
            alert("Trail coordinates copied to console. Paste into your JSON file!");
        });

        // Load and render trail polylines from JSON
        fetch('data/trails.json')
            .then(res => res.json())
            .then(trails => {
                trails.forEach(trail => {
                    const polyline = L.polyline(trail.coordinates, {
                        color: 'green',
                        weight: 4
                    })
                        .bindTooltip(trail.name)
                        .on('mouseover', function () {
                            this.setStyle({ weight: 8, color: 'darkgreen' });
                        })
                        .on('mouseout', function () {
                            this.setStyle({ weight: 4, color: 'green' });
                        })
                        .on('click', function () {
                            
                        })
                        .addTo(map);
                });
            })
            .catch(err => {
                console.error("Failed to load trails.json:", err);
            });
    </script>

