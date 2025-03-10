document.addEventListener("DOMContentLoaded", function() {
    const loginSection = document.getElementById("login-section");
    const registerSection = document.getElementById("register-section");
    const switchToRegister = document.getElementById("switch-to-register");
    const switchToLogin = document.getElementById("switch-to-login");

    // Asegurar que solo el login esté visible al cargar la página
    registerSection.style.display = "none";

    switchToRegister.addEventListener("click", function(event) {
        event.preventDefault();
        loginSection.style.display = "none";
        registerSection.style.display = "flex";  // Usamos "flex" para mantener la alineación
    });

    switchToLogin.addEventListener("click", function(event) {
        event.preventDefault();
        registerSection.style.display = "none";
        loginSection.style.display = "flex";
    });
});
