
/**
 * Función que muestra el modal llamado usuarioRegistrado
 */
function usuarioVerificado() {
    $("#usuarioRegistrado").modal("show");
}

/**
 * Función que muestra el modal llamado usuarioNoRegistrado
 */
function usuarioSinVerificar() {
    $("#usuarioNoRegistrado").modal("show");
}

/**
 * Función que muestra el modal llamado mensajeAlerta
 */
function cedulaExistente() {
    $("#mensajeAlerta").modal("show");
}

$(document).on('click', '.btn-danger', function () {
    var tareaId = $(this).data('tareaid');
    var tareaTitulo = $(this).data('tareatitulo');
    $('#tareaTituloToDelete').text(tareaTitulo);

    // Configura el evento click para el botón de eliminación en el modal
    $('#confirmDeleteButton').off('click').on('click', function () {
        // Realiza la solicitud para eliminar la tarea con ID 'tareaId'
        $.ajax({
            url: 'SvTareas?id=' + tareaId,
            type: 'GET',
            success: function (data) {
                if (data === 'OK') {
                    // Eliminación exitosa, recarga la página o actualiza la tabla de datos
                    window.location.reload(); // Recarga la página completa
                    // También puedes eliminar la fila de la tabla sin recargar la página si lo prefieres
                } else {
                    // Maneja el caso en el que no se pudo eliminar la tarea
                    alert('Error al eliminar la tarea.');
                }
            },
            error: function () {
                alert('Error al comunicarse con el servidor.');
            }
        });

        // Cierra el modal de confirmación
        $('#confirmDeleteModal').modal('hide');
    });
});