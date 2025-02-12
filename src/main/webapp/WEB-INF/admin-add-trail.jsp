<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add New Trail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<%--<%@include file="../../main-nav.jsp"%>--%>
<div class="container py-4">
    <a href="trails" class="btn btn-primary mb-4" role="button">View All Trails</a>
    <h2>Add New Trail</h2>
    <c:if test="${not empty trailAdded}">
        <div class="alert ${trailAdded ? 'alert-success' : 'alert-danger'}" role="alert">
                ${trailAddedMessage}
        </div>
    </c:if>
    <form class="row g-3" method="POST" action="admin-add-trail">
        <!-- <div class="col-md-3">
            <label for="trailId" class="form-label">Trail Id</label>
            <input type="text" class="form-control <c:choose><c:when test='${trailIdError == true}'>is-invalid</c:when><c:when test='${trailIdError == false}'>is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="trailId" name="trailId" value="${trailId}">
            <div class="<c:choose><c:when test='${trailIdError == true}'>invalid-feedback</c:when><c:when test='${trailIdError == false}'>valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                ${trailIdMessage}
            </div>
        </div> -->
        <div class="col-md-12">
            <label for="trailName" class="form-label">Trail Name</label>
            <input type="text" class="form-control <c:choose><c:when test='${trailNameError == true}'>is-invalid</c:when><c:when test='${trailNameError == false}'>is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="trailName" name="trailName" value="${trailName}">
            <div class="<c:choose><c:when test='${trailNameError == true}'>invalid-feedback</c:when><c:when test='${trailNameError == false}'>valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                ${trailNameMessage}
            </div>
        </div>
        <div class="col-md-4">
            <label for="trailDistance" class="form-label">Trail Distance (miles)</label>
            <input type="text" class="form-control <c:choose><c:when test='${trailDistanceError == true}'>is-invalid</c:when><c:when test='${trailDistanceError == false}'>is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="trailDistance" name="trailDistance" value="${trailDistance}">
            <div class="<c:choose><c:when test='${trailDistanceError == true}'>invalid-feedback</c:when><c:when test='${trailDistanceError == false}'>valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                ${trailDistanceMessage}
            </div>
        </div>
        <div class="col-md-4">
            <label for="trailDifficulty" class="form-label">Trail Difficulty</label>
            <select class="form-select" id="trailDifficulty" name="trailDifficulty">
                <option value="EASY" <c:if test="${trailDifficulty == 'EASY'}">selected</c:if>>Easy</option>
                <option value="MODERATE" <c:if test="${trailDifficulty == 'MODERATE'}">selected</c:if>>Moderate</option>
                <option value="HARD" <c:if test="${trailDifficulty == 'HARD'}">selected</c:if>>Hard</option>
            </select>
        </div>
        <div class="col-md-4">
            <label for="trailTime" class="form-label">Trail Time (HH:mm)</label>
            <input type="text" class="form-control <c:choose><c:when test='${trailTimeError == true}'>is-invalid</c:when><c:when test='${trailTimeError == false}'>is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="trailTime" name="trailTime" value="${trailTime}">
            <div class="<c:choose><c:when test='${trailTimeError == true}'>invalid-feedback</c:when><c:when test='${trailTimeError == false}'>valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                ${trailTimeMessage}
            </div>
        </div>
        <div class="col-md-12">
            <label for="trailDescription" class="form-label">Trail Description</label>
            <textarea class="form-control <c:choose><c:when test='${trailDescriptionError == true}'>is-invalid</c:when><c:when test='${trailDescriptionError == false}'>is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="trailDescription" name="trailDescription">${trailDescription}</textarea>
            <div class="<c:choose><c:when test='${trailDescriptionError == true}'>invalid-feedback</c:when><c:when test='${trailDescriptionError == false}'>valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                ${trailDescriptionMessage}
            </div>
        </div>
        <div class="col-md-6">
            <label for="allowsBikes" class="form-label">Allows Bikes</label>
            <select class="form-select" id="allowsBikes" name="allowsBikes">
                <option value="true" <c:if test="${allowsBikes == true}">selected</c:if>>Yes</option>
                <option value="false" <c:if test="${allowsBikes == false}">selected</c:if>>No</option>
            </select>
        </div>
        <div class="col-md-6">
            <label for="trailImage" class="form-label">Trail Image URL</label>
            <input type="text" class="form-control <c:choose><c:when test='${trailImageError == true}'>is-invalid</c:when><c:when test='${trailImageError == false}'>is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="trailImage" name="trailImage" value="${trailImage}">
            <div class="<c:choose><c:when test='${trailImageError == true}'>invalid-feedback</c:when><c:when test='${trailImageError == false}'>valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                ${trailImageMessage}
            </div>
        </div>
        <div class="col-12">
            <button class="btn btn-dark" type="submit">Submit form</button>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
