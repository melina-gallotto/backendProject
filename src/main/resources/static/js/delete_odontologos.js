function deleteBy(id) {
    const url = '/odontologos/' + id;
    const settings = {
        method: 'DELETE'
    };

    fetch(url, settings)
        .then(response => {
            if (response.ok) {
                let row_id = "#tr_" + id;
                document.querySelector(row_id).remove();
                console.log("Odontologo eliminado con éxito: " + id);
            } else {
                console.error("Error al eliminar odontologo: " + id);
            }
        })
        .catch(error => {
            console.error("Error en la solicitud:", error);
        });
}
