<div class="container py-4">
    <h2>Update Trail</h2>
    <a href="trails" class="btn btn-primary long-button">View All Trails</a>

    <!-- Display update message if trail is updated -->
    <c:if test="${not empty trailUpdated}">
        <div class="alert ${trailUpdated ? 'alert-success' : 'alert-danger'}" role="alert">
                ${trailUpdatedMessage}
        </div>
    </c:if>

    <!-- Form for updating the trail -->
    <form class="row g-3" method="POST" action="update-trail">
        <!-- Hidden Input for Trail ID -->
        <input type="hidden" name="trail_id" value="${id}" />

        <!-- Trail Name -->
        <div class="col-md-12">
            <label for="trailName" class="form-label">Trail Name</label>
            <input type="text" class="form-control ${not empty trailNameError ? 'is-invalid' : ''}"
                   id="trailName" name="trailName" value="${not empty trailName ? trailName : ''}">
            <c:if test="${not empty trailNameError}">
                <div class="invalid-feedback">${trailNameMessage}</div>
            </c:if>
        </div>

        <!-- Trail Distance -->
        <div class="col-md-4">
            <label for="trailDistance" class="form-label">Trail Distance (miles)</label>
            <input type="text" class="form-control ${not empty trailDistanceError ? 'is-invalid' : ''}"
                   id="trailDistance" name="trailDistance" value="${not empty trailDistance ? trailDistance : ''}">
            <c:if test="${not empty trailDistanceError}">
                <div class="invalid-feedback">${trailDistanceMessage}</div>
            </c:if>
        </div>

        <!-- Trail Difficulty -->
        <div class="col-md-4">
            <label for="trailDifficulty" class="form-label">Trail Difficulty</label>
            <select class="form-select ${not empty trailDifficultyError ? 'is-invalid' : ''}"
                    id="trailDifficulty" name="trailDifficulty" required>
                <option value="EASY" ${trailDifficulty == 'EASY' ? 'selected' : ''}>Easy</option>
                <option value="MODERATE" ${trailDifficulty == 'MODERATE' ? 'selected' : ''}>Moderate</option>
                <option value="HARD" ${trailDifficulty == 'HARD' ? 'selected' : ''}>Hard</option>
            </select>
            <c:if test="${not empty trailDifficultyError}">
                <div class="invalid-feedback">${trailDifficultyMessage}</div>
            </c:if>
        </div>

        <!-- Trail Time -->
        <div class="col-md-4">
            <label for="trailTime" class="form-label">Trail Time (HH:mm)</label>
            <input type="text" class="form-control ${not empty trailTimeError ? 'is-invalid' : ''}"
                   id="trailTime" name="trailTime" value="${trailTime != null ? trailTime : ''}">
            <c:if test="${not empty trailTimeError}">
                <div class="invalid-feedback">${trailTimeMessage}</div>
            </c:if>
        </div>


        <!-- Trail Description -->
        <div class="col-md-12">
            <label for="trailDescription" class="form-label">Trail Description</label>
            <textarea class="form-control ${not empty trailDescriptionError ? 'is-invalid' : ''}"
                      id="trailDescription" name="trailDescription">${trailDescription != null ? trailDescription : ''}</textarea>
            <c:if test="${not empty trailDescriptionError}">
                <div class="invalid-feedback">${trailDescriptionMessage}</div>
            </c:if>
        </div>


        <!-- Category -->
        <div class="col-md-4">
            <label for="category_id" class="form-label">Category</label>
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
            <label for="trailImage" class="form-label">Trail Image URL</label>
            <input type="text" class="form-control ${not empty trailImageError ? 'is-invalid' : ''}"
                   id="trailImage" name="trailImage" value="${trailImage != null ? trailImage : ''}">
            <c:if test="${not empty trailImageError}">
                <div class="invalid-feedback">${trailImageMessage}</div>
            </c:if>
        </div>


        <!-- Submit Button -->
        <div class="col-12">
            <button class="btn btn-dark" type="submit">Update Trail</button>
        </div>
    </form>
</div>
