<main class="form-signin w-100 m-auto">
    <c:if test="${not empty loginFail}">
        <div class="alert alert-danger mb-2">${loginFail}</div>
    </c:if>
    <form method="post" action="${appURL}/login" id="loginForm">
        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

        <div class="form-floating">
            <input type="text" class="form-control" id="email" name="email" placeholder="name@example.com" value="${email}">
            <label for="email">Email address</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="password" name="password" placeholder="Password" value="${password}">
            <label for="password">Password</label>
        </div>

        <div class="form-check text-start my-3">
            <input class="form-check-input" type="checkbox" value="true" id="rememberMe" name="rememberMe" ${rememberMe eq 'true' ? 'checked' : ''}>
            <label class="form-check-label" for="rememberMe">
                Remember me for 30 days
            </label>
        </div>
        <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
        <p class="mt-5 mb-3 text-body-secondary">Don't have an account? <a href="${appURL}/signup">Sign-up</a></p>
    </form>
</main>