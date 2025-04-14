<div class="container col-xl-10 col-xxl-8 px-4 py-5 fade-up">
    <div class="row align-items-center g-lg-5 py-5">
        <!-- Text/Info Column -->
        <div class="col-lg-7 text-start text-light px-3 pb-3 px-md-4">
            <h1 class="display-4 fw-bold lh-1 mb-4 text-start">Support Our Cause</h1>
            <p class="col-lg-10 fs-4">Donate to our park to receive extra information and be invited to exclusive events.</p>
            <ul class="list-unstyled fs-5 mt-4">
                <li>Your donation contributes to the maintenance of the trails and funds special events to teach about the environment</li>
            </ul>
        </div>

        <!-- Contact Form Column -->
        <div class="col-md-10 mx-auto col-lg-5">
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success mb-3">${successMessage}</div>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger mb-3">${errorMessage}</div>
            </c:if>
            <form action="${pageContext.request.contextPath}/donate" method="post" class="p-4 p-md-5 border rounded-3 bg-body-tertiary">
                <label class="form-label">Donation Amount</label>

                <div class="form-check mb-2">
                    <input class="form-check-input" type="radio" name="donationOption" id="amount5" value="5">
                    <label class="form-check-label" for="amount5">$5</label>
                </div>
                <div class="form-check mb-2">
                    <input class="form-check-input" type="radio" name="donationOption" id="amount10" value="10">
                    <label class="form-check-label" for="amount10">$10</label>
                </div>
                <div class="form-check mb-2">
                    <input class="form-check-input" type="radio" name="donationOption" id="amount20" value="20">
                    <label class="form-check-label" for="amount20">$20</label>
                </div>
                <div class="form-check mb-3">
                    <input class="form-check-input" type="radio" name="donationOption" id="customOption" value="custom">
                    <label class="form-check-label" for="customOption">Custom Amount</label>
                </div>

                <div class="form-floating mb-3" id="customAmountWrapper" style="display: none;">
                    <input type="number" step="0.01" min="0" class="form-control <c:if test='${not empty amountError}'>is-invalid</c:if>"
                           id="customAmount" name="customAmount" value="${param.customAmount}" placeholder="Enter custom amount">
                    <label for="customAmount">Custom Amount</label>
                    <c:if test="${not empty amountError}">
                        <div class="invalid-feedback">${amountError}</div>
                    </c:if>
                </div>

                <button type="submit" class="btn btn-dark btn-block btn-lg">Check out</button>
            </form>



        </div>
    </div>
</div>

<script src="${appURL}/scripts/fade-up-animation.js"></script>

<%--This javascript opens the Custom Amount area if the last button is checked--%>
<script>
    const customRadio = document.getElementById('customOption');
    const customWrapper = document.getElementById('customAmountWrapper');

    document.querySelectorAll('input[name="donationOption"]').forEach(radio => {
        radio.addEventListener('change', () => {
            if (customRadio.checked) {
                customWrapper.style.display = 'block';
            } else {
                customWrapper.style.display = 'none';
            }
        });
    });
</script>
