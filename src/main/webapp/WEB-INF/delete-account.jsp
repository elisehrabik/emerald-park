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
                        <div class="card-body transparent-overlay">
                            <h6 class="text-light">If you delete your account, you will lose your all data.</h6>

                                <input type="hidden" id="correctEmail" value="${sessionScope.activeUser.email}" />

                                <!-- Email id -->
                                <div class="col-md-6 my-4">
                                    <label class="form-label text-light" for="email">Enter your email to confirm account deletion</label>
                                    <input class="form-control" type="text" id="email" name="email" value="${email}">
                                    <div class="invalid-feedback" id="emailError" style="display: none;"></div>
                                </div>

                                <button type="button" class="btn btn-primary mb-0 table-button delete-button" id="openDeleteModal">
                                    Delete my account
                                </button>

                                <!-- Delete Account Confirmation Modal -->
                                <div class="modal fade" id="deleteAccountModal" tabindex="-1" aria-labelledby="deleteAccountModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <form method="POST" action="${appURL}/delete-account" id="deleteAccountForm">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="deleteAccountModalLabel">Confirm Account Deletion</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    Are you sure you want to delete your account? This action cannot be undone.
                                                    <input type="hidden" name="email" id="modalEmailInput">
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                                    <button type="submit" class="btn btn-danger">Yes, Delete My Account</button>
                                                </div>
                                            </div>
                                        </form>
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
        const openDeleteModalBtn = document.getElementById("openDeleteModal");
        const modalEmailInput = document.getElementById("modalEmailInput");

        openDeleteModalBtn.addEventListener("click", function () {
            const emailInput = document.getElementById("email");
            const enteredEmail = emailInput.value.trim();
            const correctEmail = document.getElementById("correctEmail").value;
            const emailError = document.getElementById("emailError");

            emailInput.classList.remove("is-invalid");
            emailError.style.display = "none";
            emailError.textContent = "";

            const emailRegex = /^[^@\s]+@[^@\s]+\.[^@\s]+$/;
            const isValid = emailRegex.test(enteredEmail);

            if (!isValid) {
                emailInput.classList.add("is-invalid");
                emailError.textContent = "Please enter a valid email address.";
                emailError.style.display = "block";
                return;
            }

            if (enteredEmail !== correctEmail) {
                emailInput.classList.add("is-invalid");
                emailError.textContent = "The email entered does not match your account.";
                emailError.style.display = "block";
                return;
            }

            // ✅ Set the email value just before showing the modal
            modalEmailInput.value = enteredEmail;

            const modal = new bootstrap.Modal(document.getElementById("deleteAccountModal"));
            modal.show();
        });
    });
</script>
