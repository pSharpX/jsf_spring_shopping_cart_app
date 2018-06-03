package pe.edu.cibertec.model;

/**
 * Created by CHRISTIAN on 14/04/2018.
 */
public class UsuarioModel {

    private String nombre;
    private String apellido;
    private int edad;

    public UsuarioModel() {
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
}
