package pe.edu.cibertec.managed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.dto.CarritoCompraDto;
import pe.edu.cibertec.dto.DetalleCarritoDto;
import pe.edu.cibertec.dto.ProductoDto;
import pe.edu.cibertec.servicio.CarritoCompraServicio;
import pe.edu.cibertec.servicio.DetalleCarritoServicio;
import pe.edu.cibertec.servicio.ProductoServicio;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

/**
 * Created by CHRISTIAN on 15/04/2018.
 */
@Component
@Scope(value = "session")
public class CarritoBean {

    private String mensaje;
    private CarritoCompraDto carritoCompraDto = new CarritoCompraDto();
    private DetalleCarritoDto detalleCarritoDto = new DetalleCarritoDto();
    private List<DetalleCarritoDto> collection;

    @Autowired
    private CarritoCompraServicio carritoService;

    @Autowired
    private DetalleCarritoServicio detalleCarritoService;

    @Autowired
    private ProductoServicio productoService;

    @PostConstruct
    public void init() {
        this.carritoCompraDto.setUsuario(this.getUsuario());
        collection = new ArrayList<>();
    }

    public String listar(){
        try{
            if(collection != null && collection.size() > 0){
                this.carritoCompraDto.setDetalleCarrito(this.collection);
                ToDoubleFunction<DetalleCarritoDto> calcSubTotal = d -> d.getPrecioUnitario() * d.getCantidad();
                this.carritoCompraDto.setTotal(this.carritoCompraDto.getDetalleCarrito().stream().mapToDouble(calcSubTotal).sum());
            }
            return "cart_list";
        }catch (Exception ex){
            FacesMessage fm = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }
    }

    public String seleccionarItem(Long idProducto){
        try{
            ProductoDto productoDto = this.productoService.buscar(idProducto);
            if(productoDto == null)
                throw new Exception("Product not found");
            detalleCarritoDto.setProductoId(productoDto.getId());
            detalleCarritoDto.setProducto(productoDto.getNombre());
            detalleCarritoDto.setCantidad(1);
            detalleCarritoDto.setPrecioUnitario(productoDto.getPrecio());
            return "cart_add";
        }catch (Exception ex){
            FacesMessage fm = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }
    }

    public String checkout(){
        try{
            return "cart_checkout";
        }catch (Exception ex){
            FacesMessage fm = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }
    }

    public String save(){
        try{
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

    public String agregarItem(DetalleCarritoDto detalleCarritoDto){
        try {
            addItemToShoppingCart(detalleCarritoDto);
            this.detalleCarritoDto = new DetalleCarritoDto();
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

    private void addItemToShoppingCart(DetalleCarritoDto detalleCarritoDto){
        Predicate<DetalleCarritoDto> predicate = (d) -> d.getProductoId() == detalleCarritoDto.getProductoId();
        if(this.collection.stream().anyMatch(predicate)){
            Consumer<DetalleCarritoDto> consumer = (DetalleCarritoDto d) -> {
                if(d.getProductoId() == detalleCarritoDto.getProductoId()){
                    d.setCantidad(d.getCantidad() + detalleCarritoDto.getCantidad());
                }
            };
            this.collection.forEach(consumer);
        }else{
            this.collection.add(detalleCarritoDto);
        }
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public CarritoCompraDto getCarritoCompraDto() {
        return carritoCompraDto;
    }

    public void setCarritoCompraDto(CarritoCompraDto carritoCompraDto) {
        this.carritoCompraDto = carritoCompraDto;
    }

    public List<DetalleCarritoDto> getCollection() {
        return collection;
    }

    public void setCollection(List<DetalleCarritoDto> collection) {
        this.collection = collection;
    }

    public DetalleCarritoDto getDetalleCarritoDto() {
        return detalleCarritoDto;
    }

    public void setDetalleCarritoDto(DetalleCarritoDto detalleCarritoDto) {
        this.detalleCarritoDto = detalleCarritoDto;
    }

    private String getUsuario(){
        Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Object object = session.get("login");
        if(object == null)
            return null;
        LoginBean loginBean = (LoginBean)object;
        return  loginBean.getUsername();
    }

    public boolean isCantidadInputValid() {
        FacesContext context = FacesContext.getCurrentInstance();
        UIInput input = (UIInput) context.getViewRoot().findComponent("cart_form:cantidad");
        return input.isValid();
    }

    public boolean isPrecioInputValid() {
        FacesContext context = FacesContext.getCurrentInstance();
        UIInput input = (UIInput) context.getViewRoot().findComponent("cart_form:precio");
        return input.isValid();
    }
}
