public class Metal extends Objeto {
    private int dureza;
    private int pureza;
    private String composicion;

    public Metal(String nombre, int precio, int peso, int dureza, int pureza, String composicion) {
        super(nombre, precio, peso);
        this.dureza = dureza;
        this.pureza = pureza;
        this.composicion = composicion;
    }

    public int getDureza() {
        return dureza;
    }

    public int getPureza() {
        return pureza;
    }

    public String getComposicion() {
        return composicion;
    }

    @Override
    public Objeto copia() {
        return new Metal(getNombre(), getPrecio(), getPeso(), dureza, pureza, composicion);
    }

    @Override
    public String toString() {
        return "METAL: " + getNombre() + " | precio=" + getPrecio() + " | peso=" + getPeso()
                + " | DUR=" + dureza + " | PUR=" + pureza + " | COMP=" + composicion;
    }
}
