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
                    <div class="card transparent-overlay border rounded-3 ">
                        <!-- Card header -->
                        <div class="card-header transparent-overlay2 border-bottom">
                            <h3 class="card-header-title mb-0">Edit Profile</h3>
                        </div>
                        <!-- Card body START -->
                        <div class="card-body">
                            <!-- Form -->
                            <form class="row g-4" action="${appURL}/edit-profile" method="POST">

                                <!-- First name -->
                                <div class="col-md-6">
                                    <label class="form-label text-light" for="firstName">First Name</label>
                                    <input class="<c:if test="${not empty firstNameError}">is-invalid</c:if> form-control" type="text" id="firstName" name="firstName" value="${fn:escapeXml(sessionScope.activeUser.firstName)}">
                                    <c:if test="${not empty firstNameError}"><div class="invalid-feedback">${firstNameError}</div></c:if>
                                </div>

                                <!-- Last name -->
                                <div class="col-md-6">
                                    <label class="form-label text-light" for="lastName">Last Name</label>
                                    <input type="text" class="<c:if test="${not empty lastNameError}">is-invalid</c:if> form-control" id="lastName" name="lastName" value="${sessionScope.activeUser.lastName}">
                                    <c:if test="${not empty lastNameError}"><div class="invalid-feedback">${lastNameError}</div></c:if>
                                </div>

                                <!-- Email id -->
                                <div class="col-md-6">
                                    <label class="form-label text-light" for="email">Email</label>
                                    <input class="<c:if test="${not empty emailError}">is-invalid</c:if> form-control" type="text" id="email" name="email" value="${not empty email ? email : sessionScope.activeUser.email}">
                                    <c:if test="${not empty emailError }"><div class="invalid-feedback">${emailError}</div></c:if>
                                </div>

                                <!-- Phone number -->
                                <div class="col-md-6">
                                    <label class="form-label text-light" for="phone">Phone number</label>
                                    <input type="text" class="<c:if test="${not empty phoneError}">is-invalid</c:if> form-control" id="phone" name="phone" value="${not empty phone ? phone : sessionScope.activeUser.phone}">
                                    <c:if test="${not empty phoneError }"><div class="invalid-feedback">${phoneError}</div></c:if>
                                </div>

                                <!-- Select option -->
                                <div class="col-md-6">
                                    <!-- Language Preference -->
                                    <label class="form-label text-light" for="language">Language</label>
                                    <select class="<c:if test="${not empty languageError}">is-invalid</c:if> form-select js-choice z-index-9" aria-label=".form-select-sm" id="language" name="language">
                                        <option value="en-US" ${sessionScope.activeUser.language == 'en-US' ? 'selected' : ''}>English</option>
                                        <option value="es-MX" ${sessionScope.activeUser.language == 'es-MX' ? 'selected' : ''}>Spanish</option>
                                        <option value="fr-FR" <c:if test="${sessionScope.activeUser.language == 'fr-FR'}">selected</c:if> >French</option>
                                    </select>
                                    <c:if test="${not empty languageError }"><div class="invalid-feedback">${languageError}</div></c:if>
                                </div>

                                <!-- Select option for Time Zone -->
                                <div class="col-md-6">
                                    <!-- Time Zone -->
                                    <label class="form-label text-light" for="timezone">Time Zone</label>
                                    <select class="<c:if test="${not empty timezoneError}">is-invalid</c:if> form-select js-choice z-index-9" aria-label=".form-select-sm" id="timezone" name="timezone">
                                        <option value="America/Chicago" ${sessionScope.activeUser.timezone == 'America/Chicago' ? 'selected' : ''}>Central Time (CT)</option>
                                        <option value="America/New_York" ${sessionScope.activeUser.timezone == 'America/New_York' ? 'selected' : ''}>Eastern Time (ET)</option>
                                        <option value="America/Denver" ${sessionScope.activeUser.timezone == 'America/Denver' ? 'selected' : ''}>Mountain Time (MT)</option>
                                        <option value="America/Los_Angeles" ${sessionScope.activeUser.timezone == 'America/Los_Angeles' ? 'selected' : ''}>Pacific Time (PT)</option>
                                        <option value="America/Anchorage" ${sessionScope.activeUser.timezone == 'America/Anchorage' ? 'selected' : ''}>Alaska Time (AKT)</option>
                                        <option value="Pacific/Honolulu" ${sessionScope.activeUser.timezone == 'Pacific/Honolulu' ? 'selected' : ''}>Hawaii-Aleutian Time (HST)</option>
                                    </select>
                                    <c:if test="${not empty timezoneError }">
                                        <div class="invalid-feedback">${timezoneError}</div>
                                    </c:if>
                                </div>


                                <!-- pronouns -->
                                <div class="col-md-6">
                                    <label class="form-label text-light" for="pronouns">Pronouns</label>
                                    <select class="<c:if test="${not empty pronouns}">is-invalid</c:if> form-select js-choice z-index-9" aria-label=".form-select-sm" id="pronouns" name="pronouns">
                                        <option value="--" ${sessionScope.activeUser.pronouns == '--' ? 'selected' : ''}>--</option>
                                        <option value="she/her" ${sessionScope.activeUser.pronouns == 'she/her' ? 'selected' : ''}>She/Her</option>
                                        <option value="he/him" ${sessionScope.activeUser.pronouns == 'he/him' ? 'selected' : ''}>He/Him</option>
                                        <option value="they/them" ${sessionScope.activeUser.pronouns == 'they/them' ? 'selected' : ''}>They/Them</option>
                                        <option value="she/they" ${sessionScope.activeUser.pronouns == 'she/they' ? 'selected' : ''}>She/They</option>
                                        <option value="he/they" ${sessionScope.activeUser.pronouns == 'he/they' ? 'selected' : ''}>He/They</option>
                                        <option value="she/he/they" <c:if test="${sessionScope.activeUser.pronouns == 'she/he/they'}">selected</c:if> >She/He/They</option>
                                    </select>
                                    <c:if test="${not empty pronounsError }"><div class="invalid-feedback">${pronounsError}</div></c:if>
                                </div>

                                <!-- birthday -->
                                <div class="col-md-6">
                                    <label class="form-label text-light" for="birthday">Date of Birth</label>
                                    <input type="date" class="<c:if test='${not empty birthdayError}'>is-invalid</c:if> form-control"
                                           id="birthday"
                                           name="birthday"
                                           value="${sessionScope.activeUser.birthday != null ? sessionScope.activeUser.birthday.toString() : ''}">

                                    <c:if test="${not empty birthdayError}">
                                        <div class="invalid-feedback">${birthdayError}</div>
                                    </c:if>
                                </div>

                                <!-- avatar -->
                                <div class="col-md-6">
                                    <label class="form-label text-light" for="avatar">Avatar</label>
                                    <select class="<c:if test="${not empty avatar}">is-invalid</c:if> form-select js-choice z-index-9" aria-label=".form-select-sm" id="avatar" name="avatar">
                                        <option value="tree" ${sessionScope.activeUser.avatar == 'tree' ? 'selected' : ''}>Tree</option>
                                        <option value="person-hiking" ${sessionScope.activeUser.avatar == 'person-hiking' ? 'selected' : ''}>Hiker</option>
                                        <option value="bicycle" ${sessionScope.activeUser.avatar == 'bicycle' ? 'selected' : ''}>Bike</option>
                                        <option value="horse" ${sessionScope.activeUser.avatar == 'horse' ? 'selected' : ''}>Horse</option>
                                        <option value="dog" ${sessionScope.activeUser.avatar == 'dog' ? 'selected' : ''}>Dog</option>
                                        <option value="cat" ${sessionScope.activeUser.avatar == 'cat' ? 'selected' : ''}>Cat</option>
                                        <option value="fish" ${sessionScope.activeUser.avatar == 'fish' ? 'selected' : ''}>Fish</option>
                                        <option value="mountain-sun" ${sessionScope.activeUser.avatar == 'mountain-sun' ? 'selected' : ''}>Mountain</option>
                                        <option value="leaf" <c:if test="${sessionScope.activeUser.avatar == 'leaf'}">selected</c:if> >Leaf</option>
                                    </select>
                                    <c:if test="${not empty avatarError }"><div class="invalid-feedback">${avatarError}</div></c:if>
                                </div>

                                <!-- Save button -->
                                <div class="d-sm-flex justify-content-center">
                                    <button type="submit" class="btn btn-primary mb-0 table-button">Save changes</button>
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