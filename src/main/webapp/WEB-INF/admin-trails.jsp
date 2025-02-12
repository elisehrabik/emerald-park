<%@ page import="edu.kirkwood.emeraldTrails.model.Trail" %><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="en-US" />
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin - All Trails</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<!-- Navigation (main and park) -->
<%--<%@include file="../../main-nav.jsp" %>--%>
<%@include file="park-nav.jsp" %>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
