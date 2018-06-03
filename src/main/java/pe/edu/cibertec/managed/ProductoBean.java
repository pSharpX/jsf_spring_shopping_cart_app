package pe.edu.cibertec.managed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.dto.ProductoDto;
import pe.edu.cibertec.servicio.ProductoServicio;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by CHRISTIAN on 15/04/2018.
 */
@Component
@Scope(value = "session")
public class ProductoBean {

    private ProductoDto producto = new ProductoDto();
    private String mensaje;
    private Collection<ProductoDto> productos;

    @Autowired
    private ProductoServicio productoService;

    @PostConstruct
    public void init() {
    }

    public String principal() {
        return "home";
    }

    public String mostrar(Long codigo) {
        try {
            producto = productoService.buscar(codigo);
        } catch (Exception ex) {
        }
        return "product_detail";
    }

    public String listar(){
        try {
            productos = productoService.obtenerTodos();
        } catch (Exception ex) {
        }
        return "product_list";
    }

    public  String crear(){
        try{
            producto = new ProductoDto();
        }catch (Exception ex){
        }
        return "product_create";
    }

    public String guardar(ProductoDto producto){
        try{
            this.productoService.crear(producto);
            return "product_list";
        }catch (Exception ex){
            FacesMessage fm = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }
    }

    public String editar(Long codigo){
        try{
            producto = this.productoService.buscar(codigo);
            return "product_edit";
        }catch (Exception ex){
            FacesMessage fm = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }
    }

    public String actualizar(ProductoDto producto){
        try{
            if (producto == null || producto.getId() == null) {
                throw new Exception("error");
            }
            this.productoService.actualizar(producto);
            return "product_list";
        }catch (Exception ex){
            FacesMessage fm = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }
    }

    public ProductoDto getProducto() {
        return producto;
    }

    public void setProducto(ProductoDto producto) {
        this.producto = producto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Collection<ProductoDto> getProductos() {
        return productos;
    }

    public void setProductos(Collection<ProductoDto> productos) {
        this.productos = productos;
    }

    public boolean isNombreInputValid() {
        FacesContext context = FacesContext.getCurrentInstance();
        UIInput input = (UIInput) context.getViewRoot().findComponent("product_form:nombre");
        return input.isValid();
    }

    public boolean isDescripcionInputValid() {
        FacesContext context = FacesContext.getCurrentInstance();
        UIInput input = (UIInput) context.getViewRoot().findComponent("product_form:descripcion");
        return input.isValid();
    }

    public boolean isPrecioInputValid() {
        FacesContext context = FacesContext.getCurrentInstance();
        UIInput input = (UIInput) context.getViewRoot().findComponent("product_form:precio");
        return input.isValid();
    }

    private HttpServletRequest getRequest() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        if (request == null) {
            throw new RuntimeException("Sorry. Got a null request from faces context");
        }
        return request;
    }

}
