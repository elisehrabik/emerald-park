
<div class="container py-4">
    <div class="col d-flex justify-content-between align-items-center">
        <h2 class="">My Favorites</h2>
        <!-- Responsive toggler START -->
        <button class="btn btn-primary d-lg-none basic-button-green" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasSidebar" aria-controls="offcanvasSidebar">
            <i class="bi bi-list fs-4"></i>
        </button>
        <!-- Responsive toggler END -->
    </div>

    <div class="row">
        <!-- Main Content (Trails) -->
        <div>
            <!-- Cards -->
            <c:choose>
                <c:when test="${not empty trails}">
                    <div class="row g-4">
                    <c:forEach items="${trails}" var="trail">
                        <div class="col-sm-12 col-md-6 col-lg-4">
                        <div class="card h-100 custom-shadow hover-grow position-relative">

                            <a href="view-trail?id=${trail.trail_id}">
                                <img src="${trail.trail_image}" class="card-img-top" alt="${trail.trail_name} hiking trail at Emerald Park.">
                            </a>

                            <!-- Floating Heart -->
                            <c:choose>
                                <c:when test="${not empty activeUser}">
                                    <form action="${favoriteTrailIds.contains(trail.trail_id) ? 'remove-favorite' : 'add-favorite'}"
                                          method="post"
                                          class="position-absolute top-0 end-0 m-2 z-1">
                                        <input type="hidden" name="trailId" value="${trail.trail_id}" />
                                        <button type="submit" class="favorite-btn btn btn-link p-0 m-0 border-0"
                                                style="font-size: 1.5rem; color: white;">
                                            <i class="bi ${favoriteTrailIds.contains(trail.trail_id) ? 'bi-heart-fill' : 'bi-heart'}"></i>
                                        </button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form action="add-favorite" method="post"
                                          class="position-absolute top-0 end-0 m-2 z-1">
                                        <input type="hidden" name="trailId" value="${trail.trail_id}" />
                                        <button type="submit" class="btn btn-link p-0 m-0 border-0"
                                                style="font-size: 1.5rem; color: white;">
                                            <i class="bi bi-heart"></i>
                                        </button>
                                    </form>
                                </c:otherwise>
                            </c:choose>

                            <div class="card-body">
                                <a href="view-trail?id=${trail.trail_id}" class="text-decoration-none text-dark">
                                    <h5 class="card-title">${trail.trail_name} Trail</h5>
                                    <p class="card-text">
                                        <small class="text-muted">${trail.trail_distance} miles |
                                                ${trail.trail_difficulty} |
                                                ${trail.categoryName}</small>
                                    </p>
                                    <p class="card-text">${trail.trail_description}</p>
                                </a>
                            </div>

                        </div> <!-- End Card -->
                    </div> <!-- End Column -->
                    </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <p class="lead mb-5">You haven't favorited any trails yet.</p>
                    <a class="button-dark d-flex justify-content-center align-items-center mx-auto mx-lg-0 mb-5"
                       href="${appURL}/view-trails">
                        View All Trails
                    </a>
                    <div style="height: 8rem;"></div>

                </c:otherwise>
            </c:choose>

        </div><!-- End Main Content -->

    </div><!-- End Row -->
</div><!-- End Container -->



<script>
    document.querySelectorAll('.favorite-btn').forEach(button => {
        button.addEventListener('click', function(e) {

            const icon = this.querySelector('i');
            const isFavorite = icon.classList.contains('bi-heart-fill');
            if (isFavorite) {
                icon.classList.remove('bi-heart-fill');
                icon.classList.add('bi-heart');
            } else {
                icon.classList.remove('bi-heart');
                icon.classList.add('bi-heart-fill');
            }

            this.submit();
        });
    });

</script>
