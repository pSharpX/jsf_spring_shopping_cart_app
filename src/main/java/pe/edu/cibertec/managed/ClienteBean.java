/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.managed;

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.dto.UsuarioDto;
import pe.edu.cibertec.servicio.UsuarioServicio;

/**
 *
 * @author Java-LM
 */
@Component
@Scope(value = "session")
public class ClienteBean {

	private static final String HTTP_GET = "GET";
	private static final String HTTP_POST = "POST";
	
	private static final String ERROR_TITLE_KEY = "operacion.resultado.error";
	private static final String ERROR_MESSAGE_KEY = "operacion.resultado.error.mensaje";
	
	private UsuarioDto cliente = new UsuarioDto();
	private String mensaje;
	private List<UsuarioDto> clientes;

	@Autowired
	private UsuarioServicio dao;

	@PostConstruct
	public void init() {
	}

	public String principal() {
		return "home";
	}

	public String mostrar(Long codigo) {
		try {
			cliente = dao.buscar(codigo);
		} catch (Exception ex) {
		}
		return "customer_detail";
	}

	public String crear() {
		cliente = new UsuarioDto();
		return "customer_create";
	}

	public String guardar(UsuarioDto cliente) {
		try {
			if (cliente == null) {
				throw new Exception("error");
			}
			this.dao.crear(cliente);
			return "result";			
		} catch (Exception ex) {
			ResourceBundle rb = ResourceBundle.getBundle("pe.edu.cibertec.recursos.mensajes",
	                FacesContext.getCurrentInstance().getViewRoot().getLocale());
			
			FacesMessage fm = new FacesMessage(
	                FacesMessage.SEVERITY_ERROR,
	                rb.getString(ERROR_MESSAGE_KEY),
	                rb.getString(ERROR_MESSAGE_KEY));
	        FacesContext.getCurrentInstance().addMessage(null, fm);
	        return null;
		}		
	}

	public String listar() {
		try {
			clientes = dao.obtenerTodos();
		} catch (Exception ex) {
		}
		return "customer_list";
	}

	public String editar(Long codigo) {
		try {
			cliente = dao.buscar(codigo);
		} catch (Exception ex) {
		}
		return "customer_edit";
	}

	public String actualizar(UsuarioDto cliente) {
		try {
			if (cliente == null || cliente.getId() == null) {
				throw new Exception("error");
			}
			this.dao.actualizar(cliente);
		} catch (Exception ex) {
		}
		return "result";
	}

	public String eliminar(Long codigo) {
		String action = "customer_delete";
		try {
			cliente = dao.buscar(codigo);
			action = "customer_delete";
		} catch (Exception ex) {
			action = "customer_delete";
		}
		return action;
	}

	public String confirmarEliminar(Long codigo) {
		String action = "customer_list";
		try {
			String request_method = this.getRequest().getMethod();
			if (HTTP_POST.equalsIgnoreCase(request_method)) {
				this.dao.eliminar(codigo);
				action = "customer_list";
			}
		} catch (Exception ex) {
			cliente = dao.buscar(codigo);
			action = "customer_delete";
		}
		return action;
	}

	public void mensajeProfesion(ValueChangeEvent e) {
		String valor = (String) e.getNewValue();
		if ("001".equals(valor)) {
			setMensaje("Tenemos los mejores cursos de arquitectura");
		}
		if ("002".equals(valor)) {
			setMensaje("Grandes eventos esperan por ti");
		}
	}

	public UsuarioDto getCliente() {
		return cliente;
	}

	public void setCliente(UsuarioDto cliente) {
		this.cliente = cliente;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<UsuarioDto> getClientes() {
		return clientes;
	}

	public void setClientes(List<UsuarioDto> clientes) {
		this.clientes = clientes;
	}

	public boolean isNombreInputValid() {
		FacesContext context = FacesContext.getCurrentInstance();
		UIInput input = (UIInput) context.getViewRoot().findComponent("customer_form:nombre");
		return input.isValid();
	}

	public boolean isApellidoInputValid() {
		FacesContext context = FacesContext.getCurrentInstance();
		UIInput input = (UIInput) context.getViewRoot().findComponent("customer_form:apellido");
		return input.isValid();
	}

	public boolean isNumeroMovilInputValid() {
		FacesContext context = FacesContext.getCurrentInstance();
		UIInput input = (UIInput) context.getViewRoot().findComponent("customer_form:numeroMovil");
		return input.isValid();
	}

	public boolean isNumeroDocumentoInputValid() {
		FacesContext context = FacesContext.getCurrentInstance();
		UIInput input = (UIInput) context.getViewRoot().findComponent("customer_form:numeroDocumento");
		return input.isValid();
	}

	public boolean isFechaNacimientoInputValid() {
		FacesContext context = FacesContext.getCurrentInstance();
		UIInput input = (UIInput) context.getViewRoot().findComponent("customer_form:fechaNacimiento");
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
