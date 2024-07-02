package com.jimenez.app.libreria.models.DTO;

import java.util.Date;

public class RentaDTO {

    private String rentaId;

    private String nombreUsuario;

    private String titulo;

    private String nombreResponsable;

    private Date fechaRenta;

    private Date fechaDevolucion;

    private String estado;

    private Long precio;

    public String getRentaId() {
        return rentaId;
    }

    public void setRentaId(String rentaId) {
        this.rentaId = rentaId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public Date getFechaRenta() {
        return fechaRenta;
    }

    public void setFechaRenta(Date fechaRenta) {
        this.fechaRenta = fechaRenta;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolcion) {
        this.fechaDevolucion = fechaDevolcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }
}
