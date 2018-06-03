/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author JAdv-LM
 */

public class Cliente implements Serializable {
    private Integer codigo;
    private String nombre;
    private String apellido;
    private Character genero;
    private String profesion;
    private String numeroMovil;

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    private String operadorMovil;
    private Boolean publicidad;
    private String correo;
    private String numeroDocumento;
    private Integer cantidadHijos;
    private Date fechaNacimiento;

    public Cliente() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getNumeroMovil() {
        return numeroMovil;
    }

    public void setNumeroMovil(String numeroMovil) {
        this.numeroMovil = numeroMovil;
    }

    public String getOperadorMovil() {
        return operadorMovil;
    }

    public void setOperadorMovil(String operadorMovil) {
        this.operadorMovil = operadorMovil;
    }

    public Boolean getPublicidad() {
        return publicidad;
    }

    public void setPublicidad(Boolean publicidad) {
        this.publicidad = publicidad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Integer getCantidadHijos() {
        return cantidadHijos;
    }

    public void setCantidadHijos(Integer cantidadHijos) {
        this.cantidadHijos = cantidadHijos;
    }  
    
}
