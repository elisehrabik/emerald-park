<div class="container py-4">
    <div class="row">
        <!-- Main content START -->
        <div class="col-xl-12">
            <!-- Title -->
            <h1>All Maintenance Requests</h1>
            <p class="lead admin-result">
                <c:choose>
                    <c:when test="${maintenances.size() == 1}">There is 1 maintenance request</c:when>
                    <c:otherwise>There are ${maintenances.size()} maintenance requests</c:otherwise>
                </c:choose>
            </p>
            <div class="text-center mb-5">
                <a href="admin-add-maintenance" class="btn btn-primary table-button" style="width:20rem;">Add Maintenance Request</a>
            </div>
            <c:if test="${maintenances.size() > 0}">
                <div class="table-responsive my-table" style="padding:0; border-radius: 5px 5px 5px 5px;">
                    <table class="table table-bordered my-table">
            <thead>
            <tr>
                <th scope="col" style="width: 120px;"></th>
                <th scope="col" style="width: 100px;">Trail Name</th>
                <th scope="col">Admin</th>
                <th scope="col">Type</th>
                <th scope="col" style="width: 100px;">Request Date</th>
                <th scope="col" style="width: 120px;">Completion Date</th>
                <th scope="col">Notes</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${maintenances}" var="maintenance">
                <tr>
                    <td style="width: 120px;">
                        <a href="edit-maintenance?maintenance_id=${maintenance.maintenance_id}" class="btn btn-sm btn-outline-primary mb-1 edit-color" style="width:100%">Edit</a>
                        <a href="delete-maintenance?maintenance_id=${maintenance.maintenance_id}" class="btn btn-sm btn-outline-danger delete-color" style="width:100%">Delete</a>
                    </td>

                    <td style="width: 100px;">${maintenance.trail_name}</td>
                    <td>${maintenance.admin_first_name}</td>
                    <td>${maintenance.maintenance_type}</td>
                    <td style="width: 100px;">${maintenance.request_date}</td>
                    <td style="width: 120px;">${maintenance.completion_date}</td>
                    <td>${maintenance.maintenance_notes}</td>
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
