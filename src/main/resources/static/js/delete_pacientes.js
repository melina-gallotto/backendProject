function deleteBy(id) {
    const url = '/pacientes/' + id;
    const settings = {
        method: 'DELETE'
    };

    fetch(url, settings)
        .then(response => {
            if (response.ok) {
                let row_id = "#tr_" + id;
                document.querySelector(row_id).remove();
                console.log("Paciente eliminado con éxito: " + id);
            } else {
                console.error("Error al eliminar paciente: " + id);
            }
        })
        .catch(error => {
            console.error("Error en la solicitud:", error);
        });
}
