
package com.mycompany.mundo;

import java.io.Serializable;

public class Tarea implements Serializable{
    
    private String cedulaUsuario;
    private int id;
    private String titulo;
    private String descripcion;
    private String fecha;
    public Tarea siguiente;

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public Tarea(int id, String titulo, String descripcion,  String fecha, String cedulaUsuario) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.cedulaUsuario = cedulaUsuario;
        this.siguiente = null;
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
