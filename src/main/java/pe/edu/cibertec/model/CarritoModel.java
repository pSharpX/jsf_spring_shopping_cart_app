package pe.edu.cibertec.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by CHRISTIAN on 14/04/2018.
 */
public class CarritoModel {

    private Long id;
    private double total;
    private Date fechaCreacion;
    private Date fechaCompra;
    private String direccionEnvio;
    private boolean activo;
    private String usuario;
    private Long idUsuario;
    private List<DetalleCarritoModel> detalleCarrito;

    public CarritoModel() {
        detalleCarrito = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<DetalleCarritoModel> getDetalleCarrito() {
        return detalleCarrito;
    }

    public void setDetalleCarrito(List<DetalleCarritoModel> detalleCarrito) {
        this.detalleCarrito = detalleCarrito;
    }
}
