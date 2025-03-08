<main>
    <%@ include file="/WEB-INF/edit-profile-header.jspf" %>

    <!-- Page content START -->
    <section class="pt-0">
        <div class="container">
            <div class="row">
                <%@ include file="/WEB-INF/left-sidebar.jspf" %>

                <!-- Main content START -->
                <div class="col-lg-9">
                    <!-- Edit profile START -->
                    <div class="card bg-transparent border rounded-3">
                        <!-- Card header -->
                        <div class="card-header bg-light border-bottom">
                            <h3 class="card-header-title mb-0">Edit Profile</h3>
                        </div>
                        <!-- Card body START -->
                        <div class="card-body">
                            <!-- Form -->
                            <form class="row g-4" action="${appURL}/edit-profile" method="POST">

                                <!-- First name -->
                                <div class="col-md-6">
                                    <label class="form-label" for="firstName">First Name</label>
                                    <input class="<c:if test="${not empty firstNameError}">is-invalid</c:if> form-control" type="text" id="firstName" name="firstName" value="${sessionScope.activeUser.firstName}">
                                    <c:if test="${not empty firstNameError}"><div class="invalid-feedback">${firstNameError}</div></c:if>
                                </div>

                                <!-- Last name -->
                                <div class="col-md-6">
                                    <label class="form-label" for="lastName">Last Name</label>
                                    <input type="text" class="<c:if test="${not empty lastNameError}">is-invalid</c:if> form-control" id="lastName" name="lastName" value="${sessionScope.activeUser.lastName}">
                                    <c:if test="${not empty lastNameError}"><div class="invalid-feedback">${lastNameError}</div></c:if>
                                </div>

                                <!-- Email id -->
                                <div class="col-md-6">
                                    <label class="form-label" for="email">Email</label>
                                    <input class="<c:if test="${not empty emailError}">is-invalid</c:if> form-control" type="text" id="email" name="email" value="${not empty email ? email : sessionScope.activeUser.email}">
                                    <c:if test="${not empty emailError }"><div class="invalid-feedback">${emailError}</div></c:if>
                                </div>

                                <!-- Phone number -->
                                <div class="col-md-6">
                                    <label class="form-label" for="phone">Phone number</label>
                                    <input type="text" class="<c:if test="${not empty phoneError}">is-invalid</c:if> form-control" id="phone" name="phone" value="${not empty phone ? phone : sessionScope.activeUser.phone}">
                                    <c:if test="${not empty phoneError }"><div class="invalid-feedback">${phoneError}</div></c:if>
                                </div>

                                <!-- Select option -->
                                <div class="col-md-6">
                                    <!-- Language Preference -->
                                    <label class="form-label" for="language">Language</label>
                                    <select class="<c:if test="${not empty languageError}">is-invalid</c:if> form-select js-choice z-index-9 bg-transparent" aria-label=".form-select-sm" id="language" name="language">
                                        <option value="en-US" ${sessionScope.activeUser.language == 'en-US' ? 'selected' : ''}>English</option>
                                        <option value="es-MX" ${sessionScope.activeUser.language == 'es-MX' ? 'selected' : ''}>Spanish</option>
                                        <option value="fr-FR" <c:if test="${sessionScope.activeUser.language == 'fr-FR'}">selected</c:if> >French</option>
                                    </select>
                                    <c:if test="${not empty languageError }"><div class="invalid-feedback">${languageError}</div></c:if>
                                </div>

                                <!-- Save button -->
                                <div class="d-sm-flex justify-content-end">
                                    <button type="submit" class="btn btn-primary mb-0">Save changes</button>
                                </div>
                            </form>
                        </div>
                        <!-- Card body END -->
                    </div>
                    <!-- Edit profile END -->
                </div>
                <!-- Main content END -->
            </div><!-- Row END -->
        </div>
    </section>
</main>