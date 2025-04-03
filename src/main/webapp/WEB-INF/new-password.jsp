<main>
    <section class="p-0 d-flex align-items-center position-relative overflow-hidden" style="z-index: 1">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12 col-lg-8 m-auto">
                    <div class="row my-5">
                        <div class="col-sm-10 col-xl-8 m-auto">
                            <!-- Title -->
                            <h1 class="fs-2 text-light text-center">New Password</h1>
                            <p class="lead mb-4 text-light text-center">Please enter your new password.</p>

                            <c:if test="${not empty newPasswordFail}">
                                <div class="alert alert-danger mb-2" role="alert">
                                        ${newPasswordFail}
                                </div>
                            </c:if>

                            <!-- Form START -->
                            <form method="post" action="${appURL}/new-password">
                                <!-- Password -->
                                <div class="mb-4">
                                    <label for="inputPassword1" class="form-label text-light">Password *</label>
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-text bg-light rounded-start border-0 text-secondary px-3">
                                            <i class="bi bi-lock-fill"></i>
                                        </span>
                                        <input type="password" class="form-control <c:if test="${not empty password1Error}">is-invalid</c:if> border-0 bg-light rounded-end ps-1"
                                               placeholder="*********" id="inputPassword1" name="password1" value="${password1}">
                                        <c:if test="${not empty password1Error}">
                                            <div class="invalid-feedback">${password1Error}</div>
                                        </c:if>
                                    </div>
                                </div>

                                <!-- Confirm Password -->
                                <div class="mb-4">
                                    <label for="inputPassword2" class="form-label text-light">Confirm Password *</label>
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-text bg-light rounded-start border-0 text-secondary px-3">
                                            <i class="bi bi-lock-fill"></i>
                                        </span>
                                        <input type="password" class="form-control <c:if test="${not empty password2Error}">is-invalid</c:if> border-0 bg-light rounded-end ps-1"
                                               placeholder="*********" id="inputPassword2" name="password2" value="${password2}">
                                        <c:if test="${not empty password2Error}">
                                            <div class="invalid-feedback">${password2Error}</div>
                                        </c:if>
                                    </div>
                                </div>

                                <!-- Hidden field -->
                                <input type="hidden" name="token" value="${token}">

                                <!-- Button -->
                                <div class="align-items-center mt-0">
                                    <div class="d-grid justify-content-center">
                                        <button class="btn btn-primary mb-0 table-button" type="submit">Submit</button>
                                    </div>
                                </div>
                            </form>
                            <!-- Form END -->

                            <!-- Sign in link -->
                            <div class="mt-4 text-center">
                                <span><a href="${appURL}/login" style="color:white;">Login</a></span>
                            </div>
                            <br>
                            <br>
                            <br>
                            <br>
                            <br>
                            <br>
                        </div>
                    </div> <!-- Row END -->
                </div>
            </div> <!-- Row END -->
        </div>
    </section>
</main>
