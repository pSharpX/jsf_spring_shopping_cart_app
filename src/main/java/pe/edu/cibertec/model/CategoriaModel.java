package pe.edu.cibertec.model;

/**
 * Created by CHRISTIAN on 14/04/2018.
 */
public class CategoriaModel {
    private Long id;
    private String nombre;

    public CategoriaModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
