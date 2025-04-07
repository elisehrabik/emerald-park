<div class="container">
    <h1 class="mb-4">${trail.trail_name}</h1>
    <div class="row">
        <div class="col-md-8">
            <img src="${trail.trail_image}" alt="${trail.trail_name}" class="img-fluid rounded">
        </div>

        <div class="col-md-4">
            <h3>Description:</h3>
            <p class="mb-4 fs-5 border-bottom pb-3">${trail.trail_description}</p>

            <h3>Details:</h3>
            <ul class="mb-4 fs-5 ">
                <li><strong>Distance:  </strong> ${trail.trail_distance} miles</li>
                <li><strong>Difficulty:  </strong> ${trail.trail_difficulty}</li>
                <li><strong>Estimated Time:  </strong> ${trail.trail_timeFormatted}</li>
                <li><strong>Category:  </strong> ${trail.categoryName}</li>
            </ul>

            <a href="${pageContext.request.contextPath}/view-trails" class="button-dark">Back to Trails</a>
        </div>
    </div>
    <hr>
</div>