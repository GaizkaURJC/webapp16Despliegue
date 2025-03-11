document.addEventListener("DOMContentLoaded", function () {
    // Referencias a los inputs del registro
    const telefonoInput = document.getElementById("telefono");
    const emailInput = document.getElementById("email");
    const nameInput = document.getElementById("name");
    const submitBtn = document.getElementById("submitBtn");
    const passwordInput = document.getElementById("password");
    const form = document.querySelector("form[action='/users/create']");

    // Referencias a los inputs del login
    const emailLoginInput = document.getElementById("emailLogin");
    const passwordLoginInput = document.getElementById("passwordLogin");
    const submitLoginBtn = document.getElementById("submitLogin");
    const loginForm = document.querySelector("form[action='/users/authenticate']");

    // Crear elementos de error debajo de los inputs del registro
    const telefonoError = document.createElement("span");
    const emailError = document.createElement("span");
    const nameError = document.createElement("span");
    const passwordError = document.createElement("span");

    // Crear elementos de error debajo de los inputs del login
    const emailLoginError = document.createElement("span");
    const passwordLoginError = document.createElement("span");

    [telefonoError, emailError, nameError, emailLoginError, passwordLoginError, passwordError].forEach((el) => {
        el.style.color = "red";
        el.style.fontSize = "0.9em";
        el.style.display = "block";
    });

    // Agregar mensajes de error debajo de cada campo
    telefonoInput?.parentNode.appendChild(telefonoError);
    emailInput?.parentNode.appendChild(emailError);
    nameInput?.parentNode.appendChild(nameError);
    emailLoginInput?.parentNode.appendChild(emailLoginError);
    passwordLoginInput?.parentNode.appendChild(passwordLoginError);
    passwordInput?.parentNode.appendChild(passwordError);

    function validarTelefono() {
        const telefono = telefonoInput?.value.trim();
        if (!telefono || !/^\d{9}$/.test(telefono)) {
            telefonoError.textContent = "El numero de telefono debe tener exactamente 9 dígitos.";
            return false;
        } else {
            telefonoError.textContent = "";
            return true;
        }
    }

    function validarEmail() {
        const email = emailInput?.value.trim();
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!email || !emailRegex.test(email)) {
            emailError.textContent = "Por favor, introduce un correo valido.";
            return false;
        } else {
            emailError.textContent = "";
            return true;
        }
    }

    function validarNombre() {
        const name = nameInput?.value.trim();
        if (!name || !/^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+(\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*$/.test(name)) {
            nameError.textContent = "El nombre debe comenzar con una mayuscula.";
            return false;
        } else {
            nameError.textContent = "";
            return true;
        }
    }

    function validarPassword() {
        const password = passwordInput?.value.trim();
        if (!password || password.length < 4) {
            passwordError.textContent = "La contrasena debe tener al menos 4 caracteres.";
            return false;
        } else {
            passwordError.textContent = "";
            return true;
        }
    }

    function validarEmailLogin() {
        const emailLogin = emailLoginInput?.value.trim();
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailLogin || !emailRegex.test(emailLogin)) {
            emailLoginError.textContent = "Por favor, introduce un correo valido.";
            return false;
        } else {
            emailLoginError.textContent = "";
            return true;
        }
    }

    function validarPasswordLogin() {
        const password = passwordLoginInput?.value.trim();
        if (!password || password.length < 4) {
            passwordLoginError.textContent = "La contrasena debe tener al menos 4 caracteres.";
            return false;
        } else {
            passwordLoginError.textContent = "";
            return true;
        }
    }


    // Habilitar/deshabilitar el botón de envío del registro
    function actualizarEstadoBotonRegistro() {
        if (validarTelefono() && validarEmail() && validarNombre() && validarPassword()) {
            submitBtn.disabled = false;
        } else {
            submitBtn.disabled = true;
        }
    }

    // Habilitar/deshabilitar el botón de envío del login
    function actualizarEstadoBotonLogin() {
        if (validarEmailLogin() && validarPasswordLogin()) {
            submitLoginBtn.disabled = false;
        } else {
            submitLoginBtn.disabled = true;
        }
    }


    // Eventos de validación para el registro
    telefonoInput?.addEventListener("input", () => {
        validarTelefono();
        actualizarEstadoBotonRegistro();
    });

    emailInput?.addEventListener("input", () => {
        validarEmail();
        actualizarEstadoBotonRegistro();
    });

    nameInput?.addEventListener("input", () => {
        validarNombre();
        actualizarEstadoBotonRegistro();
    });

    passwordInput?.addEventListener("input", () => {
        validarPassword();
        actualizarEstadoBotonRegistro();
    });


    // Eventos de validación para el login
    emailLoginInput?.addEventListener("input", () => {
        validarEmailLogin();
        actualizarEstadoBotonLogin();
    });

    passwordLoginInput?.addEventListener("input", () => {
        validarPasswordLogin();
        actualizarEstadoBotonLogin();
    });


    // Evitar envío del formulario si hay errores
    form?.addEventListener("submit", function (event) {
        if (submitBtn.disabled) {
            event.preventDefault();
        }
    });

    loginForm?.addEventListener("submit", function (event) {
        if (submitLoginBtn.disabled) {
            event.preventDefault();
        }
    });


    // Deshabilitar el botón cuando se abre el modal de login
    const loginModal = document.getElementById("modalLogin");
    loginModal.addEventListener("show.bs.modal", function () {
        submitLoginBtn.disabled = true;
    });

    // Deshabilitar el botón cuando se abre el modal de registro
    const registerModal = document.getElementById("modalSingUp");
    registerModal.addEventListener("show.bs.modal", function () {
        submitBtn.disabled = true;
    });
});
