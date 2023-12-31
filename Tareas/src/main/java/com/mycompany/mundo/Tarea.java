
package com.mycompany.mundo;

import java.io.Serializable;

public class Tarea implements Serializable{

    private int id;
    private String titulo;
    private String descripcion;
    private String fecha;
    public Tarea siguiente;


    public Tarea(int id, String titulo, String descripcion,  String fecha) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.siguiente = null;
    }

    public Tarea() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Tarea getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Tarea siguiente) {
        this.siguiente = siguiente;
    }
}
