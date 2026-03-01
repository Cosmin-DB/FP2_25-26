public class Arma extends Objeto {
    private int ataque;
    private double longitud;
    private int antiguedad;
    private int integridad;

    public Arma(String nombre, double precio, int peso, int ataque, double longitud, int antiguedad, int integridad) {
        super(nombre, precio, peso);
        this.ataque = ataque;
        this.longitud = longitud;
        this.antiguedad = antiguedad;
        this.integridad = integridad;
    }

    public int getAtaque() {
        return ataque;
    }

    @Override
    public String toString() {
        return "ARMA: " + getNombre() + " | precio=" + getPrecio() + " | peso=" + getPeso()
                + " | ATK=+" + ataque + " | long=" + longitud + " | ant=" + antiguedad + " | int=" + integridad;
    }
}
