document.addEventListener("DOMContentLoaded", function () {
    const filtros = document.querySelectorAll("#conciertos-filters a");
    const conciertos = document.querySelectorAll(".portfolio-item");

    function normalizar(texto) {
        return texto.trim().toLowerCase();
    }

    function aplicarFiltro(categoriaSeleccionada) {
        conciertos.forEach(concierto => {
            const categoriaConcierto = normalizar(concierto.getAttribute("data-category"));
            if (categoriaSeleccionada === "all" || categoriaSeleccionada === categoriaConcierto) {
                concierto.style.display = "block";
            } else {
                concierto.style.display = "none";
            }
        });
        reiniciarLoadMore();
    }

    function reiniciarLoadMore() {
        const loadMoreButton = document.querySelector('.loadMore');
        let currentItems = 4;
        const elementList = [...document.querySelectorAll('.portfolio-item')].filter(el => el.style.display === 'block');

        // Mostrar solo los primeros 4 y ocultar el resto
        elementList.forEach((el, index) => {
            el.style.display = (index < currentItems) ? 'block' : 'none';
        });

        // Mostrar o esconder el botón "Load More"
        if (elementList.length <= currentItems) {
            loadMoreButton.style.display = 'none';
        } else {
            loadMoreButton.style.display = 'block';
        }

        // Función de paginación al hacer clic en "Load More"
        loadMoreButton.onclick = function (e) {
            e.preventDefault();
            for (let i = currentItems; i < currentItems + 4; i++) {
                if (elementList[i]) {
                    elementList[i].style.display = 'block';
                }
            }
            currentItems += 4;

            if (currentItems >= elementList.length) {
                loadMoreButton.style.display = 'none';
            }
        };
    }

    filtros.forEach(filtro => {
        filtro.addEventListener("click", function (e) {
            e.preventDefault();
            filtros.forEach(f => f.classList.remove("activo"));
            this.classList.add("activo");

            const categoriaSeleccionada = normalizar(this.getAttribute("data-category"));
            aplicarFiltro(categoriaSeleccionada);
        });
    });

    // Simular clic inicial en "Todos" al cargar la página
    aplicarFiltro("all");
    filtros[0].classList.add("activo"); // Añadir clase activa al primer filtro
});
