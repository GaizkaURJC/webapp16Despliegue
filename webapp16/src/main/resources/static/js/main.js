// This script will show the login and register forms when the user clicks on the corresponding buttons
document.addEventListener("DOMContentLoaded", function () {
    const loginSection = document.getElementById("login-section");
    const registerSection = document.getElementById("register-section");
    const switchToRegister = document.getElementById("switch-to-register");
    const switchToLogin = document.getElementById("switch-to-login");

    // Ensure only the login is visible when the page loads
    registerSection.style.display = "none";

    switchToRegister.addEventListener("click", function (event) {
        event.preventDefault();
        loginSection.style.display = "none";
        registerSection.style.display = "flex";  // Use "flex" to maintain alignment
    });

    switchToLogin.addEventListener("click", function (event) {
        event.preventDefault();
        registerSection.style.display = "none";
        loginSection.style.display = "flex";
    });
});


// This script will show a chart with the distribution of the users by gender
document.addEventListener('DOMContentLoaded', function () {
    // Create the chart context
    const ctx = document.getElementById('genderChart').getContext('2d');

    // Fetch data from the API
    fetch('/api/statistics/gender-distribution')
        .then(response => response.json())
        .then(data => {
            const maleCount = data['Hombre'] || 0;
            const femaleCount = data['Mujer'] || 0;

            // Create the 'pie' chart with the obtained data
            const myChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: ['Hombre', 'Mujer'],
                    datasets: [{
                        data: [maleCount, femaleCount],
                        backgroundColor: [
                            '#f2f526', // Color for men
                            'black'    // Color for women
                        ],
                        borderColor: [
                            '#f2f526',
                            'black'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false, // Ensure the chart takes the full width of the container
                    plugins: {
                        legend: {
                            position: 'bottom',
                        },
                        title: {
                            display: true,
                            text: 'Distribución por género'
                        }
                    }
                }
            });
        })
        .catch(error => {
            console.error('Error al obtener los datos de género:', error);
        });
});


// This script will show a loader when a form is submitted and hide it when the page has finished loading
document.addEventListener("DOMContentLoaded", function () {
    var loader = document.getElementById("loader");

    function showLoader() {
        loader.style.display = "block";
    }

    function hideLoader() {
        loader.style.display = "none";
    }

    // Show the loader when a form is submitted
    var forms = document.querySelectorAll("form");
    forms.forEach(function (form) {
        form.addEventListener("submit", showLoader);
    });

    // Hide the loader when the page has finished loading
    window.addEventListener("load", hideLoader);
});
