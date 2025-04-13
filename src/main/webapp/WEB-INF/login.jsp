<div class="container d-flex justify-content-center align-items-center mt-5 fade-up" style="min-height: 50vh;">
    <main class="form-signin p-4 p-md-5 border rounded-3 bg-body-tertiary" style="width: 100%; max-width: 30rem;">
        <c:if test="${not empty loginFail}">
        <div class="alert alert-danger mb-2">${loginFail}</div>
    </c:if>
    <form method="post" action="${appURL}/login" id="loginForm">
        <input type="hidden" name="redirect" value="${redirect}" />
        <h1 class="h3 mb-3 fw-normal mt-5">Please sign in</h1>

        <div class="form-floating">
            <input type="text" class="form-control" id="email" name="email" placeholder="name@example.com" value="${email}">
            <label for="email">Email address</label>
        </div>
        <div class="form-floating mt-3">
            <input type="password" class="form-control" id="password" name="password" placeholder="Password" value="${password}">
            <label for="password">Password</label>
        </div>

        <div class="form-check text-start my-3">
            <input class="form-check-input" type="checkbox" value="true" id="rememberMe" name="rememberMe" ${rememberMe eq 'true' ? 'checked' : ''}>
            <label class="form-check-label" for="rememberMe">
                Remember me for 30 days
            </label>
        </div>
        <button class="button-dark button-login" type="submit">Sign in</button>
        <p class="my-3 text-body-secondary "><a href="${appURL}/reset-password">Forgot password?</a> <br> Don't have an account? <a href="${appURL}/signup">Sign-up</a></p>
    </form>
</main>
</div>

<script src="${appURL}/scripts/fade-up-animation.js"></script>
