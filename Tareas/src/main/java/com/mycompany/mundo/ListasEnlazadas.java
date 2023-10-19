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

        Tarea tarea = this.cabezera;
        String resultado = "";

        // System.out.println(cedula.trim());
        while (tarea != null) {

            //if (aux.getCedulaUsuario().equals(cedula)) {
            resultado += "<tr>";
            resultado += "<td>" + tarea.getId() + "</td>";
            resultado += "<td>" + tarea.getTitulo() + "</td>";
            resultado += "<td>" + tarea.getDescripcion() + "</td>";
            resultado += "<td>" + tarea.getFecha() + "</td>";
            resultado += "<td> <a href=\"#\" class=\"btn btn-success\" data-bs-toggle=\"modal\" data-bs-target=\"#editModalConfirm\" data-nombre=\"" + tarea.getId() + "\"><i class=\"fa-solid fa-marker\"></i></a>";
            resultado += "<a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#eliminar\" data-nombre=\"" + tarea.getId() + "\"><i class=\"fa-solid fa-trash\"></i></a></td>";
            resultado += "</tr>";
            System.out.println(tarea.getId());
            //}
            tarea = tarea.siguiente;
        }
        return resultado;
    }

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