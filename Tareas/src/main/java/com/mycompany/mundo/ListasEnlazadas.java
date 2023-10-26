package com.mycompany.mundo;

import java.io.Serializable;

public class ListasEnlazadas implements Serializable {

    // Cabeza de la lista enlazada que apunta al primer nodo
    private Tarea cabezera;

    /**
     * Agrega un nuevo nodo al principio de la lista
     *
     * @param id ID de la tarea
     * @param titulo Título de la tarea
     * @param descripcion Descripción de la tarea
     * @param fecha Fecha de la tarea
     */
    public void agregarNodoInicio(int id, String titulo, String descripcion, String fecha) {
        Tarea nuevo = new Tarea(id, titulo, descripcion, fecha);
        nuevo.siguiente = cabezera;

        // El nuevo nodo se convierte en la nueva cabeza de la lista
        cabezera = nuevo;
    }

    /**
     * Agrega un nuevo nodo antes de una tarea existente en la lista
     *
     * @param id ID de la nueva tarea
     * @param idTarea ID de la tarea existente antes de la cual se agregará la
     * nueva tarea
     * @param titulo Título de la nueva tarea
     * @param descripcion Descripción de la nueva tarea
     * @param fecha Fecha de la nueva tarea
     */
    public void agregarNodoAntes(int id, int idTarea, String titulo, String descripcion, String fecha) {
        Tarea nuevo = new Tarea(id, titulo, descripcion, fecha);

        // Si la lista está vacía, el nuevo nodo se convierte en la cabeza de la lista
        if (cabezera == null) {
            cabezera = nuevo;
            return;
        }

        // Si la tarea existe en la cabeza de la lista, el nuevo nodo se agrega antes de la cabeza
        if (cabezera.getId() == idTarea) {
            nuevo.siguiente = cabezera;
            cabezera = nuevo;
            return;
        }

        Tarea anterior = null;
        Tarea actual = cabezera;

        // Buscar la tarea existente y agregar el nuevo nodo antes de ella
        while (actual != null) {
            if (actual.getId() == idTarea) {
                nuevo.siguiente = actual;
                anterior.siguiente = nuevo;
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
    }

    /**
     * Agrega un nuevo nodo después de una tarea existente en la lista
     *
     * @param id ID de la nueva tarea
     * @param idTarea ID de la tarea existente después de la cual se agregará la
     * nueva tarea
     * @param titulo Título de la nueva tarea
     * @param descripcion Descripción de la nueva tarea
     * @param fecha Fecha de la nueva tarea
     */
    public void agregarNodoDespues(int id, int idTarea, String titulo, String descripcion, String fecha) {
        Tarea nuevo = new Tarea(id, titulo, descripcion, fecha);

        // Si la lista está vacía, el nuevo nodo se convierte en la cabeza de la lista
        if (cabezera == null) {
            cabezera = nuevo;
            return;
        }

        Tarea actual = cabezera;

        // Buscar la tarea existente y agregar el nuevo nodo después de ella
        while (actual != null) {
            if (actual.getId() == idTarea) {
                nuevo.siguiente = actual.siguiente;
                actual.siguiente = nuevo;
                return;
            }
            actual = actual.siguiente;
        }
    }

    /**
     * Agrega un nuevo nodo al final de la lista
     *
     * @param id ID de la tarea
     * @param titulo Título de la tarea
     * @param descripcion Descripción de la tarea
     * @param fecha Fecha de la tarea
     */
    public void agregarNodoFinal(int id, String titulo, String descripcion, String fecha) {
        Tarea nuevo = new Tarea(id, titulo, descripcion, fecha);

        // Si la lista está vacía, el nuevo nodo se convierte en la cabeza de la lista
        if (cabezera == null) {
            cabezera = nuevo;
        } else {
            Tarea aux = cabezera;

            // Encontrar el último nodo de la lista y agregar el nuevo nodo al final
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
    }

    public boolean idExistente(int id) {
        Tarea actual = cabezera;

        // Itera a través de la lista enlazada
        while (actual != null) {
            if (actual.getId() == id) {
                return true; // El ID existe en la lista
            }
            actual = actual.siguiente;
        }

        // El ID no se encontró en la lista
        return false;
    }

    /**
     * Genera una representación en formato HTML de la lista de tareas
     *
     * @return Cadena HTML que representa las tareas en la lista
     */
    public String MostrarLista() {
        Tarea tarea = this.cabezera;
        String resultado = "";

        if (tarea != null) {
            // Iterar sobre la lista de tareas y construir una representación HTML de las tareas
            while (tarea != null) {
                resultado += "<tr>";
                resultado += "<td>" + tarea.getId() + "</td>";
                resultado += "<td>" + tarea.getTitulo() + "</td>";
                resultado += "<td>" + tarea.getDescripcion() + "</td>";
                resultado += "<td>" + tarea.getFecha() + "</td>";
                resultado += "<td> <a href=\"#\" type=\"button\" class=\"btn btn-success\" data-bs-toggle=\"modal\" data-bs-target=\"#editModalConfirm\" data-nombre=\"" + tarea.getId() + "\"><i class=\"fa-solid fa-marker\"></i></a>";
                resultado += "<a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#eliminar\" data-nombre=\"" + tarea.getId() + "\"><i class=\"fa-solid fa-trash\"></i></a></td>";
                resultado += "</tr>";
                tarea = tarea.siguiente;
            }
        } else if (tarea == null) {
            resultado += "<tr>";
            // colspan="6" Sirve para ocupar todas las columnas de la tabla de datos
            resultado += "<td colspan='6' align='center' valign='middle'>No se encontro ninguna tarea registrada</td>";
            resultado += "<tr>";
        }
        return resultado;
    }

    /**
     * Busca una tarea de la lista en base a su ID
     *
     * @param idTarea ID de la tarea a buscar
     */
    public String tablaBusqueda(String inputId) {
        // Inicializa la tarea de búsqueda como la cabeza de la lista
        Tarea tarea = this.cabezera;
        // Inicializa una cadena para almacenar el resultado HTML
        String resultado = "";
        // Bandera para indicar si se encontró una tarea con el ID especificado
        boolean tareaId = false;

        // Verifica si la lista de tareas, el inputId y no está vacío
        if (tarea != null && inputId != null && !inputId.isEmpty()) {
            // Recorre la lista de tareas
            while (tarea != null) {
                // Compara el ID de la tarea con el inputId
                if (tarea.getId() == Integer.parseInt(inputId)) {
                    // Agrega información de la tarea al resultado HTML
                    resultado += "<tr>";
                    resultado += "<td>" + tarea.getId() + "</td>";
                    resultado += "<td>" + tarea.getTitulo() + "</td>";
                    resultado += "<td>" + tarea.getDescripcion() + "</td>";
                    resultado += "<td>" + tarea.getFecha() + "</td>";
                    // Agrega botones para edición y eliminación
                    resultado += "<td> <a href=\"#\" type=\"button\" class=\"btn btn-success\" data-bs-toggle=\"modal\" data-bs-target=\"#editModalConfirm\" data-nombre=\"" + tarea.getId() + "\"><i class=\"fa-solid fa-pen-clip\"></i></a>";
                    resultado += "<a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#eliminar\" data-nombre=\"" + tarea.getId() + "\"><i class=\"fa-solid fa-trash\"></i></a></td>";
                    resultado += "</tr>";
                    // Marca que se encontró la tarea
                    tareaId = true;
                }
                tarea = tarea.siguiente;
            }
        }

        // Si no se encontró la tarea, agrega un mensaje al resultado
        if (!tareaId) {
            resultado += "<tr>";
            // colspan="6" sirve para ocupar todas las columnas de la tabla de datos
            resultado += "<td colspan='6' align='center' valign='middle'>No se encontró ninguna tarea con el ID ingresado</td>";
            resultado += "<tr>";
        }
        // Devuelve el resultado HTML
        return resultado;
    }

    /**
     * Elimina una tarea de la lista en base a su ID
     *
     * @param idTarea ID de la tarea a eliminar
     */
    public void eliminarTarea(int idTarea) {
        if (cabezera != null) {
            // Caso especial: eliminación del primer elemento
            if (cabezera.getId() == idTarea) {
                Tarea temp = cabezera;
                cabezera = cabezera.siguiente;
                temp.siguiente = null;
                return;  // Tarea eliminada, salimos del método
            }

            Tarea anterior = cabezera;
            Tarea actual = cabezera.siguiente;

            // Buscar y eliminar la tarea en la lista
            while (actual != null) {
                if (actual.getId() == idTarea) {
                    // Encontramos la tarea a eliminar
                    anterior.siguiente = actual.siguiente;
                    actual.siguiente = null;
                    return;  // Tarea eliminada, salimos del método
                }
                anterior = actual;
                actual = actual.siguiente;
            }
        }
    }

    /**
     * Edita una tarea en la lista en base a su ID, actualizando sus atributos
     * si se proporcionan nuevos valores.
     *
     * @param id ID de la tarea a editar
     * @param nuevoTitulo Nuevo título para la tarea (dejar vacío para no
     * modificar)
     * @param nuevaDescripcion Nueva descripción para la tarea (dejar vacío para
     * no modificar)
     * @param nuevaFecha Nueva fecha para la tarea (dejar vacío para no
     * modificar)
     */
    public void EditarTarea(int id, String nuevoTitulo, String nuevaDescripcion, String nuevaFecha) {
        System.out.println("Corriendo método editar"); // Imprime un mensaje para la depuración

        Tarea actual = cabezera;

        // Itera a través de la lista de tareas
        while (actual != null) {

            if (actual.getId() == id) {
                // Si encuentra una tarea con el ID especificado, verifica si se proporcionan nuevos valores y los actualiza

                if (!nuevoTitulo.equals("")) {
                    actual.setTitulo(nuevoTitulo);
                }

                if (!nuevaDescripcion.equals("")) {
                    actual.setDescripcion(nuevaDescripcion);
                }

                if (!nuevaFecha.equals("")) {
                    actual.setFecha(nuevaFecha);
                }
            }
            actual = actual.siguiente;
        }
    }

    /**
     * Verifica si la lista de tareas no está vacía.
     *
     * @return true si la lista no está vacía, de lo contrario, false
     */
    public boolean verificar() {
        Tarea actual = cabezera;
        // En caso de que el primer elemento de la lista no sea nulo, se devuelve true
        if (actual != null) {
            return true;
        }
        return false; // Retorna false si nunca entró en el condicional, es decir, la lista está vacía
    }
}
