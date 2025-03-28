<div class="container py-4">
    <div class="row">
        <!-- Main content START -->
        <a class="col-xl-12">
            <!-- Title -->
            <h1>All Trails</h1>
    <p class="lead admin-result">
        <c:choose>
            <c:when test="${trails.size() == 1}">There is 1 trail</c:when>
            <c:otherwise>There are ${trails.size()} trails</c:otherwise>
        </c:choose>
    </p>
            <div class="text-center mb-5">
                <a href="admin-add-trail" class="btn btn-primary table-button">Add Trail</a>
            </div>
<c:if test="${trails.size() > 0}">
    <div class="table-responsive my-table" style="padding:0; border-radius: 5px 5px 5px 5px;">
    <table class="table table-bordered my-table">
            <thead>
            <tr  class="my-table">
                <th scope="col" style="width: 120px;"></th>
                <th scope="col">Name</th>
                <th scope="col">Distance</th>
                <th scope="col">Difficulty</th>
                <th scope="col" style="width: 150px;">Time to Complete</th>
                <th scope="col">Description</th>
                <th scope="col">Image</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${trails}" var="trail">
                <tr>
                    <td>
                        <a href="update-trail?trail_id=${trail.trail_id}" class="btn btn-sm btn-outline-primary mb-1 edit-color" style="width:100%">Edit</a>
                        <a href="delete-trail?trail_id=${trail.trail_id}" class="btn btn-sm btn-outline-danger delete-color" style="width:100%">Delete</a>
                    </td>

                    <td>${trail.trail_name}</td>
                    <td>${trail.trail_distance}</td>
                    <td>${trail.trail_difficulty}</td>
                    <td style="width: 150px;">${trail.trail_time}</td>
                    <td>${trail.trail_description}</td>
                    <td>${trail.trail_image}</td>
                    </c:forEach>
                </tbody>
            </table>
            </div>
</c:if>
</div>
    </div>
</div>
</body>
</html>
