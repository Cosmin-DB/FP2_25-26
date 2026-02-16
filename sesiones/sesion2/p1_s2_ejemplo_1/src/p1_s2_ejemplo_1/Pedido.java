package p1_s2_ejemplo_1;

public class Pedido {
	
    // Atributos
    private int numeroPedido;
    private String fecha;
    private Cliente cliente;
    private Libro[] libros;
    private static final int MAX_LIBROS = 20; 
    
    // Constructor
    public Pedido(int numeroPedido, String fecha, Cliente cliente) {
        this.numeroPedido = numeroPedido;
        this.fecha = fecha;
        this.cliente = cliente;
        this.libros = new Libro[MAX_LIBROS];
    }
    
    // Métodos de servicio
    public boolean añadirLibro(Libro libro) {
        boolean añadido = false;
        for (int i = 0; i < MAX_LIBROS; i++) {
            if (libros[i] == null) {
                libros[i] = libro;
                añadido = true;
                break;
            } 
        }
        return añadido;
    }
    
    public double calcularTotal() {
        double total = 0.0;
        for (int i = 0; i < MAX_LIBROS; i++) {
            if (libros[i] != null) {
                total += libros[i].getPrecio();
            }
        }
        return total;
    }
    
    
    // Métodos de acceso (getters)
    public int getNumeroPedido() {
        return numeroPedido;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public Libro[] getLibros() {
        return libros;
    }

    // Métodos de modificación (setters)
    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public void setCliente(Cliente cliente) {
    	this.cliente = cliente;
    }

    public void setLibros(Libro[] libros) {
        this.libros = libros;
    }
    
}
