
<div class="container py-4">
    <div class="card transparent-overlay border rounded-3 p-5">
    <h2 class="text-light">Add New Trail</h2>
    <a href="trails" class="table-button mb-5 text-center">View All Trails</a>
    <c:if test="${not empty trailAdded}">
        <div class="alert ${trailAdded ? 'alert-success' : 'alert-danger'}" role="alert">
                ${trailAddedMessage}
        </div>
    </c:if>


    <form class="row g-3" method="POST" action="${appURL}/admin-add-trail">
        <!-- <div class="col-md-3">
            <label for="trailId" class="form-label">Trail Id</label>
            <input type="text" class="form-control <c:choose><c:when test='${trailIdError == true}'>is-invalid</c:when><c:when test='${trailIdError == false}'>is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="trailId" name="trailId" value="${trailId}">
            <div class="<c:choose><c:when test='${trailIdError == true}'>invalid-feedback</c:when><c:when test='${trailIdError == false}'>valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                ${trailIdMessage}
            </div>
        </div> -->
        <div class="col-md-12">
            <label for="trailName" class="form-label text-light">Trail Name</label>
            <input type="text" class="form-control <c:choose><c:when test='${trailNameError == true}'>is-invalid</c:when><c:when test='${trailNameError == false}'>is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="trailName" name="trailName" value="${trailName}">
            <div class="<c:choose><c:when test='${trailNameError == true}'>invalid-feedback</c:when><c:when test='${trailNameError == false}'>valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                ${trailNameMessage}
            </div>
        </div>
        <div class="col-md-4">
            <label for="trailDistance" class="form-label text-light">Trail Distance (miles)</label>
            <input type="text" class="form-control <c:choose><c:when test='${trailDistanceError == true}'>is-invalid</c:when><c:when test='${trailDistanceError == false}'>is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="trailDistance" name="trailDistance" value="${trailDistance}">
            <div class="<c:choose><c:when test='${trailDistanceError == true}'>invalid-feedback</c:when><c:when test='${trailDistanceError == false}'>valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                ${trailDistanceMessage}
            </div>
        </div>
        <div class="col-md-4">
            <label for="trailDifficulty" class="form-label text-light">Trail Difficulty</label>
            <select class="form-select" id="trailDifficulty" name="trailDifficulty">
                <option value="EASY" <c:if test="${trailDifficulty == 'EASY'}">selected</c:if>>Easy</option>
                <option value="MODERATE" <c:if test="${trailDifficulty == 'MODERATE'}">selected</c:if>>Moderate</option>
                <option value="HARD" <c:if test="${trailDifficulty == 'HARD'}">selected</c:if>>Hard</option>
            </select>
        </div>
        <div class="col-md-4">
            <label for="trailTime" class="form-label text-light">Trail Time (HH:mm)</label>
            <input type="text" class="form-control <c:choose><c:when test='${trailTimeError == true}'>is-invalid</c:when><c:when test='${trailTimeError == false}'>is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="trailTime" name="trailTime" value="${trailTime}">
            <div class="<c:choose><c:when test='${trailTimeError == true}'>invalid-feedback</c:when><c:when test='${trailTimeError == false}'>valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                ${trailTimeMessage}
            </div>
        </div>
        <div class="col-md-12">
            <label for="trailDescription" class="form-label text-light">Trail Description</label>
            <textarea class="form-control <c:choose><c:when test='${trailDescriptionError == true}'>is-invalid</c:when><c:when test='${trailDescriptionError == false}'>is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="trailDescription" name="trailDescription">${trailDescription}</textarea>
            <div class="<c:choose><c:when test='${trailDescriptionError == true}'>invalid-feedback</c:when><c:when test='${trailDescriptionError == false}'>valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                ${trailDescriptionMessage}
            </div>
        </div>
        <div class="col-md-4">
            <label for="categoryId" class="form-label text-light">Category:</label>
            <select class="form-select" id="categoryId" name="categoryId" required>
                <option value="">Select a category</option>
                <option value="1" <c:if test="${categoryId == 1}">selected</c:if>>Hiking</option>
                <option value="2" <c:if test="${categoryId == 2}">selected</c:if>>Biking</option>
                <option value="3" <c:if test="${categoryId == 3}">selected</c:if>>Scenic</option>
            </select>
        </div>
        <div class="col-md-6">
            <label for="trailImage" class="form-label text-light">Trail Image URL</label>
            <input type="text" class="form-control <c:choose><c:when test='${trailImageError == true}'>is-invalid</c:when><c:when test='${trailImageError == false}'>is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="trailImage" name="trailImage" value="${trailImage}">
            <div class="<c:choose><c:when test='${trailImageError == true}'>invalid-feedback</c:when><c:when test='${trailImageError == false}'>valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                ${trailImageMessage}
            </div>
        </div>
        <div class="col-12">
            <button class=" table-button" type="submit">Submit form</button>
        </div>
    </form>
</div>
</div>

</body>
</html>
