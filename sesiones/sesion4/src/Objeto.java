public abstract class Objeto {
    private String nombre;
    private double precio;
    private int peso;

    public Objeto(String nombre, double precio, int peso) {
        this.nombre = nombre;
        this.precio = precio;
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public abstract String toString();
}
