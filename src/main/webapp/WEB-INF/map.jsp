<style>
    #map {
        width: 100%;
        height: 600px;
        margin-bottom: -3rem;
        z-index: 1;
        background-color: #8FA496;
    }
</style>

<h2 class="mt-2" style="text-align:center;">Map of Emerald Park</h2>
<div class="map-container">
    <div id="map"></div>
    <div id="trail-info" class="card h-100 custom-shadow position-absolute d-none trail-popup">

        <button type="button" class="btn-close position-absolute top-0 end-0 mt-3 me-3"
                aria-label="Close" onclick="hideSidebar()"></button>

        <a id="trail-link" href="#" class="text-decoration-none text-dark mt-5 mx-4">
            <img id="trail-image" class="card-img-top trail-popup-img" alt="Trail image at Emerald Park" style="border-radius: 5px;">
        </a>

        <div class="card-body p-4">
            <a id="trail-link-title" href="#" class="text-decoration-none text-dark">
                <h5 class="card-title trail-popup-title" id="trail-name">Trail Name</h5>
            </a>
                <p class="card-text">
                    <small class="text-muted">
                        <span id="trail-distance"></span> miles |
                        <span id="trail-difficulty"></span> |
                        <span id="trail-category"></span>
                    </small>
                </p>
                <p class="card-text" id="trail-description">Description goes here.</p>

        </div>
    </div>


</div>

<script>
    window.appUrl = '<%= request.getContextPath() %>';


    // Initialize the map using Simple CRS (for fictional/custom maps)
    const map = L.map('map', {
        crs: L.CRS.Simple,
        minZoom: -2,
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
    let selectedPolyline = null;

    fetch('data/trails.json')
        .then(res => res.json())
        .then(trails => {
            trails.forEach(trail => {
                const polyline = L.polyline(trail.coordinates, {
                    color: '#394d40',
                    weight: 4,
                });

                const clickBuffer = L.polyline(trail.coordinates, {
                    color: '#000',
                    opacity: 0.0,
                    weight: 20,
                    interactive: true,
                    pane: 'shadowPane'
                })
                    .on('click', function () {
                        polyline.fire('click');
                    })
                    .on('mouseover', function () {
                        polyline.fire('mouseover');
                    })
                    .on('mouseout', function () {
                        polyline.fire('mouseout');
                    })
                    .addTo(map);



                polyline.options.trailId = trail.trail_id;

                polyline
                    .bindTooltip(trail.name)
                    .on('mouseover', function () {
                        if (selectedPolyline !== this) {
                            this.setStyle({ weight: 6 });
                        }
                        polyline.bindTooltip(trail.name, {
                            permanent: false,
                            direction: "top",
                            offset: [0, -6],
                            className: "trail-label"
                        });

                    })
                    .on('mouseout', function () {
                        if (selectedPolyline !== this) {
                            this.setStyle({ weight: 4 });
                        }
                    })
                    .on('click', function () {
                        // Reset previously selected trail
                        if (selectedPolyline) {
                            selectedPolyline.setStyle({ weight: 4 });
                            selectedPolyline.unbindTooltip();
                            selectedPolyline.bindTooltip(trail.name, {
                                permanent: false,
                                direction: "top",
                                offset: [0, -6],
                                className: "trail-label"
                            });
                        }

                        // Set this as the new selected trail
                        selectedPolyline = this;
                        this.setStyle({ weight: 8 });
                        this.unbindTooltip();

                        this.bindTooltip(trail.name, {
                            permanent: true,
                            direction: "top",
                            offset: [0, -6],
                            className: "trail-label"
                        }).openTooltip();

                        const trailId = String(this.options.trailId).trim();
                        if (!trailId) return;

                        const url = window.appUrl + "/trail-popup?id=" + trailId;

                        fetch(url)
                            .then(res => res.json())
                            .then(data => {
                                if (data.error) {
                                    alert(data.error);
                                    return;
                                }

                                document.getElementById("trail-name").textContent = data.trail_name + " Trail";
                                document.getElementById("trail-distance").textContent = data.trail_distance;
                                document.getElementById("trail-difficulty").textContent = data.trail_difficulty;
                                document.getElementById("trail-category").textContent = data.categoryName;
                                document.getElementById("trail-description").textContent = data.trail_description;
                                document.getElementById("trail-image").src = data.trail_image;
                                document.getElementById("trail-image").alt = `${data.trail_name} hiking trail image`;

                                const appUrl = window.appUrl.endsWith('/')
                                    ? window.appUrl.slice(0, -1)
                                    : window.appUrl;
                                const trailUrl = appUrl + "/view-trail?id=" + data.trail_id;

                                document.getElementById("trail-link").href = trailUrl;
                                document.getElementById("trail-link-title").href = trailUrl;

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
