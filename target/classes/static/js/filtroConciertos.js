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
        let currentItems = 3;
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
            for (let i = currentItems; i < currentItems + 3; i++) {
                if (elementList[i]) {
                    elementList[i].style.display = 'block';
                }
            }
            currentItems += 3;

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

    aplicarFiltro("all"); // Inicializa con todos visibles al principio
});
