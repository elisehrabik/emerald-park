<div class="container py-4">
    <div class="card transparent-overlay border rounded-3 p-5">

    <h2 class="text-light">Complete Maintenance Request</h2>
    <a href="maintenance" class="btn btn-primary table-button mb-5">View All Maintenance Requests</a>

    <c:if test="${not empty maintenanceUpdated}">
        <div class="alert ${maintenanceUpdated ? 'alert-success' : 'alert-danger'}" role="alert">
                ${maintenanceUpdatedMessage}
        </div>
    </c:if>

    <form class="row g-3" method="POST" action="${appURL}/edit-maintenance?maintenance_id=${maintenance.maintenance_id}">
        <input type="hidden" name="maintenance_id" value="${maintenance.maintenance_id}" />

        <!-- Completion Date -->
        <div class="col-md-6">
            <label for="completion_date" class="form-label text-light">Completion Date</label>
            <input type="date" class="form-control ${not empty completionDateError ? 'is-invalid' : ''}"
                   id="completion_date" name="completion_date" value="${completion_date != null ? completion_date : ''}">
            <c:if test="${not empty completionDateError}">
                <div class="invalid-feedback">${completionDateMessage}</div>
            </c:if>
        </div>

        <!-- Maintenance Complete -->
        <div class="col-md-6">
            <label for="maintenance_complete" class="form-label text-light">Maintenance Complete</label>
            <select class="form-select ${not empty maintenanceCompleteError ? 'is-invalid' : ''}"
                    id="maintenance_complete" name="maintenance_complete" >
                <option value="0" ${maintenance_complete == '0' ? 'selected' : ''}>No</option>
                <option value="1" ${maintenance_complete == '1' ? 'selected' : ''}>Yes</option>
            </select>
            <c:if test="${not empty maintenanceCompleteError}">
                <div class="invalid-feedback">${maintenanceCompleteMessage}</div>
            </c:if>
        </div>

        <!-- Maintenance Notes -->
        <div class="col-md-12">
            <label for="maintenance_notes" class="form-label text-light">Maintenance Notes</label>
            <textarea class="form-control ${not empty maintenanceNotesError ? 'is-invalid' : ''}"
                      id="maintenance_notes" name="maintenance_notes">${maintenance_notes != null ? maintenance_notes : ''}</textarea>
            <c:if test="${not empty maintenanceNotesError}">
                <div class="invalid-feedback">${maintenanceNotesMessage}</div>
            </c:if>
        </div>

        <!-- Submit Button -->
        <div class="col-12">
            <button class="btn btn-dark table-button" type="submit">Confirm Completion</button>
        </div>
    </form>
</div>
</div>