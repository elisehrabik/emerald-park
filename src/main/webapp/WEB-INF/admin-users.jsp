<div class="container py-4">
    <div class="row">
        <!-- Main content START -->
        <div class="col-xl-12">
            <!-- Title -->
            <h1>All Users</h1>
            <p class="lead admin-result">
                <c:choose>
                    <c:when test="${users.size() == 1}">There is 1 user</c:when>
                    <c:otherwise>There are ${users.size()} users</c:otherwise>
                </c:choose>
            </p>
<%--            <div class="text-center mb-5">--%>
<%--                <a href="admin-add-user" class="btn btn-primary table-button">Add User</a>--%>
<%--            </div>--%>
            <c:if test="${users.size() > 0}">
                <div class="table-responsive my-table" style="padding:0; border-radius: 5px 5px 5px 5px;">
                    <table class="table table-bordered my-table">
                        <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">First name</th>
                            <th scope="col">Last name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Phone</th>
                            <th scope="col">Language</th>
                            <th scope="col">Status</th>
                            <th scope="col">Privileges</th>
                            <th scope="col">Created At</th>
                            <th scope="col">Timezone</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="user">
                        <tr>
                            <td>
                                <a href="admin-update-user?user_id=${fn:escapeXml(user.userId)}" class="btn btn-sm btn-outline-primary mb-1 px-4 edit-color" style="width:100%">Edit</a>
<%--                                <a href="delete-user?user_id=${user.userId}" class="btn btn-sm btn-outline-danger delete-color" style="width:100%">Delete</a>--%>
                            </td>
                            <td>${fn:escapeXml(user.firstName)}</td>
                            <td style="width: 10em;">${fn:escapeXml(user.lastName)}</td>
                            <td>${fn:escapeXml(user.email)}</td>
                            <td>${fn:escapeXml(user.phone)}</td>
                            <td >${fn:escapeXml(user.language)}</td>
                            <td>${fn:escapeXml(user.status)}</td>
                            <td>${fn:escapeXml(user.privileges)}</td>
                            <td>
                                <c:if test="${not empty user.createdAtDate}">
                                    <fmt:formatDate
                                            value="${user.createdAtDate}"
                                            pattern="MMMM d, yyyy h:mm a z"
                                            timeZone="${activeUser.timezone}" />
                                </c:if>
                            </td>
                            <td>${fn:escapeXml(user.timezone)}</td>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div> <%-- Col END --%>
    </div> <%-- Row END --%>
</div> <%-- Container END --%>
