public abstract class Objeto {
    private String nombre;
    private int precio;

    public Objeto(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public abstract Objeto copia();

    @Override
    public abstract String toString();
}
