package pe.edu.cibertec.model;

/**
 * Created by CHRISTIAN on 14/04/2018.
 */
public class DetalleCarritoModel {

    private Long id;
    private String carrito;
    private Long idCarrito;
    private String producto;
    private Long idProducto;
    private int cantidad;
    private double precioUnitario;
    private double subTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarrito() {
        return carrito;
    }

    public void setCarrito(String carrito) {
        this.carrito = carrito;
    }

    public Long getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubTotal() {
        return cantidad * precioUnitario;
    }
}
