/**
 * Variable global utilizada para almacenar temporalmente el id de la tarea
 * que se mostrará en el modal de confirmación antes de eliminarlo.
 */
var id = "4";

/**
 * Esta función se encarga de mostrar el modal de confirmación antes de eliminar una tarea.
 * Se dispara cuando se muestra el modal.
 */
$('#eliminar').on('show.bs.modal', function (event) {
    // Obtiene el botón que desencadenó el evento de mostrar el modal
    var button = $(event.relatedTarget);

    // Obtiene el id de la tarea desde el atributo 'data-nombre' del botón
    var idTarea = button.data('nombre');

    console.log("idTarea");
    // Obtiene el modal actual
    var modal = $(this);

    // Almacena el nombre de la tarea en la variable global 'id'
    id = idTarea;

});

/**
 * Esta función se encarga de eliminar una tarea a través de una solicitud AJAX al servidor.
 */
function eliminarTarea() {

    // Obtiene el id de la tarea desde una variable previamente definida (id)
    var idTarea = id;

    // Registra el id de la tarea en la consola (para fines de depuración)
    console.log(id);

    // Realiza una solicitud AJAX al servlet 'SvEliminar' para eliminar la solicitud
    $.ajax({
        url: 'SvTareas?id=' + idTarea, // URL del servlet con el parámetro 'id' para la eliminación
        method: 'GET', // Método HTTP utilizado para la solicitud (GET en este caso)
        success: function (data) {
            // En caso de éxito en la solicitud:

            // Cierra el modal de eliminación
            $('#eliminar').modal('hide');

            // Recarga la página actual para reflejar los cambios
            location.reload();
        },
        error: function () {
            // En caso de error en la solicitud:

            // Registra un mensaje de error en la consola (para fines de depuración)
            console.log('Error al eliminar la tarea');
        }
    });
}

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