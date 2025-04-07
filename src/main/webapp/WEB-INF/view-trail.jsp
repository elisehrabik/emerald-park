<div class="container mt-5">

    <div class="row">
        <div class="col-md-8">
            <img src="${trail.trail_image}" alt="${trail.trail_name} trail" class="img-fluid rounded">
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
                <div class="review-icon text-center" data-bs-toggle="modal" data-bs-target="#userModal-${review.user_id}" style="cursor: pointer;">
                    <i class="fa fa-${review.avatar} fa-3x user-icon"></i>
                    <strong class="d-block mt-2">
                        <c:choose>
                            <c:when test="${empty review.first_name and empty review.last_name}">
                                Anonymous
                            </c:when>
                            <c:otherwise>
                                ${review.first_name}&nbsp${review.last_name}
                            </c:otherwise>
                        </c:choose>
                    </strong>
                </div>

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

    <!-- Modal for each user -->
    <div class="modal fade" id="userModal-${review.user_id}" tabindex="-1" aria-labelledby="userModalLabel-${review.user_id}" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <i class="fa fa-${review.avatar}  fa-3x user-icon"></i>
                    <h5 class="modal-title ms-3" id="userModalLabel-${review.user_id}">
                        <c:choose>
                            <c:when test="${empty review.first_name and empty review.last_name}">
                                Anonymous
                            </c:when>
                            <c:otherwise>
                                ${review.first_name}&nbsp${review.last_name}
                            </c:otherwise>
                        </c:choose>
                    </h5>

                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body d-flex align-items-center">
                    <p class="mb-0"><strong>Pronouns: </strong>${review.pronouns}</p>
                </div>
                <div class="modal-footer">
                    <p>Joined <fmt:formatDate value="${review.created_dateAsDate}" pattern="MMMM d, yyyy"/></p>
                </div>
            </div>
        </div>
    </div>

    </c:forEach>
    <a class="button-dark" href="add-review?id=${trail.trail_id}">Add Review</a>
</div>

    <hr>
</div>
