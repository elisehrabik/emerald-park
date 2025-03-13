
<!-- Header -->
<h2 style="margin-left: 4rem; margin-top: 2rem; margin-bottom: 2rem;">Trails</h2>

<!-- Cards -->
<div class="container">
    <div class="row">
    <c:forEach items="${trails}" var="trail">
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <img src="${trail.trail_image}" class="card-img-top" alt="${trail.trail_name} hiking trail at Emerald Park.">
                <div class="card-body">
                    <h5 class="card-title">${trail.trail_name} Trail</h5>
                    <p class="card-text"><small class="text-muted">${trail.trail_distance} miles | ${trail.trail_difficulty}</small></p>
                    <p class="card-text">${trail.trail_description}</p>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
</div>
</body>
</html>
