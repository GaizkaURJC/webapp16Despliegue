document.addEventListener("DOMContentLoaded", function () {
    var loader = document.getElementById("loader");

    function showLoader() {
        loader.style.display = "block";
    }

    function hideLoader() {
        loader.style.display = "none";
    }

    // Mostrar el loader al enviar formularios
    var forms = document.querySelectorAll("form");
    forms.forEach(function (form) {
        form.addEventListener("submit", showLoader);
    });

    // Ocultar el loader después de cargar la página
    window.addEventListener("load", hideLoader);

    // Función para el botón "Load More"
    const loadMoreButton = document.querySelector('.loadMore');
    let currentItems = 4;
    loadMoreButton.addEventListener('click', (e) => {
        e.preventDefault();
        const elementList = [...document.querySelectorAll('.portfolio-item')];
        for (let i = currentItems; i < currentItems + 4; i++) {
            if (elementList[i]) {
                elementList[i].style.display = 'block';
            }
        }
        currentItems += 4;

        // Ocultar el botón si no hay más elementos para mostrar
        if (currentItems >= elementList.length) {
            loadMoreButton.style.display = 'none';
        }
    });

    // Inicialmente mostrar solo los primeros 3 elementos
    const elementList = [...document.querySelectorAll('.portfolio-item')];
    elementList.forEach((el, index) => {
        if (index >= currentItems) {
            el.style.display = 'none';
        }
    });
});