document.addEventListener("DOMContentLoaded", function () {
    const filtros = document.querySelectorAll("#conciertos-filters a");
    const conciertos = document.querySelectorAll(".portfolio-item");
    const loader = document.getElementById("loader"); // Obtener el loader

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

        elementList.forEach((el, index) => {
            el.style.display = (index < currentItems) ? 'block' : 'none';
        });

        if (elementList.length <= currentItems) {
            loadMoreButton.style.display = 'none';
        } else {
            loadMoreButton.style.display = 'block';
        }

        loadMoreButton.onclick = function (e) {
            e.preventDefault();

            // Mostrar el loader
            loader.style.display = "block";
            loadMoreButton.disabled = true; // Deshabilitar botón mientras carga

            setTimeout(() => {
                for (let i = currentItems; i < currentItems + 4; i++) {
                    if (elementList[i]) {
                        elementList[i].style.display = 'block';
                    }
                }
                currentItems += 4;

                if (currentItems >= elementList.length) {
                    loadMoreButton.style.display = 'none';
                }

                // Ocultar el loader después de cargar
                loader.style.display = "none";
                loadMoreButton.disabled = false;
            }, 200);
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

    aplicarFiltro("all"); // Inicializa con todos visibles al principio
});
