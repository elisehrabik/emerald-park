<style>
    #map {
        width: 100%;
        height: 600px;
        margin-bottom: 0;
        z-index: 1;
        background-color: #8FA496;
    }
</style>

<h2 style="text-align:center;">Interactive Trail Map</h2>
<div class="map-container">
    <div id="map"></div>
    <div id="trail-info" class="card h-100 custom-shadow position-absolute d-none" style="max-width: 400px; top: 1rem; right: 1rem; z-index: 1000;">
        <a id="trail-link" href="#" class="text-decoration-none text-dark">
            <img id="trail-image" class="card-img-top" alt="Trail image at Emerald Park">
        </a>

        <div class="card-body">
            <a id="trail-link-title" href="#" class="text-decoration-none text-dark">
                <h5 class="card-title" id="trail-name">Trail Name</h5>
                <p class="card-text">
                    <small class="text-muted">
                        <span id="trail-distance"></span> miles |
                        <span id="trail-difficulty"></span> |
                        <span id="trail-category"></span>
                    </small>
                </p>
                <p class="card-text" id="trail-description">Description goes here.</p>
            </a>

            <button onclick="hideSidebar()" class="btn btn-sm btn-secondary mt-2">Close</button>
        </div>
    </div>

</div>

<script>
    window.appUrl = '<%= request.getContextPath() %>';


    // Initialize the map using Simple CRS (for fictional/custom maps)
    const map = L.map('map', {
        crs: L.CRS.Simple,
        minZoom: -1,
        maxZoom: 2,
        zoomSnap: 0.25
    });

    // Image dimensions (height x width)
    const bounds = [[0, 0], [769, 1366]];
    L.imageOverlay('images/park-map.png', bounds).addTo(map);

    // Set the initial view
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

    // // Show coordinates when a trail is drawn (for dev use)
    // map.on(L.Draw.Event.CREATED, function (event) {
    //     const layer = event.layer;
    //     drawnItems.addLayer(layer);
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
                    weight: 4,
                });
                polyline.options.trailId = trail.trail_id;

                polyline
                    .bindTooltip(trail.name)
                    .on('mouseover', function () {
                        this.setStyle({weight: 8});
                    })
                    .on('mouseout', function () {
                        this.setStyle({weight: 4});
                    })
                    .on('click', function () {
                        const trailId = String(this.options.trailId).trim();
                        if (!trailId || trailId.length === 0) return;

                        const url = window.appUrl + "/trail-popup?id=" + trailId;

                        fetch(url)
                            .then(res => res.json())
                            .then(data => {
                                if (data.error) {
                                    alert(data.error);
                                    return;
                                }

                                document.getElementById("trail-name").textContent = data.trail_name + " Trail";
                                // document.getElementById("trail-id").textContent = data.trail_id;
                                document.getElementById("trail-distance").textContent = data.trail_distance;
                                document.getElementById("trail-difficulty").textContent = data.trail_difficulty;
                                document.getElementById("trail-category").textContent = data.categoryName;
                                document.getElementById("trail-description").textContent = data.trail_description;
                                document.getElementById("trail-image").src = data.trail_image;
                                document.getElementById("trail-image").alt = `${data.trail_name} hiking trail image`;

                                const appUrl = window.appUrl.endsWith('/')
                                    ? window.appUrl.slice(0, -1)
                                    : window.appUrl;
                                document.getElementById("trail-link").href = appUrl + "/view-trail?id=" + data.trail_id;

                                document.getElementById("trail-info").classList.remove("d-none");
                            })
                            .catch(err => {
                                console.error("Error fetching trail info:", err);
                            });
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
