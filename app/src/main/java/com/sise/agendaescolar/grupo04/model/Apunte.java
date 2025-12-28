package com.sise.agendaescolar.grupo04.model;

public class Apunte {
    private int id;
    private String titulo;
    private String contenido;
    private String fecha;
    private String curso;

    public Apunte() {
    }

    public Apunte(int id, String titulo, String contenido, String fecha, String curso) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.curso = curso;
    }

    public Apunte(String titulo, String contenido, String fecha, String curso) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.curso = curso;
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Apunte{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", contenido='" + contenido + '\'' +
                ", fecha='" + fecha + '\'' +
                ", curso='" + curso + '\'' +
                '}';
    }
}
