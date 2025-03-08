

<div class="container py-4">
    <h2>Admin - All Maintenance Requests</h2>
    <div class="table-responsive small">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th scope="col" style="width: 120px;">Edit/Delete</th>
                <th scope="col" style="width: 100px;">Trail Name</th>
                <th scope="col">Admin</th>
                <th scope="col">Type</th>
                <th scope="col" style="width: 100px;">Request Date</th>
                <th scope="col" style="width: 120px;">Completion Date</th>
                <th scope="col">Notes</th>
            </thead>
            <tbody>
            <c:forEach items="${maintenances}" var="maintenance">
                <tr>
                    <td style="width: 120px;">
                        <a href="edit-maintenance?maintenance_id=${maintenance.maintenance_id}" class="btn btn-sm btn-outline-primary">Edit</a>
                        <a href="delete-maintenance?maintenance_id=${maintenance.maintenance_id}" class="btn btn-sm btn-outline-danger">Delete</a>
                    </td>

                    <td style="width: 100px;">${maintenance.trail_name}</td>
                    <td>${maintenance.admin_first_name}</td>
                    <td>${maintenance.maintenance_type}</td>
                    <td style="width: 100px;">${maintenance.request_date}</td>
                    <td style="width: 120px;">${maintenance.completion_date}</td>
                    <td>${maintenance.maintenance_notes}</td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
