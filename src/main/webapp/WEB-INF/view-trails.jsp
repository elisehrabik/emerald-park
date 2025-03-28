<div class="container py-4">

    <div class="col d-flex justify-content-between align-items-center">
        <h2 class="mb-4">Trails</h2>
        <!-- Responsive toggler START -->
        <button class="btn btn-primary d-lg-none basic-button-green" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasSidebar" aria-controls="offcanvasSidebar">
            <i class="bi bi-list fs-4"></i>
        </button>
        <!-- Responsive toggler END -->
    </div>
    <div class="row">
        <div class="col-lg-9">
            <p class="lead">${fn:length(trails)} trail${fn:length(trails) != 1 ? "s" : ""} shown</p>

<!-- Cards -->
    <div class="row g-4">
    <c:forEach items="${trails}" var="trail">
        <div class="col-sm-12 col-md-6 col-lg-4">
            <div class="card h-100">
                <img src="${trail.trail_image}" class="card-img-top" alt="${trail.trail_name} hiking trail at Emerald Park.">
                <div class="card-body">
                    <h5 class="card-title">${trail.trail_name} Trail</h5>
                    <p class="card-text"><small class="text-muted">${trail.trail_distance} miles | ${trail.trail_difficulty} | ${trail.categoryName}</small></p>
                    <p class="card-text">${trail.trail_description}</p>
<%--                    <a href="${appURL}/trail-info?trail_id=${trail.id}" class="btn btn-secondary">More Info</a>--%>
                </div><!-- End Card Body -->
            </div><!-- End Card -->
        </div><!-- End Column -->
    </c:forEach>
    </div> <!-- End Product Row -->
        </div><!-- End 3/4 -->
        <%@include file="trails-sidebar.jspf"%>
    </div><!-- End 3/4 (Products), 1/4 (Sidebar) Row -->
</div><!-- End Container -->