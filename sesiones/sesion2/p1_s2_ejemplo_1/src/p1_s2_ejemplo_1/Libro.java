package p1_s2_ejemplo_1;

public class Libro {
	
    // Atributos
    private String codigo;
    private String titulo;
    private String autor;
    private double precio;
    
    // Constructor
    public Libro(String codigo, String titulo, String autor, double precio) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
    }
    
    // Métodos de acceso (getters)
    public String getCodigo() {
        return codigo;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public double getPrecio() {
        return precio;
    }

    // Métodos de modificación (setters)
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
}
