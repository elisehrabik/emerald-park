<main>
    <!-- Page content START -->
    <section class="pt-0 mt-5">
        <div class="container">
            <div class="row">
                <!-- Main content START -->
                <div class="">
                    <!-- Checkout card START -->
                    <div class="card transparent-overlay border rounded-3">
                        <!-- Card header -->
                        <div class="card-header transparent-overlay2 border-bottom">
                            <h3 class="card-header-title mb-0">Checkout</h3>
                        </div>

                        <!-- Card body START -->
                        <div class="card-body">
                            <form class="row g-4" action="${appURL}/checkout" method="POST">
                                <!-- Billing Info -->
                                <h5 class="text-light">Billing Address</h5>

                                <div class="col-md-6">
                                    <label class="form-label text-light" for="firstName">First Name</label>
                                    <input class="form-control <c:if test='${not empty firstNameError}'>is-invalid</c:if>" type="text" id="firstName" name="firstName" value="${firstName}">
                                    <c:if test="${not empty firstNameError}">
                                        <div class="invalid-feedback">${firstNameError}</div>
                                    </c:if>
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label text-light" for="lastName">Last Name</label>
                                    <input class="form-control <c:if test='${not empty lastNameError}'>is-invalid</c:if>" type="text" id="lastName" name="lastName" value="${lastName}">
                                    <c:if test="${not empty lastNameError}">
                                        <div class="invalid-feedback">${lastNameError}</div>
                                    </c:if>
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label text-light" for="email">Email</label>
                                    <input class="form-control" type="email" id="email" name="email" value="${activeUser.email}">
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label text-light" for="address">Address</label>
                                    <input class="form-control" type="text" id="address" name="address" value="${address}">
                                </div>

                                <div class="col-md-4">
                                    <label class="form-label text-light" for="city">City</label>
                                    <input class="form-control" type="text" id="city" name="city" value="${city}">
                                </div>

                                <div class="col-md-4">
                                    <label class="form-label text-light" for="state">State</label>
                                    <select class="form-select" id="state" name="state">
                                        <option value="">Choose...</option>
                                        <option value="IA" selected>Iowa</option>
                                    </select>
                                </div>

                                <div class="col-md-4">
                                    <label class="form-label text-light" for="zip">Zip</label>
                                    <input class="form-control" type="text" id="zip" name="zip" value="${zip}">
                                </div>

                                <!-- Payment Info -->
                                <h5 class="text-light mt-4">Payment</h5>

                                <div class="col-md-6">
                                    <label class="form-label text-light" for="cc-name">Name on card</label>
                                    <input class="form-control" type="text" id="cc-name" name="cc-name">
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label text-light" for="cc-number">Credit card number</label>
                                    <input class="form-control <c:if test='${not empty ccNumberError}'>is-invalid</c:if>" type="text" id="cc-number" name="cc-number">
                                    <c:if test="${not empty ccNumberError}">
                                        <div class="invalid-feedback">${ccNumberError}</div>
                                    </c:if>
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label text-light" for="cc-expiration">Expiration</label>
                                    <input class="form-control <c:if test='${not empty ccExpirationError}'>is-invalid</c:if>" type="text" id="cc-expiration" name="cc-expiration">
                                    <c:if test="${not empty ccExpirationError}">
                                        <div class="invalid-feedback">${ccExpirationError}</div>
                                    </c:if>
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label text-light" for="cc-cvv">CVV</label>
                                    <input class="form-control <c:if test='${not empty ccCvvError}'>is-invalid</c:if>" type="text" id="cc-cvv" name="cc-cvv">
                                    <c:if test="${not empty ccCvvError}">
                                        <div class="invalid-feedback">${ccCvvError}</div>
                                    </c:if>
                                </div>

                                <!-- Summary -->
                                <div class="col-12 mt-4">
                                    <div class="bg-light p-3 rounded">
                                        <h5 class="d-flex justify-content-between">
                                            <span>Donation</span>
                                            <span>$${donationAmount}</span>
                                        </h5>
                                        <hr>
                                        <h5 class="d-flex justify-content-between">
                                            <strong>Total</strong>
                                            <strong>$${donationAmount}</strong>
                                        </h5>
                                    </div>
                                </div>

                                <!-- Submit -->
                                <div class="d-sm-flex justify-content-center mt-4">
                                    <button type="submit" class="btn btn-primary table-button">Pay Now</button>
                                </div>
                            </form>
                        </div>
                        <!-- Card body END -->
                    </div>
                    <!-- Checkout card END -->
                </div>
                <!-- Main content END -->
            </div>
        </div>
    </section>
</main>
