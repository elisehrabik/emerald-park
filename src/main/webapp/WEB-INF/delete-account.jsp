<main>
    <%@include file="/WEB-INF/edit-profile-header.jspf"%>

    <section class="pt-0">
        <div class="container">
            <div class="row">
                <%@include file="/WEB-INF/left-sidebar.jspf"%>

                <!-- Main content START -->
                <div class="col-xl-9">
                    <!-- Title and select START -->
                    <div class="card border bg-transparent rounded-3 mb-0">
                        <!-- Card header -->
                        <div class="card-header transparent-overlay2 border-bottom">
                            <h3 class="card-header-title mb-0">Delete Account</h3>
                        </div>
                        <!-- Card body -->
                        <div class="card-body">
                            <h6 class="text-light">If you delete your account, you will lose your all data.</h6>
                            <form id="deleteAccountForm" method="POST" action="${appURL}/delete-account">
                                <!-- Email id -->
                                <div class="col-md-6 my-4">
                                    <label class="form-label text-light" for="email">Enter your email to confirm account deletion</label>
                                    <input class="form-control <c:if test="${not empty results.emailError}">is-invalid</c:if>" type="text" id="email" name="email" value="${email}">
                                    <c:if test="${not empty results.emailError }"><div class="invalid-feedback">${results.emailError}</div></c:if>
                                </div>

                                <button type="button" class="btn btn-primary mb-0 table-button delete-button" data-bs-toggle="modal" data-bs-target="#deleteAccountModal">
                                    Delete my account
                                </button>


                                <!-- Delete Account Confirmation Modal -->
                                <div class="modal fade" id="deleteAccountModal" tabindex="-1" aria-labelledby="deleteAccountModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="deleteAccountModalLabel">Confirm Account Deletion</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                Are you sure you want to delete your account? This action cannot be undone.
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                                <button type="button" class="btn btn-danger" id="confirmDelete">Yes, Delete My Account</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                            </form>
                        </div>
                    </div>
                    <!-- Title and select END -->
                </div>
                <!-- Main content END -->
            </div><!-- Row END -->
        </div>
    </section>
</main>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const confirmDeleteBtn = document.getElementById("confirmDelete");
        const deleteAccountForm = document.getElementById("deleteAccountForm");

        if (confirmDeleteBtn && deleteAccountForm) {
            confirmDeleteBtn.addEventListener("click", function (e) {
                e.preventDefault();
                deleteAccountForm.submit();
            });
        }
    });
</script>
