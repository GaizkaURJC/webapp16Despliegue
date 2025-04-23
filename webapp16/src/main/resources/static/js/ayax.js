document.getElementById('reservaForm').onsubmit = async function(e) {
    e.preventDefault();

    const formData = new FormData(e.target);

    const response = await fetch('/reserva/request', {
        method: 'POST',
        body: formData
    });

    if (response.ok) {
        // Crear un enlace invisible para forzar la descarga del PDF
        const pdfBlob = await response.blob();
        const link = document.createElement('a');
        link.href = URL.createObjectURL(pdfBlob);
        link.download = 'reserva.pdf';
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);

        // Redirigir al index después de la descarga
        window.location.href = '/';
    } else {
        alert('Hubo un error al crear la reserva.');
    }
};