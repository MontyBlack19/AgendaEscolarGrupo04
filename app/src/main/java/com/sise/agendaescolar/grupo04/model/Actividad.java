package com.sise.agendaescolar.grupo04.model;

public class Actividad {

    public Actividad() {
    }

    private int id;
    private String titulo;
    private String descripcion;
    private String fecha;
    private String tipo;
    private double calificacion;
    private String estado;
    private String curso;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Actividad(String descripcion, String titulo, String fecha, String tipo, double calificacion, String estado, String curso) {
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.fecha = fecha;
        this.tipo = tipo;
        this.calificacion = calificacion;
        this.estado = estado;
        this.curso = curso;
    }

    public Actividad(int id, String titulo, String descripcion, String fecha, String tipo, double calificacion, String estado, String curso) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.tipo = tipo;
        this.calificacion = calificacion;
        this.estado = estado;
        this.curso = curso;
    }


    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha='" + fecha + '\'' +
                ", tipo='" + tipo + '\'' +
                ", calificacion=" + calificacion +
                ", estado='" + estado + '\'' +
                ", curso='" + curso + '\'' +
                '}';
    }


}
