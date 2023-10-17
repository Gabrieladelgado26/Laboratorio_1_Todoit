package com.mycompany.mundo;

import java.io.Serializable;

public class ListasEnlazadas implements Serializable {

    private Tarea cabezera;

    public void ListasEnlazadas() {
        this.cabezera = null;
    }

    public void AgregarNodo(int id, String titulo, String descripcion, String fecha, String cedulaUsuario) {

        Tarea nuevo = new Tarea(id, titulo, descripcion, fecha, cedulaUsuario);
        if (this.cabezera == null) {
            this.cabezera = nuevo;
        } else {
            Tarea aux = this.cabezera;

            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }

    }

    public String MostrarLista() {

        Tarea aux = this.cabezera;
        String resultado = "";

        // System.out.println(cedula.trim());
        while (aux != null) {

            //if (aux.getCedulaUsuario().equals(cedula)) {
            resultado += "<tr>";
            resultado += "<td>" + aux.getId() + "</td>";
            resultado += "<td>" + aux.getTitulo() + "</td>";
            resultado += "<td>" + aux.getDescripcion() + "</td>";
            resultado += "<td>" + aux.getFecha() + "</td>";
            resultado += "<td> <a href=\"#\" class=\"btn btn-success\" data-bs-toggle=\"modal\" data-bs-target=\"#editModalConfirm\" data-nombre=\"" + aux.getId() + "\"><i class=\"fas fa-marker\"></i></a>\n"
                    + "<a href=\"#\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#confirmDeleteModal\" data-tareaid=\"<%=id%>\" data-tareaTitulo=\"<%=titulo%>\"><i class=\"fas fa-trash-alt\"></i></a></td>";
            resultado += "</tr>";
            //}
            aux = aux.siguiente;
        }
        return resultado;
    }

    public boolean EliminarNodo(int idTarea) {
        if (cabezera == null) {
            return false; // La lista está vacía, no hay nada que eliminar.
        }

        if (cabezera.getId() == idTarea) {
            // Si el nodo a eliminar es el primer nodo (cabezera).
            cabezera = cabezera.siguiente;
            return true;
        }

        Tarea actual = cabezera;
        while (actual.siguiente != null) {
            if (actual.siguiente.getId() == idTarea) {
                // Encontramos el nodo a eliminar.
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }

        return false; // El nodo con el idTarea no se encontró en la lista.
    }

    public boolean EditarTarea(int idTarea, String nuevoTitulo, String nuevaDescripcion, String nuevaFecha) {
        Tarea actual = cabezera;

        while (actual != null) {
            if (actual.getId() == idTarea) {
                actual.setTitulo(nuevoTitulo);
                actual.setDescripcion(nuevaDescripcion);
                actual.setFecha(nuevaFecha);
                return true; // Tarea editada con éxito
            }

            actual = actual.siguiente;
        }

        return false; // La tarea no se encontró
    }
}
