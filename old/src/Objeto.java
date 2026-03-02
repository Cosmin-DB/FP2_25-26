public abstract class Objeto {
    private String nombre;
    private int precio;
    private int peso;

    public Objeto(String nombre, int precio, int peso) {
        this.nombre = nombre;
        this.precio = precio;
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getPeso() {
        return peso;
    }

    public abstract Objeto copia();

    @Override
    public abstract String toString();
}
