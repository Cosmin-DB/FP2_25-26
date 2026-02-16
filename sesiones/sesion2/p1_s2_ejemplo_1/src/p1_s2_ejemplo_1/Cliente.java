package p1_s2_ejemplo_1;

public class Cliente {
	
    // Atributos
    private String nombreCompleto;
    private String email;
    private String direccion;
    
    // Constructor
    public Cliente(String nombreCompleto, String email, String direccion) {
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.direccion = direccion;
    }
    
    // Métodos de acceso (getters)
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    // Métodos de modificación (setters)
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
