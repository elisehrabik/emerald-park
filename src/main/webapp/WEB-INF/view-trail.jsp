<div class="container mt-5">

    <div class="row">
        <div class="col-md-8">
            <img src="${trail.trail_image}" alt="${trail.trail_name}" class="img-fluid rounded">
        </div>

        <div class="col-md-4 justify-content-between">
            <h1 class="mb-4 text-dark trail-heading">${trail.trail_name} Trail</h1>
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

    <div class="container mt-3">
        <h3>Reviews:</h3>

        <c:if test="${empty reviews}">
            <p>No reviews yet for this trail.</p>
        </c:if>

        <c:forEach var="review" items="${reviews}">
            <div class="review mb-3 d-flex align-items-center">
                <a href="view-profile?id=${review.user_id}">
                <div class="review-icon text-center">
                    <i class="fa fa-user-circle fa-3x"></i>
                    <strong class="d-block mt-2">${review.first_name}&nbsp${review.last_name}</strong>
                </div>
                </a>

                <div class="divider"></div>

                <div class="review-content ms-3">
                    <div class="stars">
                        <c:forEach var="i" begin="1" end="5">
                            <span class="${review.rating >= i ? 'filled' : 'empty'}">&#9733;</span>
                        </c:forEach>
                    </div>

                    <p class="fs-5">${review.review_notes}</p>

                    <small class="text-muted">
                        <fmt:formatDate value="${review.review_dateAsDate}" pattern="MMMM dd, yyyy"/>
                    </small>
                </div>
            </div>
        </c:forEach>
        <a class="button-dark" href="add-review?id=${trail.trail_id}">Add Review</a>
    </div>

    <hr>
</div>