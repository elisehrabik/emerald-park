<div class="container py-4">
    <div class="card transparent-overlay border rounded-3 p-5">

    <h2 class="text-light">Update Trail</h2>
    <a href="trails" class="btn btn-primary table-button mb-5">View All Trails</a>

    <!-- Display update message if trail is updated -->
    <c:if test="${not empty trailUpdated}">
        <div class="alert ${trailUpdated ? 'alert-success' : 'alert-danger'}" role="alert">
                ${trailUpdatedMessage}
        </div>
    </c:if>

    <!-- Form for updating the trail -->
    <form class="row g-3" method="POST" action="${appURL}/update-trail">
        <!-- Hidden Input for Trail ID -->
        <input type="hidden" name="trail_id" value="${id}" />

        <!-- Trail Name -->
        <div class="col-md-12">
            <label for="trailName" class="form-label text-light">Trail Name</label>
            <input type="text" class="form-control ${not empty trailNameError ? 'is-invalid' : ''}"
                   id="trailName" name="trailName" value="${not empty trailName ? trailName : ''}">
            <c:if test="${not empty trailNameError}">
                <div class="invalid-feedback">${trailNameMessage}</div>
            </c:if>
        </div>

        <!-- Trail Distance -->
        <div class="col-md-4">
            <label for="trailDistance" class="form-label text-light">Trail Distance (miles)</label>
            <input type="text" class="form-control ${not empty trailDistanceError ? 'is-invalid' : ''}"
                   id="trailDistance" name="trailDistance" value="${not empty trailDistance ? trailDistance : ''}">
            <c:if test="${not empty trailDistanceError}">
                <div class="invalid-feedback">${trailDistanceMessage}</div>
            </c:if>
        </div>

        <!-- Trail Difficulty -->
        <div class="col-md-4">
            <label for="trailDifficulty" class="form-label text-light">Trail Difficulty</label>
            <select class="form-select <c:choose>
                <c:when test='${trailDifficultyError == true}'>is-invalid</c:when>
                <c:when test='${trailDifficultyError == false}'>is-valid</c:when>
                <c:otherwise></c:otherwise>
                </c:choose>" id="trailDifficulty" name="trailDifficulty" required>
                <option value="EASY" ${trailDifficulty == 'EASY' ? 'selected' : ''}>Easy</option>
                <option value="MODERATE" ${trailDifficulty == 'MODERATE' ? 'selected' : ''}>Moderate</option>
                <option value="DIFFICULT" ${trailDifficulty == 'DIFFICULT' ? 'selected' : ''}>Difficult</option>
                <option value="EXTREME" ${trailDifficulty == 'EXTREME' ? 'selected' : ''}>Extreme</option>
            </select>
        </div>

        <!-- Trail Time -->
        <div class="col-md-4">
            <label for="trailTime" class="form-label text-light">Trail Time (HH:mm)</label>
            <input type="text" class="form-control ${not empty trailTimeError ? 'is-invalid' : ''}"
                   id="trailTime" name="trailTime" value="${trailTime != null ? trailTime : ''}">
            <c:if test="${not empty trailTimeError}">
                <div class="invalid-feedback">${trailTimeMessage}</div>
            </c:if>
        </div>


        <!-- Trail Description -->
        <div class="col-md-12">
            <label for="trailDescription" class="form-label text-light">Trail Description</label>
            <textarea class="form-control ${not empty trailDescriptionError ? 'is-invalid' : ''}"
                      id="trailDescription" name="trailDescription">${trailDescription != null ? trailDescription : ''}</textarea>
            <c:if test="${not empty trailDescriptionError}">
                <div class="invalid-feedback">${trailDescriptionMessage}</div>
            </c:if>
        </div>


        <!-- Category -->
        <div class="col-md-4">
            <label for="category_id" class="form-label text-light">Category</label>
            <select class="form-select ${not empty categoryIdError ? 'is-invalid' : ''}"
                    id="category_id" name="category_id" required>
                <option value="">Select a category</option>
                <option value="1" ${category_id == '1' ? 'selected' : ''}>Hiking</option>
                <option value="2" ${category_id == '2' ? 'selected' : ''}>Biking</option>
                <option value="3" ${category_id == '3' ? 'selected' : ''}>Scenic</option>
            </select>
            <c:if test="${not empty categoryIdError}">
                <div class="invalid-feedback">${categoryIdMessage}</div>
            </c:if>
        </div>

        <!-- Trail Image URL -->
        <div class="col-md-6">
            <label for="trailImage" class="form-label text-light">Trail Image URL</label>
            <input type="text" class="form-control ${not empty trailImageError ? 'is-invalid' : ''}"
                   id="trailImage" name="trailImage" value="${trailImage != null ? trailImage : ''}">
            <c:if test="${not empty trailImageError}">
                <div class="invalid-feedback">${trailImageMessage}</div>
            </c:if>
        </div>


        <!-- Submit Button -->
        <div class="col-12">
            <button class="btn btn-dark table-button" type="submit">Update Trail</button>
        </div>
    </form>
</div>
</div>