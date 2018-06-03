package pe.edu.cibertec.managed;

import pe.edu.cibertec.dto.CarritoCompraDto;
import pe.edu.cibertec.dto.DetalleCarritoDto;
import pe.edu.cibertec.dto.ProductoDto;
import pe.edu.cibertec.servicio.CarritoCompraServicio;
import pe.edu.cibertec.servicio.DetalleCarritoServicio;
import pe.edu.cibertec.servicio.ProductoServicio;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
@ManagedBean(name = "carritoBean")
@SessionScoped
public class CarritoBean {

    private String mensaje;
    private CarritoCompraDto carritoModel = new CarritoCompraDto();
    private DetalleCarritoDto detalleCarritoModel = new DetalleCarritoDto();
    private List<DetalleCarritoDto> detalleCarritoModels;

    private CarritoCompraServicio carritoService;

    private DetalleCarritoServicio detalleCarritoService;

    private ProductoServicio productoService;

    @PostConstruct
    public void init() {
        this.carritoModel.setUsuario(this.getUsuario());
        detalleCarritoModels = new ArrayList<>();
    }

    public String listar(){
        try{
            if(detalleCarritoModels != null && detalleCarritoModels.size() > 0){
                this.carritoModel.setDetalleCarrito(this.detalleCarritoModels);
                ToDoubleFunction<DetalleCarritoDto> calcSubTotal = d -> d.getPrecioUnitario() * d.getCantidad();
                this.carritoModel.setTotal(this.carritoModel.getDetalleCarrito().stream().mapToDouble(calcSubTotal).sum());
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
            ProductoDto productoModel = this.productoService.buscar(idProducto);
            if(productoModel == null)
                throw new Exception("Product not found");
            detalleCarritoModel.setProductoId(productoModel.getId());
            detalleCarritoModel.setProducto(productoModel.getNombre());
            detalleCarritoModel.setCantidad(1);
            detalleCarritoModel.setPrecioUnitario(productoModel.getPrecio());
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

    public String agregarItem(DetalleCarritoDto detalleCarritoModel){
        try {
            addItemToShoppingCart(detalleCarritoModel);
            this.detalleCarritoModel = new DetalleCarritoDto();
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

    private void addItemToShoppingCart(DetalleCarritoDto detalleCarritoModel){
        Predicate<DetalleCarritoDto> predicate = (d) -> d.getProductoId() == detalleCarritoModel.getProductoId();
        if(this.detalleCarritoModels.stream().anyMatch(predicate)){
            Consumer<DetalleCarritoDto> consumer = (DetalleCarritoDto d) -> {
                if(d.getProductoId() == detalleCarritoModel.getProductoId()){
                    d.setCantidad(d.getCantidad() + detalleCarritoModel.getCantidad());
                }
            };
            this.detalleCarritoModels.forEach(consumer);
        }else{
            this.detalleCarritoModels.add(detalleCarritoModel);
        }
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public CarritoCompraDto getCarritoModel() {
        return carritoModel;
    }

    public void setCarritoModel(CarritoCompraDto carritoModel) {
        this.carritoModel = carritoModel;
    }

    public List<DetalleCarritoDto> getDetalleCarritoModels() {
        return detalleCarritoModels;
    }

    public void setDetalleCarritoModels(List<DetalleCarritoDto> detalleCarritoModels) {
        this.detalleCarritoModels = detalleCarritoModels;
    }

    public DetalleCarritoDto getDetalleCarritoModel() {
        return detalleCarritoModel;
    }

    public void setDetalleCarritoModel(DetalleCarritoDto detalleCarritoModel) {
        this.detalleCarritoModel = detalleCarritoModel;
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
