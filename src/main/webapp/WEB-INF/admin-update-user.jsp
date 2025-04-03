<div class="container py-4">
    <div class="card transparent-overlay border rounded-3 p-5">

        <h2 class="text-light">Update User</h2>
        <a href="users" class="btn btn-primary table-button mb-5">View All Users</a>

        <c:if test="${not empty userUpdated}">
            <div class="alert ${userUpdated ? 'alert-success' : 'alert-danger'}" role="alert">
                    ${userUpdatedMessage}
            </div>
        </c:if>

        <form class="row g-3" method="POST" action="${appURL}/admin-update-user">
            <input type="hidden" name="user_id" value="${userId}" />

            <!-- User Status -->
            <div class="col-md-6">
                <label for="status" class="form-label text-light">Status</label>
                <select class="form-select ${not empty statusError ? 'is-invalid' : ''}"
                        id="status" name="status" required>
                    <option value="active" ${status == 'active' ? 'selected' : ''}>Active</option>
                    <option value="inactive" ${status == 'inactive' ? 'selected' : ''}>Inactive</option>
                </select>
                <c:if test="${not empty statusError}">
                    <div class="invalid-feedback">${statusMessage}</div>
                </c:if>
            </div>

            <!-- User Privileges -->
            <div class="col-md-6">
                <label for="privileges" class="form-label text-light">Privileges</label>
                <select class="form-select ${not empty privilegesError ? 'is-invalid' : ''}"
                        id="privileges" name="privileges" required>
                    <option value="user" ${privileges == 'user' ? 'selected' : ''}>User</option>
                    <option value="admin" ${privileges == 'admin' ? 'selected' : ''}>Admin</option>
                    <option value="admin" ${privileges == 'subscriber' ? 'selected' : ''}>Subscriber</option>
                </select>
                <c:if test="${not empty privilegesError}">
                    <div class="invalid-feedback">${privilegesMessage}</div>
                </c:if>
            </div>

            <!-- Submit Button -->
            <div class="col-12">
                <button class="btn btn-dark table-button" type="submit">Update User</button>
            </div>
        </form>
    </div>
</div>
