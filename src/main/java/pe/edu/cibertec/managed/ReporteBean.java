/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.managed;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Java-LM
 */

@ManagedBean(name = "reporte")
public class ReporteBean {
    
    private String tipo;
    private String titulo;
    
    public void confirmarAccion(ActionEvent ae){
        tipo = (String)ae.getComponent().getAttributes().get("tipo");
        titulo = (String)ae.getComponent().getAttributes().get("titulo");
    }
    
    public String consultar(){
        return "reporte";
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
}
