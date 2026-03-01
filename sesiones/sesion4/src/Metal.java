public class Metal extends Objeto {
    private int dureza;
    private int pureza;
    private String composicion;

    public Metal(String nombre, double precio, int peso, int dureza, int pureza, String composicion) {
        super(nombre, precio, peso);
        this.dureza = dureza;
        this.pureza = pureza;
        this.composicion = composicion;
    }

    @Override
    public String toString() {
        return "METAL: " + getNombre() + " | precio=" + getPrecio() + " | peso=" + getPeso()
                + " | DUR=" + dureza + " | PUR=" + pureza + " | COMP=" + composicion;
    }
}
