
<!-- Header -->
<h2 style="margin-left: 4rem; margin-top: 2rem; margin-bottom: 2rem;">Trails</h2>
<div class="container py-4">
    <a href="admin-add-trail" class="btn btn-primary" role="button">Add New Trail</a>
    <h2>Admin - All Trails</h2>
    <div class="table-responsive small">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th scope="col" style="width: 120px;">Edit/Delete</th>
                <th scope="col">Name</th>
                <th scope="col">Distance</th>
                <th scope="col">Difficulty</th>
                <th scope="col" style="width: 150px;">Time to Complete</th>
                <th scope="col">Description</th>
                <th scope="col" style="width: 120px;">Allows Bikes?</th>
                <th scope="col">Image</th>
            </thead>
            <tbody>
            <c:forEach items="${trails}" var="trail">
                <tr>
                    <td style="width: 120px;">
                        <a href="edit-trail?trail_id=${trail.trail_id}" class="btn btn-sm btn-outline-primary">Edit</a>
                        <a href="delete-trail?trail_id=${trail.trail_id}" class="btn btn-sm btn-outline-danger">Delete</a>
                    </td>

                    <td>${trail.trail_name}</td>
                    <td>${trail.trail_distance}</td>
                    <td>${trail.trail_difficulty}</td>
                    <td style="width: 150px;">${trail.trail_time}</td>
                    <td>${trail.trail_description}</td>
                    <td style="width: 120px;">${trail.allows_bikes}</td>
                    <td>${trail.trail_image}</td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
