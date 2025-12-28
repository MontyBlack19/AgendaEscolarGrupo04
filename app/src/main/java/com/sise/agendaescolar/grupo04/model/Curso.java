package com.sise.agendaescolar.grupo04.model;

public class Curso {

    public Curso() {
    }

    private int id;
    private String nombre;
    private String descripcion;
    private String docente;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public Curso(String nombre, String descripcion, String docente) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.docente = docente;
    }

    public Curso(int id, String nombre, String descripcion, String docente) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.docente = docente;
    }



    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", docente='" + docente + '\'' +
                '}';
    }

}
