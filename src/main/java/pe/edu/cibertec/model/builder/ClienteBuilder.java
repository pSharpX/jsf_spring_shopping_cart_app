package pe.edu.cibertec.model.builder;

import pe.edu.cibertec.model.Cliente;

public class ClienteBuilder {
	
	private Cliente cliente;
	
	public ClienteBuilder() {
		this.cliente = new Cliente();
	}

	public ClienteBuilder addCodigo(Integer codigo) {
		this.cliente.setCodigo(codigo);
		return this;
	}
	public ClienteBuilder addNombre(String nombre) {
		this.cliente.setNombre(nombre);
		return this;
	}
	public ClienteBuilder addApellido(String apellido) {
		this.cliente.setApellido(apellido);
		return this;
	}
	public ClienteBuilder addGenero(Character genero) {
		this.cliente.setGenero(genero);
		return this;
	}
	public ClienteBuilder addProfesion(String profesion) {
		this.cliente.setProfesion(profesion);
		return this;
	}
	public ClienteBuilder addNumeroMovil(String numeroMovil) {
		this.cliente.setNumeroMovil(numeroMovil);
		return this;
	}
	public ClienteBuilder addOperadorMovil(String operadorMovil) {
		this.cliente.setOperadorMovil(operadorMovil);
		return this;
	}
	public ClienteBuilder addPublicidad(Boolean publicidad) {
		this.cliente.setPublicidad(publicidad);
		return this;
	}
	public ClienteBuilder addCorreo(String correo) {
		this.cliente.setCorreo(correo);
		return this;
	}
	
	public Cliente build() {
		return cliente;
	}
	
	
}
