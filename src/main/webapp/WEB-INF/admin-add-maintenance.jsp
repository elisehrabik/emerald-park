<div class="container py-4">
    <div class="card transparent-overlay border rounded-3 p-5">

    <h2 class="text-light">Add New Maintenance Request</h2>
    <a href="maintenance" class="btn btn-primary table-button mb-5">View All Maintenance Requests</a>

    <c:if test="${not empty maintenanceAdded}">
        <div class="alert ${maintenanceAdded ? 'alert-success' : 'alert-danger'}" role="alert">
                ${maintenanceAddedMessage}
        </div>
    </c:if>

    <form class="row g-3" method="POST" action="${appURL}/admin-add-maintenance">
        <!-- Trail Selection -->
        <div class="col-md-12">
            <label for="trailId" class="form-label text-light">Trail Name</label>
            <select class="form-select ${trailIdError == true ? 'is-invalid' : (trailIdError == false ? 'is-valid' : '')}" id="trailId" name="trailId" aria-describedby="trailIdFeedback">
                <c:choose>
                    <c:when test="${trailIdError == true}">
                        <option value="" selected>Choose a Trail</option>
                    </c:when>
                    <c:when test="${trailIdError == false}">
                        <option value="">Choose a Trail</option>
                    </c:when>
                    <c:otherwise>
                        <option value="">Choose a Trail</option>
                    </c:otherwise>
                </c:choose>

                <c:forEach var="trail" items="${trails}">
                    <option value="${trail.trail_id}" ${trail.trail_id == trailId ? 'selected' : ''}>${trail.trail_name}</option>
                </c:forEach>
            </select>
            <div id="trailIdFeedback" class="${trailIdError == true ? 'invalid-feedback' : (trailIdError == false ? 'valid-feedback' : '')}">
                ${trailIdMessage}
            </div>
        </div>


        <div class="col-md-6">
            <label for="requestDate" class="form-label text-light">Request Date</label>
            <input type="date" class="form-control" id="requestDate" name="requestDate"
                   value="<fmt:formatDate value='${requestDate}' pattern='yyyy-MM-dd' />" readonly>
        </div>

<%--        <div class="col-md-6">--%>
<%--            <label for="completionDate" class="form-label">Completion Date</label>--%>
<%--            <input type="date" class="form-control" id="completionDate" name="completionDate"--%>
<%--                   value="<c:choose>--%>
<%--                  <c:when test='${not empty completionDate}'>--%>
<%--                      <fmt:formatDate value='${completionDate}' pattern='yyyy-MM-dd'/>--%>
<%--                  </c:when>--%>
<%--                  <c:otherwise></c:otherwise>--%>
<%--              </c:choose>">--%>
<%--        </div>--%>

    <div class="col-md-6">
        <label for="maintenanceType" class="form-label text-light">Maintenance Type</label>
        <select class="form-select <c:choose><c:when test='${maintenanceTypeError == true}'>is-invalid</c:when><c:when test='${maintenanceTypeError == false}'>is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="maintenanceType" name="maintenanceType" aria-describedby="maintenanceTypeFeedback">
            <option value="vegetation" <c:if test="${maintenanceType == 'vegetation'}">selected</c:if>>Vegetation</option>
            <option value="construction" <c:if test="${maintenanceType == 'construction'}">selected</c:if>>Construction</option>
            <option value="trash" <c:if test="${maintenanceType == 'trash'}">selected</c:if>>Trash</option>
        </select>
        <div id="maintenanceTypeFeedback" class="<c:choose><c:when test='${maintenanceTypeError == true}'>invalid-feedback</c:when><c:when test='${maintenanceTypeError == false}'>valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
            ${maintenanceTypeMessage}
        </div>
    </div>


    <!-- Maintenance Notes -->
        <div class="col-md-12">
            <label for="maintenanceNotes" class="form-label text-light">Maintenance Notes</label>
            <textarea class="form-control <c:choose><c:when test='${maintenanceNotesError == true}'>is-invalid</c:when><c:when test='${maintenanceNotesError == false}'>is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="maintenanceNotes" name="maintenanceNotes" aria-describedby="maintenanceNotesFeedback">${maintenanceNotes}</textarea>
            <div id="maintenanceNotesFeedback" class="<c:choose><c:when test='${maintenanceNotesError == true}'>invalid-feedback</c:when><c:when test='${maintenanceNotesError == false}'>valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                ${maintenanceNotesMessage}
            </div>
        </div>

<%--        <!-- Maintenance Complete (Yes/No) -->--%>
<%--        <div class="col-md-6">--%>
<%--            <label for="maintenanceComplete" class="form-label">Maintenance Complete</label>--%>
<%--            <select class="form-select" id="maintenanceComplete" name="maintenanceComplete">--%>
<%--                <option value="1" <c:if test="${maintenanceComplete == '1'}">selected</c:if>>Yes</option>--%>
<%--                <option value="0" <c:if test="${maintenanceComplete == '0'}">selected</c:if>>No</option>--%>
<%--            </select>--%>
<%--        </div>--%>

        <!-- Submit Button -->
        <div class="col-12">
            <button class="btn btn-dark table-button" type="submit">Submit form</button>
        </div>
    </form>
</div>
</div>

<%-- Added javascript to put in current date --%>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        let requestDateField = document.getElementById("requestDate");
        if (!requestDateField.value) {
            let today = new Date().toISOString().split('T')[0];
            requestDateField.value = today;
        }
    });
</script>

</body>
</html>
