<div class="container col-xl-10 col-xxl-8 px-4 py-5 fade-up">
    <div class="row align-items-center g-lg-5 py-5">
        <!-- Text/Info Column -->
        <div class="col-lg-5 text-start text-light px-3 pb-3 px-md-4">
            <h1 class="display-4 fw-bold lh-1 mb-3 text-start">Contact Us</h1>
            <p class="col-lg-10 fs-4">We’d love to hear from you! Reach out with questions, feedback, or suggestions.</p>
            <ul class="list-unstyled fs-5 mt-4">
                <li><strong>Email:</strong> <a class="text-decoration-none text-light" href="mailto:contact@emeraldpark.com">contact@emeraldpark.com</a></li>
                <li><strong>Phone:</strong> (555) 123-4567</li>
                <li><strong>Address:</strong> 123 Trailhead Way, Cedar Hills, IA</li>
            </ul>
        </div>

        <!-- Contact Form Column -->
        <div class="col-md-10 mx-auto col-lg-7">
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success mb-3">${successMessage}</div>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger mb-3">${errorMessage}</div>
            </c:if>
            <form action="${appURL}/contact" method="post" class="p-4 p-md-5 border rounded-3 bg-body-tertiary">
                <div class="form-floating mb-3">
                    <input type="email" class="form-control <c:if test='${not empty emailError}'>is-invalid</c:if>" id="email" name="toEmailAddress" value="${toEmailAddress}" placeholder="name@example.com">
                    <label for="email">Your Email</label>
                    <c:if test="${not empty emailError}">
                        <div class="invalid-feedback">${emailError}</div>
                    </c:if>
                </div>

                <div class="form-floating mb-3">
                    <input type="text" class="form-control <c:if test='${not empty subjectError}'>is-invalid</c:if>" id="subject" name="subject" value="${subject}" placeholder="Subject">
                    <label for="subject">Subject</label>
                    <c:if test="${not empty subjectError}">
                        <div class="invalid-feedback">${subjectError}</div>
                    </c:if>
                </div>

                <div class="form-floating mb-3">
                    <textarea class="form-control <c:if test='${not empty messageBodyError}'>is-invalid</c:if>" placeholder="Leave your message here" id="message" name="bodyHTML" style="height: 150px">${bodyHTML}</textarea>
                    <label for="message">Message</label>
                    <c:if test="${not empty messageBodyError}">
                        <div class="invalid-feedback">${messageBodyError}</div>
                    </c:if>
                </div>

                <button type="submit" class="w-100 btn btn-lg button-dark">Send Message</button>

                <c:if test="${not empty messageSuccess}">
                    <div class="text-success mt-3">${messageSuccess}</div>
                </c:if>
            </form>
        </div>
    </div>
</div>

<script src="${appURL}/scripts/fade-up-animation.js"></script>
