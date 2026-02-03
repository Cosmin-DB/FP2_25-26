public class Material extends Objeto {
    private int calidad;

    public Material(String nombre, int precio, int calidad) {
        super(nombre, precio);
        this.calidad = calidad;
    }

    public int getCalidad() {
        return calidad;
    }

    @Override
    public Objeto copia() {
        return new Material(getNombre(), getPrecio(), calidad);
    }

    @Override
    public String toString() {
        return "MATERIAL: " + getNombre() + " | precio=" + getPrecio() + " | CAL=" + calidad;
    }
}
