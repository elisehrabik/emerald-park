<%@ page import="edu.kirkwood.emeraldTrails.model.Trail" %><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="en-US" />
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin - All Reviews</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<!-- Navigation (main and park) -->
<%--<%@include file="../../main-nav.jsp" %>--%>
<%@include file="park-nav.jsp" %>
<div class="container py-4">
    <h2>Admin - All Reviews</h2>
    <div class="table-responsive small">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th scope="col" style="width: 120px;">Edit/Delete</th>
                <th scope="col">Trail Name</th>
                <th scope="col">Visitor Email</th>
                <th scope="col">Date</th>
                <th scope="col">Rating</th>
                <th scope="col">Time to Complete</th>
                <th scope="col" style="width: 200px;">Notes</th>
            </thead>
            <tbody>
            <c:forEach items="${reviews}" var="review">
                <tr>
                    <td style="width: 120px;">
                        <a href="edit-review?review_id=${review.review_id}" class="btn btn-sm btn-outline-primary">Edit</a>
                        <a href="delete-review?review_id=${review.review_id}" class="btn btn-sm btn-outline-danger">Delete</a>
                    </td>

                    <td>${review.trail_name}</td>
                    <td>${review.visitor_email}</td>
                    <td>${review.review_date}</td>
                    <td>${review.rating}</td>
                    <td>${review.review_time}</td>
                    <td style="width: 300px;">${review.review_notes}</td>
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
