document.addEventListener("DOMContentLoaded", function () {
    const emailInput = document.getElementById("email");
    const rememberMeCheckbox = document.getElementById("rememberMe");
    const loginForm= document.getElementById("loginForm");
    // Load saved email if it exists
    const savedEmail = localStorage.getItem("rememberedEmail");
    if (savedEmail) {
        emailInput.value = savedEmail;
        rememberMeCheckbox.checked = true;
    }

    loginForm.addEventListener("submit", function (event) {
        if (rememberMeCheckbox.checked) {
            localStorage.setItem("rememberedEmail", emailInput.value);
        } else {
            localStorage.removeItem("rememberedEmail");
        }
    });
});