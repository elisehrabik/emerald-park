<main>
    <section class="p-0 d-flex align-items-center position-relative overflow-hidden">

        <div class="container-fluid">
            <div class="row">

                <div class="col-12 col-lg-8 m-auto">
                    <div class="row my-5">
                        <div class="col-sm-10 col-xl-8 m-auto">
                            <!-- Title -->
                            <h1 class="fs-2">Reset Password</h1>
                            <p class="lead mb-4">Enter your email address to reset your password.</p>

                            <c:if test="${not empty passwordResetMsg}">
                                <div class="alert alert-warning mb-2" role="alert">
                                        ${passwordResetMsg}
                                </div>
                            </c:if>

                            <!-- Form START -->
                            <form method="post" action="${appURL}/reset-password">
                                <!-- Email -->
                                <div class="mb-4">
                                    <label for="inputEmail" class="form-label">Email address *</label>
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-text bg-light rounded-start border-0 text-secondary px-3"><i class="bi bi-envelope-fill"></i></span>
                                        <input type="text" class="form-control border-0 bg-light rounded-end ps-1" placeholder="E-mail" id="inputEmail" name="email" value="${email}">
                                    </div>
                                </div>

                                <!-- Button -->
                                <div class="align-items-center mt-0">
                                    <div class="d-grid">
                                        <button class="btn btn-primary mb-0" type="submit">Submit</button>
                                    </div>
                                </div>
                            </form>
                            <!-- Form END -->

                            <!-- Sign in link -->
                            <div class="mt-4 text-center">
                                <span><a href="${appURL}/login">Login</a></span>
                            </div>
                        </div>
                    </div> <!-- Row END -->
                </div>
            </div> <!-- Row END -->
        </div>
    </section>
</main>